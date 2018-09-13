package com.uyenpham.censusapplication.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.uyenpham.censusapplication.R;

public class AnimationUtil {
    public static void setAnimationFadeIn(View view, Context context){
        runSimpleAnimation(context, view, R.anim.fade_in);
    }
    /**
     * @param context
     * @param view
     */
    public static void setAnimationFadeOut(View view, Context context){
        runSimpleAnimation(context, view,R.anim.fade_out);
    }

    /**
     * Animates a view so that it slides from its current position, out of view to the left.
     *
     * @param context
     * @param view
     */
    public static void setAnimationSlideToLeft(View view, Context context){
        runSimpleAnimation(context, view,R.anim.slide_to_left);
    }
    /**
     * Animates a view so that it slides from its current position, out of view to the right.
     *
     * @param context
     * @param view
     */
    public static void setAnimationSlideToRight(View view, Context context){
        runSimpleAnimation(context, view,R.anim.slide_to_right);
    }
    /**
     * Runs a simple animation on a View with no extra parameters.
     *
     * @param context
     * @param view
     * @param animationId
     */
    private static void runSimpleAnimation(Context context, View view, int animationId) {
        view.startAnimation(AnimationUtils.loadAnimation(
                context, animationId
        ));
    }
}
