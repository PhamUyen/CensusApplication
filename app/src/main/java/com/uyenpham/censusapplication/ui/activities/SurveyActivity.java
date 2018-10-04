package com.uyenpham.censusapplication.ui.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.db.DeadDAO;
import com.uyenpham.censusapplication.db.MemberDAO;
import com.uyenpham.censusapplication.db.PeopleDAO;
import com.uyenpham.censusapplication.db.WomanDAO;
import com.uyenpham.censusapplication.models.DrawerDataFactory;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.adapters.DrawerAdapter;
import com.uyenpham.censusapplication.ui.adapters.MemberAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IChildDrawerClick;
import com.uyenpham.censusapplication.ui.interfaces.IExitClick;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.ui.interfaces.OnBackPressed;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Utils;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SurveyActivity extends BaseActivity implements IChildDrawerClick,
        IRecyclerViewListener {
    public static final int ID_SURVEY_CONTENT = R.id.content;

    private CustomNavigationBar navigationBar;
    private OnBackPressed onBackPressed;
    private IExitClick iExitClick;
    private INextQuestion iNext;
    private IPreviousQuestion iPrevious;
    private DrawerAdapter adapter;
    @Bind(R.id.left_drawer)
    RecyclerView drawerList;
    @Bind(R.id.left_drawer_member)
    RecyclerView listMember;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.imv_next)
    ImageView imvNext;
    @Bind(R.id.imv_previous)
    ImageView imvPrevious;

    private List<GroupDrawer> list;
    private FamilyDTO familyDTO;
    public static int currentIndex = 8;
    private ArrayList<QuestionDTO> listQuestion;
    private ArrayList<PeopleDetailDTO> listPeople;
    private MemberAdapter memberAdapter;
    public boolean isMember;
    public String survey;

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


        setNavigationBar();

        Bundle bundle = getIntent().getBundleExtra(Constants.KEY_EXTRA_DATA);
        familyDTO = (FamilyDTO) bundle.getSerializable(Constants.KEY_FAMILY);
        genListAnser();

        setInfoFamily(familyDTO);
        makeListQuestion();

        //set default quest
        Utils.replcaeFragmentByType(listQuestion.get(currentIndex), true, listQuestion,
                ID_SURVEY_CONTENT, mFragmentManager,-1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListDrawer();
    }

    private void genListAnser() {
        for (Field field : familyDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            AnswerDTO answerDTO;
            if (familyDTO.get(field.getName()) instanceof String) {
                answerDTO = new AnswerDTO(familyDTO.getIDHO() + field.getName(), (String)
                        familyDTO.get(field.getName()), field.getName(), familyDTO.getIDHO(), null);
            } else {
                answerDTO = new AnswerDTO(familyDTO.getIDHO() + field.getName(), null, field
                        .getName(), familyDTO.getIDHO(), (Integer) familyDTO.get(field.getName()));
            }

            if (AnswerDAO.getInstance().checkIsExistDB(answerDTO.getId())) {
                AnswerDAO.getInstance().update(answerDTO);
            } else {
                AnswerDAO.getInstance().insert(answerDTO);
            }

        }
    }

    private void setInfoFamily(FamilyDTO family) {
        Constants.mStaticObject.setPeopleDTO(PeopleDAO.getInstance().findById(familyDTO));
        Constants.mStaticObject.setWomanDTO(WomanDAO.getInstance().findById(family.getIDHO()));
        Constants.mStaticObject.setDeadDTO(DeadDAO.getInstance().findById(family.getIDHO()));
        Constants.mStaticObject.setMemberDTO(MemberDAO.getInstance().findByIdHo(family.getIDHO()));
        Constants.mStaticObject.getFamilyDetailDTO().setIDHO(family.getIDHO());
    }

    private void makeListQuestion() {
        listQuestion = DrawerDataFactory.makeListInfo();
        listQuestion.addAll(DrawerDataFactory.makeListPeople());
    }

    private void setListDrawer() {
        list.add(DrawerDataFactory.makeInfoGroup());
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
            currentIndex =8;
            finish();
            Constants.mStaticObject.updateDB();
            Constants.mStaticObject.reset();

        }
    }

    @OnClick({R.id.imv_next, R.id.imv_previous})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_next:
                if (iNext != null) {
                    iNext.next();
                }
                break;
            case R.id.imv_previous:
                if (iPrevious != null) {
                    iPrevious.previuos();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(QuestionDTO quest) {
        toggleDrawer();
        if (currentIndex != getIndexOfQuestion(quest)) {
            Utils.replcaeFragmentByType(quest, true, listQuestion, ID_SURVEY_CONTENT,
                    mFragmentManager,-1);
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

    private QuestionDTO getQuestionByIndex(int index) {
        if (index != -1 && index < listQuestion.size()) {
            return listQuestion.get(index);
        }
        return null;
    }

    public void setiNext(INextQuestion iNext) {
        this.iNext = iNext;
    }

    public void setiPrevious(IPreviousQuestion iPrevious) {
        this.iPrevious = iPrevious;
    }

    public void setListPeople() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listMember.setLayoutManager(manager);
        listPeople = new ArrayList<>();
        drawerList.setVisibility(View.GONE);
        listMember.setVisibility(View.VISIBLE);
        listPeople.addAll(Constants.mStaticObject.getPeopleDetailDTO());

        memberAdapter = new MemberAdapter(listPeople);
        memberAdapter.setListener(this);
        listMember.setAdapter(memberAdapter);
        makeListQuestionMember();
        currentIndex =0;
        Utils.replcaeFragmentByType(listQuestion.get(currentIndex), true, listQuestion, ID_SURVEY_CONTENT,
                mFragmentManager, 0);
    }

    private void makeListQuestionMember() {
        listQuestion.clear();
        switch (survey) {
            case Constants.SURVEY_MEMBER:
                listQuestion = DrawerDataFactory.makeListMember();
                break;
            case Constants.SURVEY_WOMAN:
                listQuestion = DrawerDataFactory.makeListWoman();
                break;
            case Constants.SURVEY_DEAD:
                //make list dead question
//                listQuestion.
            default:
                break;


        }
    }

    @Override
    public void onClickItem(View view, int postion) {
        toggleDrawer();
        currentIndex =0;
            Utils.replcaeFragmentByType(listQuestion.get(currentIndex), true, listQuestion, ID_SURVEY_CONTENT,
                    mFragmentManager, postion);
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }
}
