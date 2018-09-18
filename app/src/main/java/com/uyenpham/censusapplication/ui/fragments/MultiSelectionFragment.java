package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class MultiSelectionFragment extends BaseFragment{
    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.list_checkbox)
    LinearLayout listCheckbox;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    @Override
    protected int getLayoutId() {
        return R.id.fragment_multi_selection;
    }

    @Override
    protected void createView(View view) {
     listCheckbox.removeAllViews();
     loadQuestion(questionDTO,answerDTO);
    }

    private void loadQuestion(QuestionDTO question, AnswerDTO answer){
        tvQuestion.setText(question.getQuestion());
        String options = question.getOptions();
        ArrayList<String> listOption = new ArrayList<>(Arrays.asList(options.split(",")));
        for(String option :listOption){
            CheckBox checkBox = getCheckbox(listOption.indexOf(option),option,String.valueOf(listOption.indexOf(option)+1),"0");
            listCheckbox.addView(checkBox);
        }
    }

    private CheckBox getCheckbox(Integer id, String text, String tag, String value) {
        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setId(id);
        checkBox.setText(text);
        checkBox.setTag(tag);
        checkBox.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        checkBox.setLayoutParams(getLayoutParams(margin,margin, margin, margin));
        checkBox.setButtonDrawable(R.drawable.bg_radio_button);
        if (value != null && value.equals(tag)) {
            checkBox.setChecked(true);
        }
        else {
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
}
