package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.FragmentHelper;

import java.util.ArrayList;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.ID_SURVEY_CONTENT;

public abstract class BaseTypeFragment extends BaseFragment{
    private ArrayList<QuestionDTO> listQuestion;
    public SurveyActivity activity;
    public abstract boolean loadQuestion(QuestionDTO questionDTO, AnswerDTO answerDTO);
    public abstract boolean save(QuestionDTO questionDTO, AnswerDTO answerDTO);
    public abstract boolean validateQuaetion(QuestionDTO question, AnswerDTO answer);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (SurveyActivity)context;
    }

    protected void replcaeFragmentByType(QuestionDTO questionDTO, boolean isNext) {
        int type = questionDTO.getType();

        switch (type) {
            case Constants.TYPE_TEXT_INPUT:
            case Constants.TYPE_TEXT_INPUT_LIST:
                TypeTextInputFragment fragment = new TypeTextInputFragment();
                fragment.setQuestionDTO(questionDTO);
                fragment.setAnswerDTO(null);
                replaceAnimation(fragment, isNext);
                break;
            case Constants.TYPE_SINGLE_SELECT:
            case Constants.TYPE_SELECT_INPUT:
            case Constants.TYPE_SINGLE_SELECT_LIST:
            case Constants.TYPE_SINGLE_SELECT_AUTO:
                SingleSelectFragment singleSelectFragment = new SingleSelectFragment();
                singleSelectFragment.setQuestionDTO(questionDTO);
                singleSelectFragment.setAnswerDTO(null);
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
    protected QuestionDTO getQuestionByIndex(int index){
        if( index!= -1 && index< listQuestion.size()){
            return listQuestion.get(index);
        }
        return null;
    }
}
