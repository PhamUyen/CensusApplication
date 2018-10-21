package com.uyenpham.censusapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.fragments.DateInputFragment;
import com.uyenpham.censusapplication.ui.fragments.MultiSelectionFragment;
import com.uyenpham.censusapplication.ui.fragments.NumberInputFragment;
import com.uyenpham.censusapplication.ui.fragments.ProvinceDistrictFragment;
import com.uyenpham.censusapplication.ui.fragments.SingleSelectFragment;
import com.uyenpham.censusapplication.ui.fragments.TypeTextInputFragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.uyenpham.censusapplication.utils.Constants.TYPE_SINGLE_SELECT_IN_LIST;

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
    public static void replcaeFragmentByType(QuestionDTO questionDTO, boolean isNext, ArrayList<QuestionDTO>listQuestion, FragmentManager fragmentManager, int posMember) {
        int type = questionDTO.getType();

        switch (type) {
            case Constants.TYPE_TEXT_INPUT:
            case Constants.TYPE_TEXT_INPUT_LIST:
                TypeTextInputFragment fragment = new TypeTextInputFragment();
                fragment.setQuestionDTO(questionDTO);
                fragment.setAnswerDTO(null);
                fragment.setListQuestion(listQuestion);
                fragment.setPosMember(posMember);
                replaceAnimation(fragment, isNext,fragmentManager);
                break;
            case Constants.TYPE_MULTI_TEXT_INPUT:
                ProvinceDistrictFragment provinceDistrictFragment = new ProvinceDistrictFragment();
                provinceDistrictFragment.setQuestionDTO(questionDTO);
                provinceDistrictFragment.setListQuestion(listQuestion);
                provinceDistrictFragment.setPosMember(posMember);
                replaceAnimation(provinceDistrictFragment, isNext,fragmentManager);
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
                singleSelectFragment.setPosMember(posMember);
                replaceAnimation(singleSelectFragment, isNext,fragmentManager);
                break;
            case Constants.TYPE_NUMBER_INPUT:
                NumberInputFragment numberInputFragment = new NumberInputFragment();
                numberInputFragment.setQuestionDTO(questionDTO);
                numberInputFragment.setListQuestion(listQuestion);
                numberInputFragment.setPosMember(posMember);
                replaceAnimation(numberInputFragment, isNext,fragmentManager);
                break;
            case Constants.TYPE_DATE_INPUT:
                DateInputFragment dateInputFragment = new DateInputFragment();
                dateInputFragment.setQuestionDTO(questionDTO);
                dateInputFragment.setListQuestion(listQuestion);
                dateInputFragment.setPosMember(posMember);
                replaceAnimation(dateInputFragment, isNext,fragmentManager);
                break;
            case Constants.TYPE_MULTI_SELECT:
            case Constants.TYPE_MULTI_SELECT_INPUT:
            case TYPE_SINGLE_SELECT_IN_LIST:
                MultiSelectionFragment multiSelectionFragment = new MultiSelectionFragment();
                multiSelectionFragment.setQuestionDTO(questionDTO);
                multiSelectionFragment.setListQuestion(listQuestion);
                multiSelectionFragment.setPosMember(posMember);
                replaceAnimation(multiSelectionFragment, isNext,fragmentManager);
                break;
            default:
                break;
        }
    }

    public static  void replaceAnimation(Fragment fragment, boolean isNext, FragmentManager fragmentManager) {
        if (isNext) {
            FragmentHelper.replaceFagmentFromRight(fragment, fragmentManager);
        } else {
            FragmentHelper.replaceFagmentFromLeft(fragment, fragmentManager);
        }
    }

    public static void hideKeyboard(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm!= null){
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
