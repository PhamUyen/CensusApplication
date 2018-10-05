package com.uyenpham.censusapplication.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.fragments.MultiSelectionFragment;
import com.uyenpham.censusapplication.ui.fragments.NumberInputFragment;
import com.uyenpham.censusapplication.ui.fragments.SingleSelectFragment;
import com.uyenpham.censusapplication.ui.fragments.TypeTextInputFragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
    public static String readFromAsset(Context context, String fileName) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            Log.e("Exception", "Fail", e);
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                Log.e("Exception", "Fail", e2);
            }
        }
        return returnString.toString();
    }
    public static void replcaeFragmentByType(QuestionDTO questionDTO, boolean isNext, ArrayList<QuestionDTO>listQuestion, int content, FragmentManager fragmentManager, int posMember) {
        int type = questionDTO.getType();

        switch (type) {
            case Constants.TYPE_TEXT_INPUT:
            case Constants.TYPE_TEXT_INPUT_LIST:
                TypeTextInputFragment fragment = new TypeTextInputFragment();
                fragment.setQuestionDTO(questionDTO);
                fragment.setAnswerDTO(null);
                fragment.setListQuestion(listQuestion);
                fragment.setContentID(content);
                fragment.setPosMember(posMember);
                replaceAnimation(fragment, isNext, content,fragmentManager);
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
                singleSelectFragment.setContentID(content);
                singleSelectFragment.setPosMember(posMember);
                replaceAnimation(singleSelectFragment, isNext, content,fragmentManager);
                break;
            case Constants.TYPE_NUMBER_INPUT:
            case Constants.TYPE_DATE_INPUT:
                NumberInputFragment numberInputFragment = new NumberInputFragment();
                numberInputFragment.setQuestionDTO(questionDTO);
                numberInputFragment.setAnswerDTO(null);
//                numberInputFragment.setContentID(content);
                replaceAnimation(numberInputFragment, isNext, content,fragmentManager);
                break;
            case Constants.TYPE_MULTI_SELECT:
            case Constants.TYPE_MULTI_SELECT_INPUT:
                MultiSelectionFragment multiSelectionFragment = new MultiSelectionFragment();
                multiSelectionFragment.setQuestionDTO(questionDTO);
                multiSelectionFragment.setAnswerDTO(null);
                multiSelectionFragment.setContentID(content);
                replaceAnimation(multiSelectionFragment, isNext, content,fragmentManager);
                break;
            default:
                break;
        }
    }

    public static  void replaceAnimation(Fragment fragment, boolean isNext, int content, FragmentManager fragmentManager) {
        if (isNext) {
            FragmentHelper.replaceFagmentFromRight(fragment, fragmentManager,
                    content);
        } else {
            FragmentHelper.replaceFagmentFromLeft(fragment, fragmentManager,
                    content);
        }
    }
}
