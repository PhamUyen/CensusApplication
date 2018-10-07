package com.uyenpham.censusapplication.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.uyenpham.censusapplication.R;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.ID_SURVEY_CONTENT;

/**
 * Define fragment manager
 *
 */
public class FragmentHelper {
    /**
     * Repale fragment with contentTab and add fragment to backstack
     *
     * @param fragment        This is fragment need replace in layout contentTab
     * @param fragmentManager This is fragment manager
     */
    public static void replaceFragmentAddToBackStack(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentTab and add fragment to backstack
     *
     * @param fragment        This is fragment need replace in layout contentTab
     * @param fragmentManager This is fragment manager
     */
    public static void replaceFragmentAddToBackStackByTag(Fragment fragment, FragmentManager fragmentManager, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentTab
     *
     * @param fragment        This is fragment need replace in layout contentTab
     * @param fragmentManager This is fragment manager
     */
    public static void replaceFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentMenu
     *
     * @param fragment        This is fragment need replace in layout contentMenu
     * @param fragmentManager This is fragment manager
     */
    public static void replaceMenuFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentMenu and add fragment to backstack
     *
     * @param fragment        This is fragment need replace in layout contentMenu
     * @param fragmentManager This is fragment manager
     */
    public static void replaceMenuAddToBackStack(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void replaceFagmentFromLeft(Fragment fragment, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_to_right);
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.commit();
    }
    public static void replaceFagmentFromRight(Fragment fragment, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_to_left);
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.commit();
    }
    public static void replaceFagmentFromRightAddToBackstack(Fragment fragment, FragmentManager fragmentManager){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_to_left);
        fragmentTransaction.replace(ID_SURVEY_CONTENT, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public static void removeFragment(Fragment fragment, FragmentManager fragmentManager){
        fragmentManager.beginTransaction().remove(fragment).commit();
    }
}
