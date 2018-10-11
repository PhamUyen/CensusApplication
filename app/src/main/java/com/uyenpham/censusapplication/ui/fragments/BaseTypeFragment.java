package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.view.View;

import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.db.FamilyDAO;
import com.uyenpham.censusapplication.db.PeopleDAO;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.utils.Constants;

import java.util.ArrayList;

public abstract class BaseTypeFragment extends BaseFragment {
    private ArrayList<QuestionDTO> listQuestion;
    public SurveyActivity activity;
    private int contentID;
    private  int posMember;
    public MemberDTO memberDTO;
    public WomanDTO womanDTO;
    public DeadDTO deadDTO;
    public HouseDTO houseDTO;
    private QuestionDTO questionDTO;
    public abstract boolean loadQuestion(QuestionDTO questionDTO);

    @Override
    protected void createView(View view) {
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
    }

    private MemberDTO getMemberById(String id){
        for(MemberDTO member : Constants.mStaticObject.getMemberDTO()){
            if(member.getmIDTV().equalsIgnoreCase(id)){
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


    public  void save(AnswerDTO answerDTO, QuestionDTO questionDTO){
        if(AnswerDAO.getInstance().checkIsExistDB(answerDTO.getId())){
            AnswerDAO.getInstance().update(answerDTO);
        }else {
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

    protected void saveAnswerToSurvey(QuestionDTO question, AnswerDTO answer) {
        switch (question.getSurvey()) {
            case Constants.SURVEY_WOMAN:
//                Constants.mStaticObject.getWomanDTO().set(question.getName(), answer.getAnswerString());
//                WomanDAO.getInstance().insert(Constants.mStaticObject.getWomanDTO());
                break;
            case Constants.SURVEY_DEAD:
//                DeadDAO.getInstance().insert(Constants.mStaticObject.getDeadDTO());
                break;
            case Constants.SURVEY_PEOPLE:
                Constants.mStaticObject.getPeopleDTO().set(question.getName(), answer.getAnswerString());
//                Constants.mStaticObject.getPeopleDetailDTO().set(question.getName(), answer.getAnswerString());
                PeopleDAO.getInstance().insert(Constants.mStaticObject.getPeopleDTO());
                break;
            case Constants.SURVEY_HOUSE:
                break;
            case Constants.SURVEY_MEMBER:
                break;
            case Constants.SURVEY_FAMILY:
                Constants.mStaticObject.getFamilyDTO().set(question.getName(), answer.getAnswerString());
                Constants.mStaticObject.getFamilyDetailDTO().set(question.getName(), answer.getAnswerString());
                FamilyDAO.getInstance().insert(Constants.mStaticObject.getFamilyDTO());
                break;
            default:
                break;
        }
    }
}
