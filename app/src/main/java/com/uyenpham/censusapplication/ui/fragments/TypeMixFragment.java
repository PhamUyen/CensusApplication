package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;

import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;

public class TypeMixFragment extends BaseTypeFragment {
    @Override
    public boolean loadQuestion(QuestionDTO questionDTO, AnswerDTO answerDTO) {
        return false;
    }

    @Override
    public boolean save(QuestionDTO questionDTO, AnswerDTO answerDTO, Object answer) {
        return false;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void createView(View view) {

    }
}
