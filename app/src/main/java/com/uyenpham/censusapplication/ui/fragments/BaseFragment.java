package com.uyenpham.censusapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uyenpham.censusapplication.ui.activities.BaseActivity;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Logger;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
//        if(savedInstanceState!= null)
//        {
//            mFragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//
//            FragmentOne fragment = new FragmentOne();
//
//            fragmentTransaction.add(R.id.fragment_container, fragment);
//            fragmentTransaction.commit();
//        }
        setRetainInstance(true);
    }


    protected void showProgressDialog(String message) {
        mActivity.showProgressDialog(message);
    }

    protected void showProgressDialog(int messageId) {
        mActivity.showProgressDialog(messageId);
    }

    protected void hideProgressDialog() {
        mActivity.hideProgressDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String className = getClass().getName();
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mView);
            createView(mView);
        } else {
            Logger.d(className + " view not null");
        }
        return mView;
    }

    public void goToActivity(Class c) {
        Intent intent = new Intent(mActivity, c);
        startActivity(intent);
        mActivity.overridePendingTransition(0, 0);
    }

    public void goToActivity(Class c, Bundle bundle) {
        Intent intent = new Intent(mActivity, c);
        intent.putExtra(Constants.KEY_EXTRA_DATA, bundle);
        startActivity(intent);
        mActivity.overridePendingTransition(0, 0);
    }

    protected abstract int getLayoutId();

    protected abstract void createView(View view);
    protected LinearLayout.LayoutParams getLayoutParams(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);
        return layoutParams;
    }
}
