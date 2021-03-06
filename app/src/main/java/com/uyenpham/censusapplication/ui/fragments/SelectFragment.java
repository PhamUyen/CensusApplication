package com.uyenpham.censusapplication.ui.fragments;

import android.os.Build;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Question;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.models.Survey;

import java.util.Map;

import ca.dalezak.androidbase.annotations.Control;
import ca.dalezak.androidbase.annotations.Type;
import ca.dalezak.androidbase.utils.Log;
import ca.dalezak.androidbase.utils.Strings;
import ca.dalezak.androidbase.utils.Toast;

@Type(SelectFragment.TYPE)
public class SelectFragment extends WidgetFragment {

    public static final String TYPE = "select";

    @Control("scroll_view")
    public ScrollView scrollView;

    @Control("radio_group")
    public RadioGroup radioGroup;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_clear) {
            radioGroup.clearCheck();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "load %d %s %s", question.cid, question.name, answer.value);
        radioGroup.removeAllViews();
        for (Map.Entry<String, String> entry : question.items().entrySet()) {
            final RadioButton radioButton = getRadioButton(
                    radioGroup.getChildCount(),
                    entry.getValue(),
                    entry.getKey(),
                    answer.value);
            radioGroup.addView(radioButton);
            if (radioButton.isChecked()) {
                final ViewTreeObserver viewTreeObserver = radioButton.getViewTreeObserver();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        try {
                            int top = radioButton.getTop();
                            scrollView.scrollTo(0, top);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                viewTreeObserver.removeOnGlobalLayoutListener(this);
                            }
                            else {
                                viewTreeObserver.removeGlobalOnLayoutListener(this);
                            }
                        }
                        catch (Exception ex) {
                            Log.w(this, "ViewTreeObserver Exception", ex);
                        }
                    }
                });
            }
        }
        if (Strings.isNullOrEmpty(question.description)) {
            labelDescription.setVisibility(View.GONE);
        }
        else {
            labelDescription.setVisibility(View.VISIBLE);
            labelDescription.setText(Html.fromHtml(question.description));
        }
        return true;
    }

    @Override
    public boolean save(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "save %d %s %s", question.cid, question.name, answer.value);
        if (radioGroup.getChildCount() > 0) {
            int buttonID = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton)radioGroup.findViewById(buttonID);
            if (radioButton != null) {
                Log.i(this, "save %s %s=%s", question.name, radioButton.getTag(), radioButton.getText());
                answer.value = radioButton.getTag().toString();
            }
            else {
                Log.i(this, "save %s none selected", question.name);
                answer.value = null;
            }
        }
        else {
            answer.value = null;
        }
//        answer.save();
        if (question.required && Strings.isNullOrEmpty(answer.value)) {
            radioGroup.requestFocus();
            Toast.showShort(getActivity(), R.string.selection_required);
            return false;
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select;
    }

    @Override
    protected void createView(View view) {

    }
}