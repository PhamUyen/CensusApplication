package com.uyenpham.censusapplication.ui.fragments;

import android.widget.PopupMenu;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.tasks.LoginUser;
import com.uyenpham.censusapplication.ui.adapters.CompletedCardAdapter;
import com.uyenpham.censusapplication.utils.Prefs;
import com.uyenpham.censusapplication.views.SubmissionCard;

import ca.dalezak.androidbase.fragments.BaseCardsFragment;
import ca.dalezak.androidbase.models.BaseModel;
import ca.dalezak.androidbase.tasks.HttpQueue;
import ca.dalezak.androidbase.tasks.HttpTask;
import ca.dalezak.androidbase.utils.Alert;
import ca.dalezak.androidbase.utils.Internet;
import ca.dalezak.androidbase.utils.Log;

public class CompletedCardsFragment
        extends BaseCardsFragment<Submission, SubmissionCard, CompletedCardAdapter>
        implements HttpQueue.Callback {

    public CompletedCardsFragment() {
        super(CompletedCardAdapter.class, R.string.no_completed_surveys, 0);
        setColumns(1, 1, 2, 2);
    }

    @Override
    public void onVisible() {
        super.onVisible();
        HttpQueue.getInstance().register(this);
    }

    @Override
    public void onHidden() {
        super.onHidden();
        HttpQueue.getInstance().unregister(this);
    }


    @Override
    public void onRefresh() {
        if (Internet.isAvailable(getActivity())) {
            if (!Prefs.hasCookie()) {
                HttpQueue.getInstance().add(new LoginUser(getActivity(), Prefs.getUsername(), Prefs.getPassword()));
            }
//            for (Submission submission : Submission.pending()) {
//                HttpQueue.getInstance().add(new UploadSubmission(getActivity(), submission));
//            }
            if (HttpQueue.getInstance().size() > 0) {
                HttpQueue.getInstance().start();
                showRefreshing(R.string.posting_);
            }
            else {
                hideRefreshing();
            }
        }
        else {
            hideRefreshing();
            new Alert(getActivity(),
                    R.string.no_internet_connection,
                    R.string.verify_internet_connection).showOk(R.string.ok);
        }
    }

    @Override
    public void onCardSelected(SubmissionCard card, Submission submission) {
//        new ShowAnswerList(getActivity(), card, submission).execute();
    }

    @Override
    public void onCardPressed(final SubmissionCard card, final Submission submission) {
        PopupMenu popup = new PopupMenu(getActivity(), card.cardView);
    }

    @Override
    public void onQueueStarted(int total) {

    }

    @Override
    public void onQueueResumed() {

    }

    @Override
    public void onQueueProgress(int total, int progress) {
        if (getListAdapter().getItemCount() == 0) {
            showLoading(R.string.posting_submissions_, total, progress);
        }
    }

    @Override
    public void onQueuePaused() {

    }

    @Override
    public void onQueueCancelled() {

    }

    @Override
    public void onQueueFinished() {
        onRefreshed();
        hideLoading();
        hideRefreshing();
    }

    @Override
    public void onQueueFailed(Exception exception) {
        onRefreshed();
        hideLoading();
        hideRefreshing();
        new Alert(getActivity(), exception).showOk(R.string.ok);
    }

    @Override
    public void onTaskStarted(HttpTask task) {
        Log.i(this, "onTaskStarted %s", task);
        if (task.isLoading()) {
            showLoading(task.getMessage());
        }
        else {
            showRefreshing(task.getMessage());
        }
    }

    @Override
    public void onTaskCancelled(HttpTask task) {

    }

    @Override
    public void onTaskProgress(HttpTask task, BaseModel model, int total, int progress) {
        Log.i(this, "onTaskStarted %s %d / %d", task, progress, total);
        if (task.isLoading()) {
            showLoading(task.getMessage(), total, progress);
        }
        else {
            showRefreshing(task.getMessage());
        }
    }

    @Override
    public void onTaskFinished(HttpTask task) {

    }

    @Override
    public void onTaskFailed(HttpTask task, Exception exception) {

    }

//    private class ShowAnswerList extends CardTask<Void, SubmissionCard> {
//
//        public ShowAnswerList(Activity activity, SubmissionCard card, Submission submission) {
//            super(activity, card, submission, R.string.loading_);
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            Intent intent = new Intent(getActivity(), AnswerListActivity.class);
////            intent.putExtra(Submission.class.getName(), getModel().uuid);
////            return intent;
//        }
//    }

}