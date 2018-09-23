package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_input_text;
    }

    @Override
    protected void createView(View view) {
        activity.setiNext(this);
        activity.setiPrevious(this);
        loadQuestion(questionDTO, answerDTO);
        if (questionDTO.getType() == Constants.TYPE_TEXT_INPUT_LIST) {
            rcvText.setVisibility(View.VISIBLE);
            setRcvText();
            edAnswer.setSingleLine();
            edAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
            edAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        InputMethodManager imm = (InputMethodManager) v.getContext()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        //add answer to list
                        listText.add(edAnswer.getText().toString());
                        if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
                            setMember(edAnswer.getText().toString(), listText.indexOf(edAnswer
                                    .getText().toString()));
                        }
                        adapter.notifyDataSetChanged();
                        edAnswer.setText(null);
                        return true;
                    }
                    return false;
                }
            });
        } else {
            rcvText.setVisibility(View.GONE);
        }
    }

    private void setMember(String name, int index) {
        switch (questionDTO.getId()) {
            case Constants.QUESTION_Q1:
                PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
                peopleDetailDTO.setIDHO(Constants.mStaticObject.getIdHo());
                peopleDetailDTO.setHOSO(Constants.mStaticObject.getPeopleDTO().getHOSO());
                peopleDetailDTO.setQ1(name);
                peopleDetailDTO.setSTT(index);
                peopleDetailDTO.setChuho(index == 0 ? 2 : 1);
                peopleDetailDTO.setID(Constants.mStaticObject.getIdHo() + index);

                Constants.mStaticObject.getPeopleDetailDTO().add(peopleDetailDTO);
                break;

            default:
                break;
        }
    }



    public boolean loadQuestion(QuestionDTO question, AnswerDTO answer) {
        if (question == null) return false;
        tvQuestion.setText(question.getQuestion());
        if (answer != null && !StringUtils.isEmpty((String) answer.getAnswer())) {
            edAnswer.setText((String) answer.getAnswer());
        } else {
            edAnswer.setHint(question.getSurvey() == null ? question.getPlaceHolder() : question
                    .getSurvey());
        }
        return true;
    }

    @Override
    public boolean save(QuestionDTO questionDTO, AnswerDTO answerDTO, Object answer) {
        if (answerDTO == null) {
            answerDTO = new AnswerDTO();
        }
        answerDTO.setQuestionID(questionDTO.getId());
        answerDTO.setAnswer(answer);
        AnswerDAO.getInstance().insert(answerDTO);
        saveAnswerToSurvey(questionDTO, answerDTO);
        return false;
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getId()) {
            case Constants.QUESTION_Q9:
                ArrayList<GroupDrawer> list = new ArrayList<>();
                list.add(new GroupDrawer("Nguyen van a", null));
                list.add(new GroupDrawer("nguyen van b", null));
                activity.setList(list);
                return true;
            case Constants.QUESTION_Q1:
                final boolean[] isValidate = {true};
                DialogUtils.showAlert(activity, R.string.txt_another_else, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                isValidate[0] = false;
                            }
                        });
                return isValidate[0];
            default:
                return true;
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
        if (validateQuaetion(questionDTO, answerDTO)) {
            if (currentIndex < getListQuestion().size() - 1) {
                currentIndex++;
                replcaeFragmentByType(getListQuestion().get(currentIndex), true);
            }
        } else {
            Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void previuos() {
        if (currentIndex > 0) {
            currentIndex--;
            replcaeFragmentByType(getListQuestion().get(currentIndex), false);
        }
    }
}
