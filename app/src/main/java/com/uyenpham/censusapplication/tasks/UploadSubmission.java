package com.uyenpham.censusapplication.tasks;

import android.content.Context;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.utils.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import ca.dalezak.androidbase.tasks.HttpPostTask;
import ca.dalezak.androidbase.utils.Dates;
import ca.dalezak.androidbase.utils.Log;

public class UploadSubmission extends HttpPostTask<Submission> {

    private static final String COOKIE = "Cookie";
    private static final String TIMESTAMP = "submission_timestamp";
    private static final String PREFIX = "cid_";
    private Submission submission;

    public UploadSubmission(Context context, Submission submission) {
       this(context, submission, false);
    }

    public UploadSubmission(Context context, Submission submission, boolean loading) {
        super(context, Prefs.getServer(), String.format("api/v1/surveys/%s", submission.survey.nid), R.string.submitting_, loading);
        this.submission = submission;
    }

    @Override
    protected void onPrepareRequest() {
        setUsername(Prefs.getUsername());
        setPassword(Prefs.getPassword());
        addHeader(COOKIE, Prefs.getCookie());
        for (Answer answer : submission.answers()) {
            String key = String.format("%s%s", PREFIX, answer.cid);
            if (answer.hasFile()) {
                addParameter(key, answer.getFile());
            }
            else if (answer.hasValue()) {
                addParameter(key, answer.value);
            }
        }
        addParameter(TIMESTAMP, Dates.toEpochString(submission.changed));
    }

    @Override
    protected Submission onHandleResponse(JSONObject json) throws JSONException {
        Log.i(this, "JSON %s", json);
        if (json.length() > 0) {
            submission.uploaded = new Date();
            submission.nid = json.optInt(Submission.Columns.NID);
            submission.sid = json.optInt(Submission.Columns.SID);
            if (submission.nid != 0 && submission.sid != 0) {
                submission.uri = String.format("%s/node/%s/submission/%s",
                        Prefs.getServer(), submission.nid, submission.sid);
            }
        }
        submission.save();
        return submission;
    }

}