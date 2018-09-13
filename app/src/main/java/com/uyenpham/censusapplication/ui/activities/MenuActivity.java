package com.uyenpham.censusapplication.ui.activities;

import android.view.View;

import com.uyenpham.censusapplication.R;

import butterknife.OnClick;

public class MenuActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @OnClick({R.id.btn_interview, R.id.btn_sync, R.id.btn_search, R.id.btn_update, R.id.btn_logout, R.id.btn_exit})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_interview:
                break;
            case R.id.btn_search:
                break;
            case R.id.btn_sync:
                break;
            case R.id.btn_logout:
                break;
            case R.id.btn_exit:
                break;
            case R.id.btn_update:
                break;
        }
    }
}
