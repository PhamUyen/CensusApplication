package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Question;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.models.Survey;

import ca.dalezak.androidbase.annotations.Control;

public class ErrorFragment extends WidgetFragment {

    @Control("label_error")
    public TextView labelError;


    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        if (question != null) {
            labelError.setText(String.format("Unable to load widget for questionByPosition type '%s'.", question.type));
        }
        else {
            labelError.setText("Unable to load widget for questionByPosition.");
        }
        return true;
    }

    @Override
    public boolean save(Survey survey, Question question, Submission submission, Answer answer) {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_error;
    }

    @Override
    protected void createView(View view) {

    }
}