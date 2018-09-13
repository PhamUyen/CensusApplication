package com.uyenpham.censusapplication.tasks;

import android.content.Context;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Survey;
import com.uyenpham.censusapplication.utils.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Date;

import ca.dalezak.androidbase.tasks.HttpGetTask;
import ca.dalezak.androidbase.utils.Dates;


public class DownloadSurveys extends HttpGetTask<Survey> {

    private static final String COOKIE = "Cookie";
    private static final String SINCE = "since";
    private URI uri;

    public DownloadSurveys(Context context) {
        this(context, false);
    }

    public DownloadSurveys(Context context, boolean loading) {
        super(context, Prefs.getServer(), "/api/v1/surveys", R.string.downloading_surveys_, loading);
        this.uri = getURI();
    }

    @Override
    protected void onPrepareRequest() {
        if (Prefs.hasSince(uri)) {
            Date date = Prefs.getSince(uri);
            addParameter(SINCE, Dates.toEpochString(date));
        }
        addHeader(COOKIE, Prefs.getCookie());
        setUsername(Prefs.getUsername());
        setPassword(Prefs.getPassword());
    }

    @Override
    protected Survey onHandleResponse(JSONObject json) throws JSONException {
        Integer nid = json.getInt(Survey.Columns.NID);
        Survey survey = Survey.find(nid);
        if (survey == null) {
            survey = new Survey();
            survey.nid = nid;
        }
        survey.uri = json.getString(Survey.Columns.URI);
        survey.type = json.getString(Survey.Columns.TYPE);
        survey.title = json.getString(Survey.Columns.TITLE);
        survey.type = json.getString(Survey.Columns.TYPE);
        String created = json.optString(Survey.Columns.CREATED);
        if (created != null) {
            survey.created = Dates.fromEpochString(created);
        }
        String changed = json.optString(Survey.Columns.CHANGED);
        if (changed != null) {
            survey.changed = Dates.fromEpochString(changed);
        }
        survey.save();
        Prefs.setSince(uri, new Date());
        return survey;
    }
}