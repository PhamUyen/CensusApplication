package com.uyenpham.censusapplication.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.uyenpham.censusapplication.R;

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
    public static void replaceFragmentAddToBackStack(Fragment fragment, FragmentManager fragmentManager,int content) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentTab and add fragment to backstack
     *
     * @param fragment        This is fragment need replace in layout contentTab
     * @param fragmentManager This is fragment manager
     */
    public static void replaceFragmentAddToBackStackByTag(Fragment fragment, FragmentManager fragmentManager, String tag, int content) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(content, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentTab
     *
     * @param fragment        This is fragment need replace in layout contentTab
     * @param fragmentManager This is fragment manager
     */
    public static void replaceFragment(Fragment fragment, FragmentManager fragmentManager, int content) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentMenu
     *
     * @param fragment        This is fragment need replace in layout contentMenu
     * @param fragmentManager This is fragment manager
     */
    public static void replaceMenuFragment(Fragment fragment, FragmentManager fragmentManager, int content) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Repale fragment with contentMenu and add fragment to backstack
     *
     * @param fragment        This is fragment need replace in layout contentMenu
     * @param fragmentManager This is fragment manager
     */
    public static void replaceMenuAddToBackStack(Fragment fragment, FragmentManager fragmentManager, int content) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void replaceFagmentFromLeft(Fragment fragment, FragmentManager fragmentManager, int content){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_to_right);
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.commit();
    }
    public static void replaceFagmentFromRight(Fragment fragment, FragmentManager fragmentManager, int content){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_to_left);
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.commit();
    }
    public static void replaceFagmentFromRightAddToBackstack(Fragment fragment, FragmentManager fragmentManager,int content){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_to_left);
        fragmentTransaction.replace(content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public static void removeFragment(Fragment fragment, FragmentManager fragmentManager){
        fragmentManager.beginTransaction().remove(fragment).commit();
    }
}
