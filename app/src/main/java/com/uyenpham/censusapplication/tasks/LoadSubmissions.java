package com.uyenpham.censusapplication.tasks;

import android.content.Context;

import org.worldbank.armm.app.models.Submission;

import ca.dalezak.androidbase.tasks.LoadTask;

public class LoadSubmissions extends LoadTask<Submission> {

    public LoadSubmissions(Context context) {
        super(context);
    }

    @Override
    protected Exception doInBackground(Object...args) {
        for (Submission submission : Submission.all()) {
            submission.updateAnswers();
            submission.updateQuestions();
            submission.updateCompleted();
        }
        return null;
    }
}