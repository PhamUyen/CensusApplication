package com.uyenpham.censusapplication.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.MemberAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IExitClick;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.ui.interfaces.OnBackPressed;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;

import butterknife.Bind;

public class MemberActivity extends SurveyActivity implements IRecyclerViewListener{
    public static final int ID_MEMBER_CONTENT = R.id.content;

    public FragmentManager mFragmentManager;
    private CustomNavigationBar navigationBar;
    private OnBackPressed onBackPressed;
    private IExitClick iExitClick;
    private INextQuestion iNext;
    private IPreviousQuestion iPrevious;
    private MemberAdapter adapter;
    @Bind(R.id.left_drawer)
    RecyclerView drawerList;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.imv_next)
    ImageView imvNext;
    @Bind(R.id.imv_previous)
    ImageView imvPrevious;
    private ArrayList<PeopleDetailDTO> list;
    private String survey;
    private PeopleDetailDTO peopleDetailDTO;
    private ArrayList<QuestionDTO> listQuestion;
    public static int currentIndex = 0;

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
        setListDrawer();

        survey= getIntent().getStringExtra("survey");

        mFragmentManager = getSupportFragmentManager();
        setNavigationBar();

        makeListQuestion();

        //set default quest
    }

    public CustomNavigationBar getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar() {
        navigationBar = findViewById(R.id.toolbar);
        navigationBar.reSetAll();
        navigationBar.setIconLeft(R.drawable.ic_menu_gallery);

        if(peopleDetailDTO == null){
            navigationBar.setTitle(list.get(0).getQ1());
        }else {
            navigationBar.setTitle(peopleDetailDTO.getQ1());
        }
    }


//    private void makeListQuestion() {
//        listQuestion = new ArrayList<>();
//        switch (survey) {
//            case Constants.SURVEY_MEMBER:
////                listQuestion.addAll(DrawerDataFactory.makeListQuestionMember());
//                break;
//            case Constants.SURVEY_WOMAN:
////                listQuestion.addAll(DrawerDataFactory.makeListWoman());
//                break;
//            default:
//                break;
//        }
//
//    }
    private void setListDrawer() {
        list.addAll(Constants.mStaticObject.getPeopleDetailDTO());

//        adapter = new MemberAdapter(list);
        adapter.setListener(this);
        drawerList.setAdapter(adapter);
    }

    @Override
    public void onClickItem(View view, int postion) {

    }

    @Override
    public void onLongClickItem(View v, int position) {

    }
}
