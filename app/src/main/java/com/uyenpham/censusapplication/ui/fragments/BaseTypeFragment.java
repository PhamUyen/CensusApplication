package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;
import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.previousIndex;

public abstract class BaseTypeFragment extends BaseFragment {
    private ArrayList<QuestionDTO> listQuestion;
    public SurveyActivity activity;
    private int contentID;
    private int posMember;
    public MemberDTO memberDTO;
    public WomanDTO womanDTO;
    public DeadDTO deadDTO;
    public HouseDTO houseDTO;
    public FamilyDetailDTO familyDetailDTO;
    public QuestionDTO questionDTO;

    public abstract boolean loadQuestion(QuestionDTO questionDTO);

    @Override
    protected void createView(View view) {
        familyDetailDTO = Constants.mStaticObject.getFamilyDetailDTO();
        if (questionDTO.getSurvey().equals(Constants.SURVEY_MEMBER)) {
            memberDTO = Constants.mStaticObject.getMemberDTO().get(posMember);
        } else if (Constants.SURVEY_WOMAN.equals(questionDTO.getSurvey())) {
            womanDTO = Constants.mStaticObject.getWomanDTO().get(posMember);
            memberDTO = getMemberById(womanDTO.getIDTV());
        } else if (Constants.SURVEY_DEAD.equals(questionDTO.getSurvey())) {
            deadDTO = Constants.mStaticObject.getDeadDTO().get(posMember);
            memberDTO = getMemberById(deadDTO.getmIDCHET());
        } else if (Constants.SURVEY_HOUSE.equals(questionDTO.getSurvey())) {
            houseDTO = Constants.mStaticObject.getHouseDTO();
        }
        initData();
    }

    public abstract void initData();

    private MemberDTO getMemberById(String id) {
        for (MemberDTO member : Constants.mStaticObject.getMemberDTO()) {
            if (member.getmIDTV().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }


    public void save(AnswerDTO answerDTO, QuestionDTO questionDTO) {
        if (AnswerDAO.getInstance().checkIsExistDB(answerDTO.getId())) {
            AnswerDAO.getInstance().update(answerDTO);
        } else {
            AnswerDAO.getInstance().insert(answerDTO);
        }
    }

    public abstract WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SurveyActivity) context;
    }


