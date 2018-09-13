package com.uyenpham.censusapplication.utils;

public class ValidateUtils {
    public static boolean validateLogin(String userName, String pass){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(pass))
            return true;
        else
            return false;
    }
}
