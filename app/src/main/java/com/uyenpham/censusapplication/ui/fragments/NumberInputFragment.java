package com.uyenpham.censusapplication.ui.fragments;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.RadioButtonAdapter;
import com.uyenpham.censusapplication.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;

public class NumberInputFragment extends BaseTypeFragment {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;


    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter radioButtonAdapter;
    private int posMember;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_number_input;
    }

    @Override
    protected void createView(View view) {
        loadQuestion(questionDTO);
    }
    @Override
    public boolean loadQuestion(QuestionDTO question) {
        answerDTO = AnswerDAO.getInstance().findById(question.getId(),Constants.mStaticObject.getIdHo());
        tvQuestion.setText(question.getQuestion());
        ArrayList<OptionDTO> listOption =question.getOptions();
        for (OptionDTO option : listOption) {
            LinearLayout linearLayout = genlayoutInputNumber(option.getOption(), answerDTO== null?"":String.valueOf(answerDTO.getAnswerInt()));
            lnContent.addView(linearLayout);
        }
        return true;
    }

    private LinearLayout genlayoutInputNumber(String option, String answer) {
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        int textSize = mActivity.getResources().getDimensionPixelOffset(R.dimen.ts_17);
        int padding = mActivity.getResources().getDimensionPixelOffset(R.dimen.padding_small);

        LinearLayout linearLayout = new LinearLayout(mActivity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getLayoutParams(0, margin, 0, margin / 2));

        TextView textView = new TextView(mActivity);
        textView.setLayoutParams(getLayoutParamsWeghtWidth(0, 0, margin, 0,1.5f));
        textView.setText(option);
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        linearLayout.addView(textView);

        EditText editText = new EditText(mActivity);
        editText.setLayoutParams(getLayoutParamsWeghtWidth(margin, 0, 0, 0,2f));
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setBackgroundResource(R.drawable.bg_edit_text);
        editText.setTag(option);
        editText.setText(answer);
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

    public void setPosMember(int posMember) {
        this.posMember = posMember;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        return false;
    }
}
