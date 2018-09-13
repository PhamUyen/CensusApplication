package com.uyenpham.censusapplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.ButterKnife;

/**
 * Base activity
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    // Inject views
    ButterKnife.bind(this);

    // Prepare layout
    initLayout();

  }

  public void initLayout() {
  }

  /**
   * Return layout resource id for activity
   */
  protected abstract int getLayoutId();

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard(View view) {
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Show keyboard for {@link EditText}
   */
  public void showKeyboard(EditText editText) {
    editText.requestFocus();
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    FragmentManager manager = getSupportFragmentManager();
    if (manager != null) {
      for (Fragment fragment : manager.getFragments()) {
        if (fragment != null) {
          fragment.onActivityResult(requestCode, resultCode, data);
        }
      }
    }
  }

  @Override
  protected void onStart() {
    super.onStart();

  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}
