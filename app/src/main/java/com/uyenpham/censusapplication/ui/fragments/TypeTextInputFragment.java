package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.TextAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class TypeTextInputFragment extends BaseTypeFragment implements INextQuestion,
        IPreviousQuestion {
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
    private boolean isValidate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_input_text;
    }

    @Override
    protected void createView(View view) {
        activity.setiNext(this);
        activity.setiPrevious(this);
        loadQuestion(questionDTO);
        edAnswer.setSingleLine();
        if (questionDTO.getType() == Constants.TYPE_TEXT_INPUT_LIST) {
            rcvText.setVisibility(View.VISIBLE);
            setRcvText();
        } else {
            rcvText.setVisibility(View.GONE);
        }
            edAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
            edAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        //add answer to list
                        if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
                            listText.add(edAnswer.getText().toString());
                            adapter.notifyDataSetChanged();
                            edAnswer.setText(null);
                        }

                        setMember(edAnswer.getText().toString());
                        return true;
                    }
                    return false;
                }
            });
    }

    private void setMember(String name) {
        answerDTO.setAnswerString(name);
        switch (questionDTO.getSurvey()) {
            case Constants.SURVEY_PEOPLE:
                int index = listText.indexOf(name);
                PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
                peopleDetailDTO.setIDHO(Constants.mStaticObject.getIdHo());
                peopleDetailDTO.setHOSO(Constants.mStaticObject.getPeopleDTO().getHOSO());
                peopleDetailDTO.setQ1(name);
                peopleDetailDTO.setSTT(index);
                peopleDetailDTO.setChuho(index == 0 ? 2 : 1);
                peopleDetailDTO.setID(Constants.mStaticObject.getIdHo() + index);

                Constants.mStaticObject.getPeopleDetailDTO().add(peopleDetailDTO);
                break;

            case Constants.SURVEY_FAMILY:
                answerDTO.setAnswerString(name);
                Constants.mStaticObject.getFamilyDTO().set(questionDTO.getId(),name);
                Constants.mStaticObject.getFamilyDetailDTO().set(questionDTO.getId(),name);
                break;
            default:
                break;
        }
    }



    public boolean loadQuestion(QuestionDTO question) {
        answerDTO = AnswerDAO.getInstance().findById(questionDTO.getId(),Constants.mStaticObject.getIdHo());
        if (question == null) return false;
        tvQuestion.setText(question.getQuestion());
        if (answerDTO != null && !StringUtils.isEmpty(answerDTO.getAnswerString())) {
            edAnswer.setText( answerDTO.getAnswerString());
        } else {
            edAnswer.setHint(question.getPlaceHolder());
        }
        return true;
    }


    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getId()) {
            case Constants.QUESTION_Q1:
                return listText.size()>0;
            case Constants.mDIENTHOAI:
                String regexStr = "^[0-9]*$";
               return  (answer.getAnswerString().matches(regexStr) && 9<answer.getAnswerString().length() && answer.getAnswerString().length()<12);
            default:
                if(answer.getAnswerString() == null){
                    return false;
                }else {
                    return true;
                }
        }
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    private void setRcvText() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rcvText.setLayoutManager(manager);
        listText = new ArrayList<>();
        adapter = new TextAdapter(listText);
        rcvText.setAdapter(adapter);
    }

    @Override
    public void next() {
        changeQuestion();
    }
    private void nextFragment(){
        save(answerDTO, questionDTO);
        if (currentIndex < getListQuestion().size() - 1) {
            currentIndex++;
            replcaeFragmentByType(getListQuestion().get(currentIndex), true);
        }
    }

    @Override
    public void previuos() {
//        save();
        if (currentIndex > 0) {
            currentIndex--;
            replcaeFragmentByType(getListQuestion().get(currentIndex), false);
        }
    }

    private void changeQuestion(){
        if(questionDTO.getId().equals(Constants.QUESTION_Q1)){
            if(validateQuaetion(questionDTO,answerDTO)){
                DialogUtils.showErrorAlert2Option(activity, R.string.txt_another_else, R.string.txt_yes, R.string.txt_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                isValidate = false;                      }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                nextFragment();
                            }
                        });
            }else {
                DialogUtils.showAlert(activity,R.string.txt_empty);
            }
        }else {
            if (validateQuaetion(questionDTO, answerDTO)) {
                    nextFragment();
            } else {
                DialogUtils.showAlert(activity,R.string.txt_empty);
            }
        }
    }
}
