package com.uyenpham.censusapplication;
import android.app.Application;


public class App extends Application {
    public String deviceId;

    public static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static App getInstance() {
        return INSTANCE;
    }


    public String getDeviceId() {
        return deviceId;
    }

}
