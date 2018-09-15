package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.EditText;

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

@Type(MultilineFragment.TYPE)
public class MultilineFragment extends WidgetFragment {

    public static final String TYPE = "textarea";

    @Control("edit_text")
    public EditText editText;


    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "load %d %s %s", question.cid, question.name, answer.value);
        if (Strings.isNullOrEmpty(question.placeholder)) {
            editText.setHint(R.string.enter_text);
        }
        else {
            editText.setHint(question.placeholder);
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
            Toast.showShort(getActivity(), R.string.text_required);
            return false;
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multiline;
    }

    @Override
    protected void createView(View view) {

    }
}