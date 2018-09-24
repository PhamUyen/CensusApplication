package com.uyenpham.censusapplication.ui.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.MultiSelectAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class SingleSelectFragment extends BaseTypeFragment implements IRecyclerViewListener,
        INextQuestion, IPreviousQuestion{
    @Bind(R.id.scroll_view)
    ScrollView scrollView;

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;

    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.ed_other)
    EditText edOther;

    @Bind(R.id.rcv_select)
    RecyclerView rcvSelect;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<PeopleDetailDTO> listMember;
    private MultiSelectAdapter adapter;
    private ArrayList<PeopleDetailDTO> listSelected;
    private boolean isYes;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_selection;
    }

    @Override
    protected void createView(View view) {
        radioGroup.removeAllViews();
        activity.setiNext(this);
        activity.setiPrevious(this);
        listSelected = new ArrayList<>();
        loadQuestion(questionDTO, answerDTO);
    }
    public boolean loadQuestion(final QuestionDTO question, AnswerDTO answer){
        tvQuestion.setText(question.getQuestion());
        ArrayList<OptionDTO> listOption = question.getOptions();
        for(OptionDTO option :listOption){
            RadioButton radioButton = getRadioButton(listOption.indexOf(option)+1,option.getOption(),String.valueOf(option.getType()),"0");
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb= radioGroup.findViewById(i);
                if(rb.isChecked() && rb.getTag().equals(Constants.TYPE_SHOW.toUpperCase())){
                    isYes = true;
                    if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                        Constants.mStaticObject.getPeopleDTO().set(question.getId(),1);
                    }
                    if(question.getType() == Constants.TYPE_SELECT_INPUT){
                        edOther.setVisibility(View.VISIBLE);
                        edOther.setSingleLine();
                        edOther.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        edOther.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                                if (actionId == EditorInfo.IME_ACTION_DONE) {
                                    if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                                        int index = Constants.mStaticObject.getPeopleDetailDTO().size();
                                        PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
                                        peopleDetailDTO.setIDHO(Constants.mStaticObject.getIdHo());
                                        peopleDetailDTO.setHOSO(Constants.mStaticObject.getPeopleDTO().getHOSO());
                                        peopleDetailDTO.setQ1(edOther.getText().toString());
                                        peopleDetailDTO.setSTT(index);
                                        peopleDetailDTO.setChuho(1);
                                        peopleDetailDTO.setID(Constants.mStaticObject.getIdHo() + index);

                                        Constants.mStaticObject.getPeopleDetailDTO().add(peopleDetailDTO);
                                    }
                                    edOther.setText(null);
                                    return true;
                                }
                                return false;
                            }
                        });
                    }else if(question.getType() == Constants.TYPE_SINGLE_SELECT_LIST){
                        rcvSelect.setVisibility(View.VISIBLE);
                        setupListSelected();
                    }
                }else {
                    edOther.setVisibility(View.GONE);
                    rcvSelect.setVisibility(View.GONE);
                    for(PeopleDetailDTO peopleDetailDTO : listMember){
                        peopleDetailDTO.setSelected(false);
                    }
                    adapter.notifyDataSetChanged();
                    isYes = false;
                    listSelected.clear();
                    if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                        Constants.mStaticObject.getPeopleDTO().set(question.getId(),2);
                    }
                }
            }
        });
        return true;
    }

    private void setupListSelected(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSelect.setLayoutManager(linearLayoutManager);
        listMember = Constants.mStaticObject.getPeopleDetailDTO();
        adapter = new MultiSelectAdapter(listMember);
        adapter.setListener(this);
        rcvSelect.setAdapter(adapter);
    }

    @Override
    public boolean save(QuestionDTO questionDTO, AnswerDTO answerDTO, Object answer) {
        if (answerDTO == null) {
            answerDTO = new AnswerDTO();
        }
        answerDTO.setQuestionID(questionDTO.getId());
        answerDTO.setAnswer(answer);
        AnswerDAO.getInstance().insert(answerDTO);
        saveAnswerToSurvey(questionDTO, answerDTO);
        return false;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getSurvey()){
            case Constants.SURVEY_PEOPLE:
                if(isYes && listSelected.size() == 0){
                    return false;
                }else {
                    return true;
                }

        }
        return true;
    }

    protected RadioButton getRadioButton(Integer id, String text, String tag, String value) {
        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setId(id);
        radioButton.setText(text);
        radioButton.setTag(tag);
        radioButton.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        radioButton.setLayoutParams(getLayoutParams(margin,margin, margin, margin));
        radioButton.setButtonDrawable(R.drawable.bg_radio_button);
        radioButton.setPadding(margin,0,0,0);
        if (value != null && value.equals(tag)) {
            radioButton.setChecked(true);
        }
        else {
            radioButton.setChecked(false);
        }
        return radioButton;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    @Override
    public void onClickItem(View view, int postion) {
        PeopleDetailDTO peopleSelected= listMember.get(postion);
        if(peopleSelected.isSelected()){
            peopleSelected.setSelected(false);
            if(listSelected.contains(peopleSelected)){
                listSelected.remove(peopleSelected);
            }
        }else {
            peopleSelected.setSelected(true);
            addListNotDuplicate(peopleSelected);
        }
        adapter.notifyDataSetChanged();
    }

    private void addListNotDuplicate(PeopleDetailDTO people){
        if(listSelected != null && listSelected.size() > 0){
            if(!listSelected.contains(people)){
                listSelected.add(people);
            }
        }else {
            listSelected.add(people);
        }
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }

    @Override
    public void next() {
        if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
            if(validateQuaetion(questionDTO,answerDTO)){
                Constants.mStaticObject.setPeopleDetailDTO(listSelected);
                nextFragment();
            }else {
                DialogUtils.showErrorAlert(activity,activity.getString(R.string.txt_select_require));
            }
        }
    }
    private void nextFragment(){
        if (currentIndex < getListQuestion().size() - 1) {
            currentIndex++;
            replcaeFragmentByType(getListQuestion().get(currentIndex), true);
        }
    }

    @Override
    public void previuos() {

    }
}
