package com.uyenpham.censusapplication.ui.fragments;

import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import ca.dalezak.androidbase.utils.Toast;

@Type(NumberFragment.TYPE)
public class NumberFragment extends WidgetFragment {

    public static final String TYPE = "number";

    @Control("edit_text")
    public EditText editText;

    @Control("label_prefix")
    public TextView labelPrefix;

    @Control("label_suffix")
    public TextView labelSuffix;

    public NumberFragment() {
        super(R.layout.fragment_number, 0);
    }


    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "load %d %s %s", question.cid, question.name, answer.value);
        if (Strings.isNullOrEmpty(question.placeholder)) {
            editText.setHint(R.string.enter_number);
        }
        else {
            editText.setHint(question.placeholder);
        }
        if (Strings.isNullOrEmpty(question.prefix)) {
            labelPrefix.setVisibility(View.GONE);
        }
        else {
            labelPrefix.setVisibility(View.VISIBLE);
            labelPrefix.setText(question.prefix);
        }
        if (Strings.isNullOrEmpty(question.suffix)) {
            labelSuffix.setVisibility(View.GONE);
        }
        else {
            labelSuffix.setVisibility(View.VISIBLE);
            labelSuffix.setText(question.suffix);
        }
        editText.setText(answer.value);
        return true;
    }

    @Override
    public boolean save(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "save %d %s %s", question.cid, question.name, answer.value);
        answer.value = editText.getText().toString();
//        answer.save();
        if (question.required && Strings.isNullOrEmpty(answer.value)) {
            editText.requestFocus();
            Toast.showShort(getActivity(), R.string.number_required);
            return false;
        }
        return true;
    }

}