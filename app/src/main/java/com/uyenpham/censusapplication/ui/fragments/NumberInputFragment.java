package com.uyenpham.censusapplication.ui.fragments;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;

import java.util.ArrayList;

import butterknife.Bind;

public class NumberInputFragment extends BaseFragment {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;


    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_number_input;
    }

    @Override
    protected void createView(View view) {
        loadQuestion(questionDTO, answerDTO);
    }

    private void loadQuestion(QuestionDTO question, AnswerDTO answer) {
        tvQuestion.setText(question.getQuestion());
//        String options = question.getOptions();
        ArrayList<OptionDTO> listOption =question.getOptions();
        for (OptionDTO option : listOption) {
            LinearLayout linearLayout = genlayoutInputNumber(option.getOption());
            lnContent.addView(linearLayout);
        }
    }

    private LinearLayout genlayoutInputNumber(String text) {
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        int textSize = mActivity.getResources().getDimensionPixelOffset(R.dimen.ts_17);
        int padding = mActivity.getResources().getDimensionPixelOffset(R.dimen.padding_small);

        LinearLayout linearLayout = new LinearLayout(mActivity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getLayoutParams(0, margin, 0, margin / 2));

        TextView textView = new TextView(mActivity);
        textView.setLayoutParams(getLayoutParamsWeghtWidth(0, 0, margin, 0,1.5f));
        textView.setText(text);
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        linearLayout.addView(textView);

        EditText editText = new EditText(mActivity);
        editText.setLayoutParams(getLayoutParamsWeghtWidth(margin, 0, 0, 0,2f));
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setBackgroundResource(R.drawable.bg_edit_text);
        editText.setTag(text);
        editText.setPadding(padding, padding, padding, padding);
        linearLayout.addView(editText);

        return linearLayout;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
}
