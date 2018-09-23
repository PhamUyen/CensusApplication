package com.uyenpham.censusapplication.ui.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;

public class SingleSelectFragment extends BaseTypeFragment{
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
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_selection;
    }

    @Override
    protected void createView(View view) {
        radioGroup.removeAllViews();
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
                if(rb.isChecked() && rb.getTag().equals(Constants.TYPE_SHOW)){
                    Constants.mStaticObject.getPeopleDTO().set(question.getId(),2);
                    if(question.getType() == Constants.TYPE_SELECT_INPUT){
                        edOther.setVisibility(View.VISIBLE);
                    }else if(question.getType() == Constants.TYPE_SINGLE_SELECT_LIST){
                        rcvSelect.setVisibility(View.VISIBLE);
                    }
                }else {
                    Constants.mStaticObject.getPeopleDTO().set(question.getId(),1);
                }
            }
        });
        return true;
    }

    @Override
    public boolean save(QuestionDTO questionDTO, AnswerDTO answerDTO, Object answer) {
        return false;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        return false;
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
}
