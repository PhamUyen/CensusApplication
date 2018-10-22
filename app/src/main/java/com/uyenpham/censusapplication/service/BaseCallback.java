package com.uyenpham.censusapplication.service;

import android.content.Context;

import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {
    public static final String SERVER_ERROR = "Error";
    public static final String SERVER_SUCCESS = "SUCCESS";
    private Context context;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
       if(response.isSuccessful()){
           onSuccess(response.body());
       }else {
           onError(String.valueOf(response.code()), response.message());
       }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!Utils.isOnline(App.getInstance()) ){
            onNetworkError();
            return;
        }
        onError(SERVER_ERROR, t.getMessage());
    }

    protected abstract void onError(String errorCode, String errorMessage);
        //SHOW DIALOG
    protected abstract void onSuccess(T data);
    public void onNetworkError() { }
}
