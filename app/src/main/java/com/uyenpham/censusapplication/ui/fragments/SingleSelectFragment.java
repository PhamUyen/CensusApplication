package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class SingleSelectFragment extends BaseFragment{
    @Bind(R.id.scroll_view)
    ScrollView scrollView;

    @Bind(R.id.radio_group)
    RadioGroup radioGroup;

    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.ed_other)
    EditText edOther;

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
    private void loadQuestion(QuestionDTO question, AnswerDTO answer){
        tvQuestion.setText(question.getQuestion());
        String options = question.getOptions();
        ArrayList<String> listOption = new ArrayList<>(Arrays.asList(options.split(",")));
        for(String option :listOption){
            RadioButton radioButton = getRadioButton(listOption.indexOf(option),option,String.valueOf(listOption.indexOf(option)+1),"0");
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb= radioGroup.findViewById(i);
            }
        });
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
