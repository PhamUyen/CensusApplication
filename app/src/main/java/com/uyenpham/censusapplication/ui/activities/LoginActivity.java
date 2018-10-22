package com.uyenpham.censusapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uyenpham.censusapplication.BuildConfig;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.locality.DistrictDTO;
import com.uyenpham.censusapplication.models.locality.NationDTO;
import com.uyenpham.censusapplication.models.locality.ProvinceDTO;
import com.uyenpham.censusapplication.models.user.LoginDTO;
import com.uyenpham.censusapplication.models.user.ResponseLoginDTO;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.KillAppService;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.FileUtils;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.utils.ValidateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.view_splash)
    TextView viewSplash;

    @Bind(R.id.ln_login)
    RelativeLayout viewLogin;

    @Bind(R.id.ed_userName)
    EditText edUserName;

    @Bind(R.id.ed_pass)
    EditText edPass;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initLayout() {
        switchToLoginView();
        if (BuildConfig.DEBUG) {
            edPass.setText("123456");
            edUserName.setText("D992001");
        }
        getListNation();
        getListDistrict();
        getListProvince();
        startService(new Intent(getBaseContext(), KillAppService.class));
    }

    @SuppressLint("StaticFieldLeak")
    private void getListNation(){
        new AsyncTask<Void,Void, List<NationDTO>>(){

            @Override
            protected  List<NationDTO> doInBackground(Void... voids) {
                List<NationDTO> list =FileUtils.getListNation(LoginActivity.this);
                return list;
            }

            @Override
            protected void onPostExecute(List<NationDTO> nationDTOS) {
                SharedPrefsUtils.setStringPreference(LoginActivity.this,Constants.KEY_NATION,new Gson().toJson(nationDTOS));
                super.onPostExecute(nationDTOS);
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    private void getListProvince(){
        new AsyncTask<Void,Void, List<ProvinceDTO>>(){

            @Override
            protected  List<ProvinceDTO> doInBackground(Void... voids) {
                List<ProvinceDTO> list =FileUtils.getListProvince(LoginActivity.this);
                return list;
            }

            @Override
            protected void onPostExecute(List<ProvinceDTO> provinceDTOS) {
                SharedPrefsUtils.setStringPreference(LoginActivity.this,Constants.KEY_PROVINCE,new Gson().toJson(provinceDTOS));
                super.onPostExecute(provinceDTOS);
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    private void getListDistrict(){
        new AsyncTask<Void,Void, List<DistrictDTO>>(){

            @Override
            protected  List<DistrictDTO> doInBackground(Void... voids) {
                List<DistrictDTO> list =FileUtils.getListDistrict(LoginActivity.this);
                return list;
            }

            @Override
            protected void onPostExecute(List<DistrictDTO> provinceDTOS) {
                SharedPrefsUtils.setStringPreference(LoginActivity.this,Constants.KEY_DISTRICT,new Gson().toJson(provinceDTOS));
                super.onPostExecute(provinceDTOS);
            }
        }.execute();
    }

    private void switchToLoginView() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        viewSplash.setVisibility(View.GONE);
                        viewLogin.setVisibility(View.VISIBLE);
                    }
                }, 2000
        );
    }

    @OnClick(R.id.btn_login)
    void onLogin() {
        String userName = edUserName.getText().toString();
        String pass = edPass.getText().toString();
        if (ValidateUtils.validateLogin(userName.trim(), pass.trim())) {
            Toast.makeText(this, R.string.txt_message_invalid_login, Toast.LENGTH_SHORT).show();
        } else {
            DialogUtils.showProgressDialog(LoginActivity.this);
            login(new LoginDTO(userName, pass));
        }
    }
    private void loginOffline(String errorMessage){
        DialogUtils.dismissProgressDialog();
        if(edUserName.getText().toString().equalsIgnoreCase(SharedPrefsUtils.getStringPreference(this,Constants.KEY_INVESTIGATE_USER))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            DialogUtils.showAlert(this,errorMessage);
        }
    }

    private void login(LoginDTO loginDTO) {
        ServiceBuilder.getApiServiceAuthen().login(loginDTO)
                .enqueue(new BaseCallback<ResponseLoginDTO>() {

                    @Override
                    public void onNetworkError() {
                        loginOffline(getString(R.string.msg_network_lost));
                        super.onNetworkError();
                    }

                    @Override
                    protected void onSuccess(ResponseLoginDTO data) {
                        SharedPrefsUtils.setStringPreference(LoginActivity.this,Constants.KEY_INVESTIGATE_USER, edUserName.getText().toString());
                        DialogUtils.dismissProgressDialog();
                        SharedPrefsUtils.setStringPreference(LoginActivity.this, Constants.KEY_TOKEN,data.getToken());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    protected void onError(String errorCode, String errorMessage) {
                        loginOffline(errorMessage);
                    }
                });
    }
}
