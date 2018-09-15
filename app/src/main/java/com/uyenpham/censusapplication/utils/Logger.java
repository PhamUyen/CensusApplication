package com.uyenpham.censusapplication.utils;

import android.util.Log;

import com.uyenpham.censusapplication.BuildConfig;

public class Logger {
    private static final boolean DEBUG_MODE = BuildConfig.DEBUG;

    private static final String TAG = "CensusApp";

    public static void e(Exception e) {
        if (DEBUG_MODE) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static void e(String msg) {
        if (DEBUG_MODE)
            Log.e(TAG, msg);
    }

    public static void d(String msg) {
        if (DEBUG_MODE)
            Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (DEBUG_MODE)
            Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (DEBUG_MODE)
            Log.w(TAG, msg);
    }

    public static void v(String msg) {
        if (DEBUG_MODE)
            Log.v(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG_MODE)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Exception e) {
        if (DEBUG_MODE)
            Log.e(tag, msg, e);
    }

    public static void d(String tag, String msg) {
        if (DEBUG_MODE)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG_MODE)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG_MODE)
            Log.w(tag, msg);
    }

    private static final String TAG_MEM = "MEMORY";

    public static void v(String tag, String msg) {
        if (DEBUG_MODE)
            Log.v(tag, msg);
    }

}
