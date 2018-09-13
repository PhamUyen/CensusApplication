package com.uyenpham.censusapplication.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.views.SubmissionCard;

import java.util.List;

import ca.dalezak.androidbase.adapters.BaseCardAdapter;
import ca.dalezak.androidbase.utils.Dates;
import ca.dalezak.androidbase.utils.Strings;

public class IncompleteCardAdapter extends BaseCardAdapter<Submission, SubmissionCard> {

    public IncompleteCardAdapter(Context context) {
        super(context, Submission.class, SubmissionCard.class, R.layout.fragment_submission_card);
    }

    @Override
    public void onBindViewHolder(SubmissionCard card, int index) {
        Submission submission = getItem(index);
        if (submission != null) {
            if (Strings.isNullOrEmpty(submission.survey.title)) {
                card.labelTitle.setText(R.string.survey_no_title);
            }
            else {
                card.labelTitle.setText(submission.survey.title);
            }
            card.labelSaved.setText(R.string.saved);
            card.dateSaved.setText(Dates.toDateTimeString(submission.changed).replace("AM","am").replace("PM", "pm"));
            card.dateSaved.setTypeface(null, Typeface.NORMAL);
            card.labelSubmitted.setText(R.string.completed);
            if (submission.completed != null) {
                card.dateSubmitted.setText(Dates.toDateTimeString(submission.completed).replace("AM","am").replace("PM", "pm"));
                card.rowSubmitted.setVisibility(View.VISIBLE);
            }
            else {
                card.rowSubmitted.setVisibility(View.GONE);
            }
            card.progressRequired.setProgress(submission.requiredAnswers());
            card.progressRequired.setMax(submission.requiredQuestions());
            card.progressTotal.setProgress(submission.answerCount());
            card.progressTotal.setMax(submission.survey.allQuestionCount());
        }
    }

    @Override
    public List<Submission> getItems() {
        return Submission.incomplete();
    }

}