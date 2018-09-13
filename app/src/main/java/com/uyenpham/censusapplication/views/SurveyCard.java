package com.uyenpham.censusapplication.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.worldbank.armm.app.R;
import org.worldbank.armm.app.models.Survey;

import ca.dalezak.androidbase.annotations.Control;
import ca.dalezak.androidbase.views.BaseCard;

public class SurveyCard extends BaseCard<Survey> {

    @Control("survey_title")
    public TextView labelTitle;

    @Control("survey_description")
    public TextView labelDescription;

    @Control("survey_questions")
    public TextView labelQuestions;

    public SurveyCard(Context context, View view) {
        super(context, view);
    }

    @Override
    public void setSelected(boolean selected) {
        if (selected) {
            cardView.setCardBackgroundColor(getContext().getResources().getColor(R.color.card_selected));
        }
        else {
            cardView.setCardBackgroundColor(getContext().getResources().getColor(R.color.white_dark));
        }
    }
}