    public void setListQuestion(ArrayList<QuestionDTO> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public void setPosMember(int posMember) {
        this.posMember = posMember;
    }

    public int getPosMember() {
        return posMember;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public ArrayList<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    protected int getIndexOfQuestion(QuestionDTO question) {
        for (QuestionDTO quest : listQuestion) {
            if (quest.getId().equals(question.getId())) {
                return listQuestion.indexOf(quest);
            }
        }
        return -1;
    }

    protected QuestionDTO getQuestionByIndex(int index) {
        if (index != -1 && index < listQuestion.size()) {
            return listQuestion.get(index);
        }
        return null;
    }

    public void setContentQuestion(TextView tvQuestion) {
        String questionContent = questionDTO.getQuestion();
        if (questionDTO.getQuestion().contains("%1$s")) {
            switch (questionDTO.getSurvey()) {
                case Constants.SURVEY_MEMBER:
                    questionContent = String.format(questionContent, memberDTO.getmC01());
                    break;
                case Constants.SURVEY_DEAD:
                    questionContent = String.format(questionContent, deadDTO.getmC43());
                    break;
            }
        }
        tvQuestion.setText(questionDTO.getName() + "." + questionContent);
    }

    public void nextMember() {
        switch (questionDTO.getSurvey()) {
            case Constants.SURVEY_MEMBER:
                if (posMember < Constants.mStaticObject.getMemberDTO().size() - 1) {
                    posMember++;
                    setPosMember(posMember);
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getPeopleDetailDTO().get(posMember).getQ1A());
                } else {
                    if (Constants.mStaticObject.getFamilyDTO().getLoaiphieu() == Constants
                            .SHORT_FORM) {
                        if (currentIndex < getListQuestion().size()) {
                            activity.makeListQuestion();
                            currentIndex = 25;
                            previousIndex =24;
                            activity.getNavigationBar().setTitle(getString(R.string
                                    .txt_interview_detail));
                            Utils.replcaeFragmentByType(activity.getListQuestionMain().get
                                            (currentIndex), true,
                                    activity.getListQuestionMain(), activity.mFragmentManager, -1);
                        }
                    } else {
                        if (!Constants.mStaticObject.getWomanDTO().isEmpty()) {
                            posMember = 0;
                            setPosMember(posMember);
                            activity.survey = Constants.SURVEY_WOMAN;
                            activity.isMember = true;
                            activity.setListPeople(posMember);
                            activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                    .getWomanDTO().get(0).getTenTV());
                        } else {
                            if (!Constants.mStaticObject.getDeadDTO().isEmpty()) {
                                activity.survey = Constants.SURVEY_DEAD;
                                posMember = 0;
                                setPosMember(posMember);
                                activity.isMember = true;
                                activity.setListPeople(posMember);
                                activity.getNavigationBar().setTitle("1 - " + Constants
                                        .mStaticObject
                                        .getDeadDTO().get(0).getmC43());
                            } else {
                                if (currentIndex < getListQuestion().size()) {
                                    activity.makeListQuestion();
                                    currentIndex = 25;
                                    previousIndex =24;
                                    activity.getNavigationBar().setTitle(getString(R.string
                                            .txt_interview_detail));
                                    Utils.replcaeFragmentByType(activity.getListQuestionMain()
                                                    .get(currentIndex), true,
                                            activity.getListQuestionMain(), activity
                                                    .mFragmentManager, -1);
                                }
                            }
                        }

                    }
                }
                break;
            case Constants.SURVEY_WOMAN:
                if (posMember < Constants.mStaticObject.getWomanDTO().size() - 1) {
                    posMember++;
                    setPosMember(posMember);
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getWomanDTO().get(posMember).getTenTV());
                } else {
                    if (Constants.mStaticObject.getDeadDTO().size() > 0) {
                        posMember = 0;
                        setPosMember(posMember);
                        activity.survey = Constants.SURVEY_DEAD;
                        activity.isMember = true;
                        activity.setListPeople(posMember);
                        activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                .getDeadDTO().get(0).getmC43());
                    } else {
                        if (currentIndex < getListQuestion().size()) {
                            activity.makeListQuestion();
                            currentIndex = 25;
                            previousIndex =24;
                            activity.getNavigationBar().setTitle(getString(R.string
                                    .txt_interview_detail));
                            Utils.replcaeFragmentByType(activity.getListQuestionMain().get
                                            (currentIndex), true,
                                    activity.getListQuestionMain(), activity.mFragmentManager, -1);
                        }
                    }
                }
                break;
            case Constants.SURVEY_DEAD:
                if (posMember < Constants.mStaticObject.getDeadDTO().size() - 1) {
                    posMember++;
                    setPosMember(posMember);
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getDeadDTO().get(posMember).getmC43());
                } else {
                    activity.makeListQuestion();
                    currentIndex = 25;
                    activity.getNavigationBar().setTitle(getString(R.string.txt_interview_detail));
                    Utils.replcaeFragmentByType(activity.getListQuestionMain().get(currentIndex),
                            true,
                            activity.getListQuestionMain(), activity.mFragmentManager, -1);
                }
                break;
            default:
                break;
        }

    }

    protected void saveAnswerToSurvey(QuestionDTO question, int postMember) {
        switch (question.getSurvey()) {
            case Constants.SURVEY_WOMAN:
                Constants.mStaticObject.getWomanDTO().set(postMember, womanDTO);
                break;
            case Constants.SURVEY_DEAD:
                Constants.mStaticObject.getDeadDTO().set(postMember, deadDTO);
                break;
            case Constants.SURVEY_PEOPLE:
                break;
            case Constants.SURVEY_HOUSE:
                Constants.mStaticObject.setHouseDTO(houseDTO);
                break;
            case Constants.SURVEY_MEMBER:
                Constants.mStaticObject.getMemberDTO().set(postMember, memberDTO);
                break;
            case Constants.SURVEY_FAMILY:
                Constants.mStaticObject.setFamilyDetailDTO(familyDetailDTO);
                break;
            default:
                break;
        }
    }
    public PeopleDetailDTO getPeopleByName(String name){
        for(PeopleDetailDTO peopleDetailDTO : Constants.mStaticObject.getPeopleDetailDTO()){
            if(peopleDetailDTO.getQ1A().equalsIgnoreCase(name)){
                return peopleDetailDTO;
            }
        }
        return null;
    }
    public int getIndexMemberByName(String name){
        List<MemberDTO> list =Constants.mStaticObject.getMemberDTO();
        for(int i=0; i< list.size(); i++){
            MemberDTO member =  Constants.mStaticObject.getMemberDTO().get(i);
            if(member.getmC01().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
}
