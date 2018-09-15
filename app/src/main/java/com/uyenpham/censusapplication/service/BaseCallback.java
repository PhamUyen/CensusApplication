package com.uyenpham.censusapplication.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseCallback<T> implements Callback<T> {
    public static final String SERVER_ERROR = "Error";
    public static final String SERVER_SUCCESS = "SUCCESS";
    private Context context;
    public  boolean isOnline() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

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
        onError(SERVER_ERROR, t.getMessage());
    }

    protected abstract void onError(String errorCode, String errorMessage);
        //SHOW DIALOG
    protected abstract void onSuccess(T data);
}
