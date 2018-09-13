package com.uyenpham.censusapplication.ui.adapters;

import android.content.Context;
import android.view.View;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Survey;
import com.uyenpham.censusapplication.views.SurveyCard;

import java.util.List;

import ca.dalezak.androidbase.adapters.BaseCardAdapter;
import ca.dalezak.androidbase.utils.Dates;
import ca.dalezak.androidbase.utils.Strings;

public class SurveyCardAdapter extends BaseCardAdapter<Survey, SurveyCard> {

    public SurveyCardAdapter(Context context) {
        super(context, Survey.class, SurveyCard.class, R.layout.fragment_survey_card);
    }

    @Override
    public void onBindViewHolder(SurveyCard card, int index) {
        Survey survey = getItem(index);
        if (survey != null) {
            if (Strings.isNullOrEmpty(survey.title)) {
                card.labelTitle.setText(R.string.survey_no_title);
            }
            else {
                card.labelTitle.setText(survey.title);
            }
            if (Strings.isNullOrEmpty(survey.description)) {
                card.labelDescription.setText(null);
                card.labelDescription.setVisibility(View.GONE);
            }
            else {
                card.labelDescription.setText(survey.description);
                card.labelDescription.setVisibility(View.VISIBLE);
            }
            String questions = survey.parentQuestionCount() == 1 ? getContext().getString(R.string.question) : getContext().getString(R.string.questions);
            if (survey.changed != null) {
                String date = Dates.toDateString(survey.changed);
                card.labelQuestions.setText(String.format("%s (%d %s)", date, survey.parentQuestionCount(), questions.toLowerCase()));
            }
            else {
                card.labelQuestions.setText(String.format("%d %s", survey.parentQuestionCount(), questions));
            }
        }
    }

    @Override
    public List<Survey> getItems() {
        return Survey.all();
    }

}