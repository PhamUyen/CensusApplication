package com.uyenpham.censusapplication.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uyenpham.censusapplication.BuildConfig;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.user.LoginDTO;
import com.uyenpham.censusapplication.models.user.ResponseLoginDTO;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.utils.ValidateUtils;

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

    private void login(LoginDTO loginDTO) {
        ServiceBuilder.getApiServiceAuthen().login(loginDTO)
                .enqueue(new BaseCallback<ResponseLoginDTO>() {
                    @Override
                    protected void onSuccess(ResponseLoginDTO data) {
                        DialogUtils.dismissProgressDialog();
                        SharedPrefsUtils.setStringPreference(LoginActivity.this, Constants.KEY_TOKEN,data.getToken());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    protected void onError(String errorCode, String errorMessage) {
                        DialogUtils.dismissProgressDialog();
                        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
