package com.uyenpham.censusapplication.ui.fragments;

import android.app.Activity;
import android.view.View;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.ui.activities.MainActivity;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import butterknife.OnClick;

public class MenuFragment extends BaseFragment {
    private MainActivity main;
    private CustomNavigationBar navigationBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (MainActivity) activity;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void createView(View view) {
    }

    @OnClick({R.id.btn_interview, R.id.btn_sync, R.id.btn_search, R.id.btn_update, R.id.btn_logout, R.id.btn_exit})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_interview:
                LocalityFragment localityFragment = new LocalityFragment();
                FragmentHelper.replaceFragmentAddToBackStack(localityFragment, main.mFragmentManager);
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
    private void initNavigationBar() {
        navigationBar = main.getNavigationBar();
        navigationBar.reSetAll();
        navigationBar.hideImgright();
        navigationBar.hideImgLeft();
        navigationBar.setTitle(getString(R.string.txt_menu_interview));
    }

    @Override
    public void onResume() {
        super.onResume();
        initNavigationBar();
    }
}
