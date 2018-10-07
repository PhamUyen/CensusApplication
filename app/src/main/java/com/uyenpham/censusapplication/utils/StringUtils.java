package com.uyenpham.censusapplication.utils;

import android.content.Context;
import android.util.SparseArray;

public class StringUtils {
    public static boolean isEmpty(String s){
        return s == null || s.equals("");
    }
    public static SparseArray<String> parseStringArray(int stringArrayResourceId, Context context) {
        String[] stringArray = context.getResources().getStringArray(stringArrayResourceId);
        SparseArray<String> outputArray = new SparseArray<String>(stringArray.length);
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|", 2);
            outputArray.put(Integer.valueOf(splitResult[0]), splitResult[1]);
        }
        return outputArray;
    }
}
