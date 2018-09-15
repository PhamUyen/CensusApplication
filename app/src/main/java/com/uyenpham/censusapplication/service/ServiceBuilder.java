package com.uyenpham.censusapplication.service;

import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static Retrofit retrofit;
    private static APIService sApiService;
    private static final String BASE_URL = "http://ubuntu.gso.gov.vn";

    public static synchronized APIService getApiServiceNormal() {
        sApiService = getRetrofitNomal().create(APIService.class);

        return sApiService;
    }

    public static synchronized APIService getApiServiceAuthen() {
        sApiService = getRetrofitAuthen().create(APIService.class);

        return sApiService;
    }
    public static Retrofit getRetrofitNomal() {
        return new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnSafeHttpClient(false))
                    .build();
    }
    public static Retrofit getRetrofitAuthen() {
        return new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnSafeHttpClient(true))
                    .build();
    }
    public static OkHttpClient getUnSafeHttpClient(final boolean isLogin) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                for (Map.Entry<String, String> header: getHeader(isLogin).entrySet()){
                    builder.header(header.getKey(), header.getValue() == null ? "" : header.getValue());
                }

                Request request = builder
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
       return httpClient.build();
    }
    private static ArrayList<Header> getHeaders(boolean isLogin){
        String token = SharedPrefsUtils.getStringPreference(App.getInstance(), Constants.KEY_TOKEN);
        ArrayList<Header> headers = new ArrayList();
        headers.add(new Header("Content-Type","application/json"));
        headers.add(new Header("Device", "ANDROID"));
        if(!isLogin){
            headers.add(new Header("Authorization",token));
        }
        return headers;
    }
    public static HashMap<String, String> getHeader(boolean isLogin){
        HashMap<String, String> headers = new HashMap<>();
        String token = SharedPrefsUtils.getStringPreference(App.getInstance(), Constants.KEY_TOKEN);
        headers.put("Content-Type","application/json");
        headers.put("Device", "ANDROID");
        if(!isLogin){
            headers.put("Authorization",token);
        }
        return headers;
    }

}
