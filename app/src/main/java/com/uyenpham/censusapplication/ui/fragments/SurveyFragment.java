package com.uyenpham.censusapplication.ui.fragments;

import android.content.Context;
import android.view.View;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;

import butterknife.OnClick;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.ID_SURVEY_CONTENT;

public class SurveyFragment extends BaseFragment implements CustomNavigationBar.INavigationOnClick{
    private CustomNavigationBar navigationBar;
    private SurveyActivity main;
    ArrayList<GroupDrawer> list = new ArrayList<>();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.main = (SurveyActivity) context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_survey;
    }

    @Override
    protected void createView(View view) {
         createListDrawer();

//         main.setList(DrawerDataFactory.makeGenres());
    }

    @Override
    public void onResume() {
        super.onResume();
        initNavigationBar();
    }

    private void initNavigationBar() {
        navigationBar = main.getNavigationBar();
        navigationBar.reSetAll();
        navigationBar.setShowRight();
        navigationBar.setShowLeft();
        navigationBar.setIconLeft(R.drawable.ic_menu);
        navigationBar.setINavigationOnClick(this);
        navigationBar.setTitle(getString(R.string.txt_interview_list));
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {
        main.toggleDrawer();
    }

    private void createListDrawer(){
        list.add(new GroupDrawer("Thông tin hộ", null));
        list.add(new GroupDrawer("Nhân khẩu thực tế", null));
        list.add(new GroupDrawer("Thành viên", null));
        list.add(new GroupDrawer("Thành viên phụ nữ", null));
    }

    @OnClick(R.id.btTest)
    void onTest(){
        TextFragment surveyFragment = new TextFragment();
        FragmentHelper.replaceFagmentFromRight(surveyFragment, main.mFragmentManager, ID_SURVEY_CONTENT);
    }
}
