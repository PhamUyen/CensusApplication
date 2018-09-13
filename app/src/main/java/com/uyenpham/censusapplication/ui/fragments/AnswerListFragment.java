package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.AdapterView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.ui.adapters.AnswerListAdapter;

import ca.dalezak.androidbase.fragments.BaseListFragment;
import ca.dalezak.androidbase.utils.Log;
import ca.dalezak.androidbase.utils.Strings;

public class AnswerListFragment extends BaseListFragment<Answer, AnswerListAdapter> {

    private Submission submission;

    public AnswerListFragment() {
        super(AnswerListAdapter.class, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        submission = Submission.find(getStringExtra(Submission.class));
        if (submission != null) {
            if (!Strings.isNullOrEmpty(submission.title)) {
                setTitle(submission.title);
            }
            else if (!Strings.isNullOrEmpty(submission.survey.title)) {
                setTitle(submission.survey.title);
            }
            else {
                setTitle(getString(R.string.survey));
            }
            getListAdapter().setSubmission(submission);
        }
        getListAdapter().refresh();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(this, "onItemClick %d", position);
    }

    @Override
    public void onRefresh() {
        hideRefreshing();
    }

}