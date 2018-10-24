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
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.adapters.TextAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IClearListener;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.StringUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;
import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.previousIndex;

public class TypeTextInputFragment extends BaseTypeFragment implements INextQuestion,
        IPreviousQuestion, IClearListener {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.ed_answer)
    EditText edAnswer;
    @Bind(R.id.rcv_text)
    RecyclerView rcvText;

//    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<String> listText;
    private TextAdapter adapter;
    private boolean isValidate;
    private int posMember;
    private ArrayList<PeopleDetailDTO> listNewPeople;
    private ArrayList<MemberDTO> listNewMem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_input_text;
    }

    @Override
    public void initData() {
        activity.setiNext(this);
        activity.setiPrevious(this);
        edAnswer.setSingleLine();
        listNewMem = new ArrayList<>();
        listNewPeople = new ArrayList<>();
        listText = new ArrayList<>();
        posMember = getPosMember();
        loadQuestion(questionDTO);
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
                        if(StringUtils.isEmpty(edAnswer.getText().toString())){
                            DialogUtils.showAlert(activity, R.string.txt_name_empty);
                        }else {
                            listText.add(edAnswer.getText().toString());
                            adapter.notifyDataSetChanged();
                            setListPeopleDetail(edAnswer.getText().toString());
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }
    private void setListPeopleDetail(String name){
        int stt = listText.indexOf(name)+1;
        PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
        peopleDetailDTO.setmIDHO(Constants.mStaticObject.getIdHo());
        peopleDetailDTO.setmHOSO(Constants.mStaticObject.getPeopleDTO().getHOSO());
        peopleDetailDTO.setQ1A(name);
        peopleDetailDTO.setmQ2A(2);
        peopleDetailDTO.setQ3B(2);
        peopleDetailDTO.setQ4B(2);
        peopleDetailDTO.setSTT(stt);
        peopleDetailDTO.setmID(Constants.mStaticObject.getIdHo() + stt);
        listNewPeople.add(peopleDetailDTO);

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setmC01(name);
        memberDTO1.setmIDTV(Constants.mStaticObject.getIdHo() + stt);
        memberDTO1.setmSTTNKTT(stt);
        listNewMem.add(memberDTO1);
        edAnswer.setText(null);
    }

    private void setMember(String name) {
        switch (questionDTO.getSurvey()) {
            case Constants.SURVEY_FAMILY:
                answerDTO.setAnswerString(name);
                Constants.mStaticObject.getFamilyDTO().set(questionDTO.getId(), name);
                familyDetailDTO.set(questionDTO.getId(), name);
                break;
            case Constants.SURVEY_MEMBER:
                answerDTO.setAnswerString(name);
                String regex = "[A-Za-z ]*";
                if (name.matches(regex)) {
                    if (posMember != -1) {
                        memberDTO.set(questionDTO.getId(), name);
                    }
                } else {
                    DialogUtils.showAlert(activity, R.string.txt_valid_name);
                }
                break;
            default:
                break;
        }
    }


    public boolean loadQuestion(QuestionDTO question) {
        answerDTO = AnswerDAO.getInstance().findById(questionDTO.getId(), Constants.mStaticObject.getIdHo());
        if (question == null) return false;
        setContentQuestion(tvQuestion);
        if (Constants.SURVEY_PEOPLE.equals(question.getSurvey())) {
            PeopleDTO peopleDTO = Constants.mStaticObject.getPeopleDTO();
            if (question.getId().equals(Constants.QUESTION_Q1a)) {
                List<PeopleDetailDTO> list = Constants.mStaticObject.getPeopleDetailDTO();
                for (PeopleDetailDTO people : list) {
                    listText.add(people.getQ1A());
                    if(adapter != null){
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            if (!StringUtils.isEmpty(String.valueOf(peopleDTO.get(question.getId())))) {
                edAnswer.setText(String.valueOf(peopleDTO.get(question.getId())));
            } else {
                edAnswer.setHint(question.getPlaceHolder());
            }
        } else if (Constants.SURVEY_MEMBER.equals(question.getSurvey())) {
            if (question.getId().equals(Constants.QUESTION_C01)) {
                edAnswer.setText(StringUtils.isEmpty(memberDTO.getmC01()) ? "" : memberDTO.getmC01());
            }
        } else if (Constants.SURVEY_FAMILY.equals(question.getSurvey())) {
            edAnswer.setText(String.valueOf(Constants.mStaticObject.getFamilyDTO().get(question.getId())));
        }
        return true;
    }


    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getId()) {
            case Constants.QUESTION_Q1a:
                return listText.size() > 0 ? null : new WarningDTO(getString(R.string.txt_empty), Constants.TYPE_NOTI);
            case Constants.mDIENTHOAI:
                String regexStr = "^[0-9]*$";
                if ((edAnswer.getText().toString().matches(regexStr) && 9 < edAnswer.getText().toString().length() && edAnswer.getText().toString().length() < 12)) {
                    return null;
                } else {
                    return new WarningDTO(getString(R.string.txt_invalid_number_phone), Constants.TYPE_NOTI);
                }
            case Constants.QUESTION_C10A:
                if (Constants.mStaticObject.getFamilyDTO().getMAHUYEN().equals(memberDTO.getmC10B())) {
                    return new WarningDTO(getString(R.string.txt_invalid_district, posMember + 1, memberDTO.getmC01(), memberDTO.getmC10B()), Constants.TYPE_NOTI);
                }
                break;
            case Constants.QUESTION_C27:
                if((memberDTO.getmC16()!= null && memberDTO.getmC16()>=1&&memberDTO.getmC16() <=4)
                        && (memberDTO.getmC23()==1 || memberDTO.getmC24()==1 || memberDTO.getmC25() ==1)){
                }else {
                    currentIndex+=2;
                }
                break;
            case Constants.QUESTION_C43:
                if ((edAnswer.getText().toString().matches("^[0-9]*$") && 9 < edAnswer.getText().toString().length() && edAnswer.getText().toString().length() < 12)) {
                    return null;
                } else {
                    return new WarningDTO(getString(R.string.txt_error_dead_name), Constants.TYPE_NOTI);
                }
            default:
                if (StringUtils.isEmpty(edAnswer.getText().toString())) {
                    return listText.size() > 0 ? null : new WarningDTO(getString(R.string.txt_empty), Constants.TYPE_NOTI);
                } else {
                    return null;
                }
        }
        return null;
    }

//    public void setQuestionDTO(QuestionDTO questionDTO) {
//        this.questionDTO = questionDTO;
//    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    private void setRcvText() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rcvText.setLayoutManager(manager);
        listText = new ArrayList<>();
        adapter = new TextAdapter(listText);
        adapter.setListener(this);
        rcvText.setAdapter(adapter);
    }

    @Override
    public void next() {
        changeQuestion();
    }

    private void nextFragment() {
        Utils.hideKeyboard(activity, edAnswer);
        if (currentIndex < getListQuestion().size() - 1) {
            saveAnswerToSurvey(questionDTO, posMember);
            previousIndex =currentIndex;
            currentIndex++;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true, getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }

    @Override
    public void previuos() {
        if (previousIndex !=-1) {
            Utils.replcaeFragmentByType(getListQuestion().get(previousIndex), false, getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }

    private void changeQuestion() {
        if (questionDTO.getId().equals(Constants.QUESTION_Q1a)) {
            if (validateQuaetion(questionDTO, answerDTO) == null) {
                DialogUtils.showErrorAlert2Option(activity, getString(R.string.txt_another_else), R.string.txt_yes, R.string.txt_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                isValidate = false;
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Constants.mStaticObject.getMemberDTO().addAll(listNewMem);
                                Constants.mStaticObject.getPeopleDetailDTO().addAll(listNewPeople);
                                nextFragment();

                            }
                        });
            } else {
                DialogUtils.showAlert(activity, R.string.txt_empty);
            }
        } else {
            if (validateQuaetion(questionDTO, answerDTO) == null) {
                setMember(edAnswer.getText().toString());
                nextFragment();
            } else {
                DialogUtils.showAlert(activity, validateQuaetion(questionDTO, answerDTO).getMessage());
            }
        }
    }


    @Override
    public void onClear(int pos) {
        listText.remove(pos);
        listNewMem.remove(pos);
        listNewPeople.remove(pos);
        adapter.notifyDataSetChanged();
    }
}
