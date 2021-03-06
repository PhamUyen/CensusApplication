package com.uyenpham.censusapplication.ui.fragments;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Question;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.models.Survey;

import ca.dalezak.androidbase.annotations.Control;
import ca.dalezak.androidbase.annotations.Type;
import ca.dalezak.androidbase.utils.Log;
import ca.dalezak.androidbase.utils.Strings;

@Type(MarkupFragment.TYPE)
public class MarkupFragment extends WidgetFragment {

    public static final String TYPE = "markup";

    @Control("label_markup")
    public TextView labelMarkup;


    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "load %d %s %s", question.cid, question.name, answer.value);
        if (Strings.isNullOrEmpty(question.value)) {
            labelMarkup.setText(null);
        }
        else {
            Log.i(this, "HTML %s", question.value);
            labelMarkup.setText(Html.fromHtml(question.value));
        }
        return true;
    }

    @Override
    public boolean save(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "save %d %s %s", question.cid, question.name, answer.value);
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_markup;
    }

    @Override
    protected void createView(View view) {

    }
}