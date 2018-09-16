package com.uyenpham.censusapplication.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.DrawerDataFactory;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.DrawerAdapter;
import com.uyenpham.censusapplication.ui.fragments.NumberInputFragment;
import com.uyenpham.censusapplication.ui.fragments.SingleSelectFragment;
import com.uyenpham.censusapplication.ui.fragments.TypeTextInputFragment;
import com.uyenpham.censusapplication.ui.interfaces.IChildDrawerClick;
import com.uyenpham.censusapplication.ui.interfaces.IExitClick;
import com.uyenpham.censusapplication.ui.interfaces.OnBackPressed;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SurveyActivity extends BaseActivity implements IChildDrawerClick {
    public static final int ID_SURVEY_CONTENT = R.id.content;

    public FragmentManager mFragmentManager;
    private CustomNavigationBar navigationBar;
    private OnBackPressed onBackPressed;
    private IExitClick iExitClick;
    private DrawerAdapter adapter;
    @Bind(R.id.left_drawer)
    RecyclerView drawerList;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.imv_next)
    ImageView imvNext;
    @Bind(R.id.imv_previous)
    ImageView imvPrevious;

    private List<GroupDrawer> list;
    private FamilyDTO familyDTO;
    private int currentIndex = 8;
    private ArrayList<QuestionDTO> listQuestion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initLayout() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        drawerList.setLayoutManager(manager);
        list = new ArrayList<>();


        mFragmentManager = getSupportFragmentManager();
        setNavigationBar();

        Bundle bundle = getIntent().getBundleExtra(Constants.KEY_EXTRA_DATA);
        familyDTO = (FamilyDTO) bundle.getSerializable(Constants.KEY_FAMILY);

        setListDrawer();
        makeListQuestion();

        //set default quest
        replcaeFragmentByType(listQuestion.get(currentIndex), true);
    }
    private void makeListQuestion(){
        listQuestion = DrawerDataFactory.makeListInfo(familyDTO);
        listQuestion.addAll(DrawerDataFactory.makeListPeople());
        listQuestion.addAll(DrawerDataFactory.makeListMember());
        listQuestion.addAll(DrawerDataFactory.makeListWoman());
    }

    private void setListDrawer(){
        list.add(DrawerDataFactory.makeInfoGroup(familyDTO));
        list.add(DrawerDataFactory.makePeopleGroup());
        list.add(DrawerDataFactory.makeMemberGroup());
        list.add(DrawerDataFactory.makeWomanGroup());

        adapter = new DrawerAdapter(list);
        adapter.setListener(this);
        drawerList.setAdapter(adapter);
    }

    @OnClick(R.id.imvLeft)
    void onMenuClick() {
        toggleDrawer();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            if (onBackPressed != null) {
                onBackPressed.OnBack();
                return;
            }
        }
    }

    public void setiExitClick(IExitClick iExitClick) {
        this.iExitClick = iExitClick;
    }

    public DrawerAdapter getAdapter() {
        return adapter;
    }

    private void setAdapter(DrawerAdapter adapter) {
        this.adapter = adapter;
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    public void toggleDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    public CustomNavigationBar getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar() {
        navigationBar = findViewById(R.id.toolbar);
        navigationBar.reSetAll();
        navigationBar.setIconLeft(R.drawable.ic_menu);
        navigationBar.setTitle(getString(R.string.txt_interview_detail));
    }

    public List<GroupDrawer> getList() {
        return list;
    }

    public void setList(List<GroupDrawer> list) {
        this.list.addAll(list);
        adapter = new DrawerAdapter(list);
        drawerList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_exit)
    void onExit() {
        if (iExitClick != null) {
            iExitClick.onExitClick();
        } else {
            finish();
        }
    }

    @OnClick({R.id.imv_next, R.id.imv_previous})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_next:
                if (currentIndex < listQuestion.size() - 1) {
                    currentIndex++;
                    replcaeFragmentByType(listQuestion.get(currentIndex), true);
                }
                break;
            case R.id.imv_previous:
                if (currentIndex > 0) {
                    currentIndex--;
                    replcaeFragmentByType(listQuestion.get(currentIndex), false);
                }
                break;
            default:
                break;
        }
    }

    private void replcaeFragmentByType(QuestionDTO questionDTO, boolean isNext) {
        int type = questionDTO.getType();

        switch (type) {
            case Constants.TYPE_TEXT_INPUT:
            case Constants.TYPE_TEXT_INPUT_LIST:
                TypeTextInputFragment fragment = new TypeTextInputFragment();
                fragment.setQuestionDTO(questionDTO);
                fragment.setAnswerDTO(null);
                replaceAnimation(fragment, isNext);
                break;
            case Constants.TYPE_SINGLE_SELECT:
                SingleSelectFragment singleSelectFragment = new SingleSelectFragment();
                singleSelectFragment.setQuestionDTO(questionDTO);
                singleSelectFragment.setAnswerDTO(null);
                replaceAnimation(singleSelectFragment, isNext);
                break;
            case Constants.TYPE_NUMBER_INPUT:
                NumberInputFragment numberInputFragment = new NumberInputFragment();
                numberInputFragment.setQuestionDTO(questionDTO);
                numberInputFragment.setAnswerDTO(null);
                replaceAnimation(numberInputFragment, isNext);
                break;
            default:
                break;
        }
    }

    private void replaceAnimation(Fragment fragment, boolean isNext) {
        if (isNext) {
            FragmentHelper.replaceFagmentFromRight(fragment, mFragmentManager,
                    ID_SURVEY_CONTENT);
        } else {
            FragmentHelper.replaceFagmentFromLeft(fragment, mFragmentManager,
                    ID_SURVEY_CONTENT);
        }
    }

    @Override
    public void onClick(QuestionDTO quest) {
        toggleDrawer();
        if (currentIndex != getIndexOfQuestion(quest)) {
            replcaeFragmentByType(quest, true);
            currentIndex = getIndexOfQuestion(quest);
        }
    }

    private int getIndexOfQuestion(QuestionDTO question) {
        for (QuestionDTO quest : listQuestion) {
            if (quest.getId().equals(question.getId())) {
                return listQuestion.indexOf(quest);
            }
        }
        return -1;
    }
}
