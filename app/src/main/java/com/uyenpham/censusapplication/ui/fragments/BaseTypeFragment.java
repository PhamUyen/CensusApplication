package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.db.DeadDAO;
import com.uyenpham.censusapplication.db.FamilyDAO;
import com.uyenpham.censusapplication.db.PeopleDAO;
import com.uyenpham.censusapplication.db.WomanDAO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.FragmentHelper;

import java.util.ArrayList;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.ID_SURVEY_CONTENT;

public abstract class BaseTypeFragment extends BaseFragment {
    private ArrayList<QuestionDTO> listQuestion;
    public SurveyActivity activity;

    public abstract boolean loadQuestion(QuestionDTO questionDTO);

    public  void save(AnswerDTO answerDTO, QuestionDTO questionDTO){
        if(AnswerDAO.getInstance().checkIsExistDB(answerDTO.getId())){
            AnswerDAO.getInstance().update(answerDTO);
        }else {
            AnswerDAO.getInstance().insert(answerDTO);
        }
        saveAnswerToSurvey(questionDTO, answerDTO);
    }

    public abstract boolean validateQuaetion(QuestionDTO question, AnswerDTO answer);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SurveyActivity) context;
    }

    protected void replcaeFragmentByType(QuestionDTO questionDTO, boolean isNext) {
        int type = questionDTO.getType();

        switch (type) {
            case Constants.TYPE_TEXT_INPUT:
            case Constants.TYPE_TEXT_INPUT_LIST:
                TypeTextInputFragment fragment = new TypeTextInputFragment();
                fragment.setQuestionDTO(questionDTO);
                fragment.setAnswerDTO(null);
                fragment.setListQuestion(listQuestion);
                replaceAnimation(fragment, isNext);
                break;
            case Constants.TYPE_SINGLE_SELECT:
            case Constants.TYPE_SELECT_INPUT:
            case Constants.TYPE_SINGLE_SELECT_LIST:
            case Constants.TYPE_SINGLE_SELECT_AUTO:
            case Constants.TYPE_MIX:
                SingleSelectFragment singleSelectFragment = new SingleSelectFragment();
                singleSelectFragment.setQuestionDTO(questionDTO);
                singleSelectFragment.setAnswerDTO(null);
                singleSelectFragment.setListQuestion(listQuestion);
                replaceAnimation(singleSelectFragment, isNext);
                break;
            case Constants.TYPE_NUMBER_INPUT:
                NumberInputFragment numberInputFragment = new NumberInputFragment();
                numberInputFragment.setQuestionDTO(questionDTO);
                numberInputFragment.setAnswerDTO(null);
                replaceAnimation(numberInputFragment, isNext);
                break;
            case Constants.TYPE_MULTI_SELECT:
            case Constants.TYPE_MULTI_SELECT_INPUT:
                MultiSelectionFragment multiSelectionFragment = new MultiSelectionFragment();
                multiSelectionFragment.setQuestionDTO(questionDTO);
                multiSelectionFragment.setAnswerDTO(null);
                replaceAnimation(multiSelectionFragment, isNext);
                break;
            default:
                break;
        }
    }

    protected void replaceAnimation(Fragment fragment, boolean isNext) {
        if (isNext) {
            FragmentHelper.replaceFagmentFromRight(fragment, activity.mFragmentManager,
                    ID_SURVEY_CONTENT);
        } else {
            FragmentHelper.replaceFagmentFromLeft(fragment, activity.mFragmentManager,
                    ID_SURVEY_CONTENT);
        }
    }

    public void setListQuestion(ArrayList<QuestionDTO> listQuestion) {
        this.listQuestion = listQuestion;
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
                Constants.mStaticObject.getWomanDTO().set(question.getName(), answer.getAnswerString());
                WomanDAO.getInstance().insert(Constants.mStaticObject.getWomanDTO());
                break;
            case Constants.SURVEY_DEAD:
                DeadDAO.getInstance().insert(Constants.mStaticObject.getDeadDTO());
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
