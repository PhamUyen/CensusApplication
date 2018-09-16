package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.TextAdapter;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;

public class TypeTextInputFragment extends BaseFragment {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.ed_answer)
    EditText edAnswer;
    @Bind(R.id.rcv_text)
    RecyclerView rcvText;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<String> listText;
    private TextAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_input_text;
    }

    @Override
    protected void createView(View view) {
       loadQuestion(questionDTO, answerDTO);
       if(questionDTO.getType() == Constants.TYPE_TEXT_INPUT_LIST){
           rcvText.setVisibility(View.VISIBLE);

           edAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
           edAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
               @Override
               public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                   if (actionId == EditorInfo.IME_ACTION_DONE) {
                       InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                       //add answer to list
                       listText.add(edAnswer.getText().toString());
                       adapter.notifyDataSetChanged();
                       return true;
                   }
                   return false;
               }
           });
       }else {
           rcvText.setVisibility(View.GONE);
       }
    }
    public void loadQuestion(QuestionDTO question, AnswerDTO answer){
        if(question == null) return;
        tvQuestion.setText(question.getQuestion());
        if(answer != null && !StringUtils.isEmpty(answer.getAnswer())){
            edAnswer.setText(answer.getAnswer());
        }else {
            edAnswer.setHint(question.getAnswer()== null ? question.getPlaceHolder() : question.getAnswer());
        }
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
    private void setRcvText(){
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rcvText.setLayoutManager(manager);
        listText = new ArrayList<>();
        adapter = new TextAdapter(listText);
        rcvText.setAdapter(adapter);
    }
}