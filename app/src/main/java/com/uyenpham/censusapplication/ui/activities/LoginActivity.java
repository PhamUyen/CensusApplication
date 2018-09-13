package com.uyenpham.censusapplication.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uyenpham.censusapplication.MainActivity;
import com.uyenpham.censusapplication.R;
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
        super.initLayout();
        switchToLoginView();
    }

    private void switchToLoginView(){
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        viewSplash.setVisibility(View.GONE);
                        viewLogin.setVisibility(View.VISIBLE);
                    }
                },2000
        );
    }

    @OnClick(R.id.btn_login)
    void onLogin(){
        String userName = edUserName.getText().toString();
        String pass = edPass.getText().toString();
        if(ValidateUtils.validateLogin(userName.trim(), pass.trim())){
            Toast.makeText(this, R.string.txt_message_invalid_login, Toast.LENGTH_SHORT).show();
        }else {
            //TODO: call login API
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
