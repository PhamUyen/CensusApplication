package com.uyenpham.censusapplication.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.RadioButtonAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRadioButtonClick;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class MultiSelectionFragment extends BaseTypeFragment implements IRadioButtonClick,
        INextQuestion, IPreviousQuestion {
    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.list_checkbox)
    RecyclerView listCheckbox;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multi_selection;
    }

    @Override
    protected void createView(View view) {
        listCheckbox.removeAllViews();

        loadQuestion(questionDTO);
    }


    private CheckBox getCheckbox(Integer id, String text, String tag, String value) {
        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setId(id);
        checkBox.setText(text);
        checkBox.setTag(tag);
        checkBox.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        checkBox.setLayoutParams(getLayoutParams(margin, margin, margin, margin));
        checkBox.setButtonDrawable(R.drawable.bg_radio_button);
        checkBox.setPadding(margin, 0, 0, 0);
        if (value != null && value.equals(tag)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        return checkBox;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    @Override
    public boolean loadQuestion(QuestionDTO question) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listCheckbox.setLayoutManager(linearLayoutManager);
        tvQuestion.setText(question.getQuestion());
        listOption = question.getOptions();
        adapter = new RadioButtonAdapter(listOption);
        listCheckbox.setAdapter(adapter);
        adapter.setListener(this);
        return false;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        return false;
    }

    @Override
    public void onRadioClick(int pos, boolean isChecked) {
        OptionDTO option = listOption.get(pos);
        option.setSelected(!isChecked);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void next() {
        switch (questionDTO.getId()) {
            case Constants.QUESTION_Q9:
                ArrayList<GroupDrawer> list = new ArrayList<>();
                for(PeopleDetailDTO peopleDetailDTO : Constants.mStaticObject.getPeopleDetailDTO()){
                    GroupDrawer drawer = new GroupDrawer(peopleDetailDTO.getQ1(), null);
                    list.add(drawer);
                }
                activity.setList(list);
                activity.getNavigationBar().setTitle(list.get(0).getName());
                break;
            default:
                break;
        }
    }

    @Override
    public void previuos() {
        if (currentIndex > 0) {
            currentIndex--;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), false,getListQuestion(),activity.mFragmentManager,getPosMember());
        }
    }
}
