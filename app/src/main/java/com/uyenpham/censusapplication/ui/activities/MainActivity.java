package com.uyenpham.censusapplication.ui.activities;

import android.support.v4.app.FragmentManager;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.ui.fragments.MenuFragment;
import com.uyenpham.censusapplication.ui.interfaces.OnBackPressed;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

public class MainActivity extends BaseActivity {
    public static final int ID_MAIN_CONTENT = R.id.content;

    public FragmentManager mFragmentManager;
    private CustomNavigationBar navigationBar;
    private OnBackPressed onBackPressed;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLayout() {
        mFragmentManager = getSupportFragmentManager();
        setNavigationBar();

        MenuFragment profileFragment = new MenuFragment();
        FragmentHelper.replaceFragmentAddToBackStack(profileFragment, mFragmentManager, ID_MAIN_CONTENT);
    }
    public CustomNavigationBar getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar() {
        navigationBar = (CustomNavigationBar) findViewById(R.id.toolbar);
    }
}
