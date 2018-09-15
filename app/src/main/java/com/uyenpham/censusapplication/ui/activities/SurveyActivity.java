package com.uyenpham.censusapplication.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.DrawerDataFactory;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.SingleFamilyResponse;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.ui.adapters.DrawerAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IExitClick;
import com.uyenpham.censusapplication.ui.interfaces.OnBackPressed;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SurveyActivity extends BaseActivity {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        drawerList.setLayoutManager(manager);
        list = new ArrayList<>();


        mFragmentManager = getSupportFragmentManager();
        setNavigationBar();

        Bundle bundle = getIntent().getBundleExtra(Constants.KEY_EXTRA_DATA);
        familyDTO = (FamilyDTO) bundle.getSerializable(Constants.KEY_FAMILY);
        list.add(DrawerDataFactory.makeInfoGroup(familyDTO));
        adapter = new DrawerAdapter(list);
        drawerList.setAdapter(adapter);
//        getFamily(idHo);

//        SurveyFragment profileFragment = new SurveyFragment();
//        FragmentHelper.replaceFragmentAddToBackStack(profileFragment, mFragmentManager, ID_SURVEY_CONTENT);
    }

    @OnClick(R.id.imvLeft)
    void onMenuClick(){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    private void closeDrawer(){
        drawer.closeDrawer(GravityCompat.START);
    }

    public void openDrawer(){
        drawer.openDrawer(GravityCompat.START);
    }
    public void toggleDrawer(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }else {
            openDrawer();
        }
    }
    public CustomNavigationBar getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar() {
        navigationBar = (CustomNavigationBar) findViewById(R.id.toolbar);
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
    void onExit(){
        if(iExitClick != null){
            iExitClick.onExitClick();
        }else {
            finish();
        }
    }

    private void getFamily(String id){
        ServiceBuilder.getApiServiceNormal().getOneFamily(id)
                .enqueue(new BaseCallback<SingleFamilyResponse>() {
                    @Override
                    protected void onError(String errorCode, String errorMessage) {

                    }

                    @Override
                    protected void onSuccess(SingleFamilyResponse data) {
                        familyDTO = data.getFamily();
                        list.add(DrawerDataFactory.makeInfoGroup(familyDTO));
                        adapter = new DrawerAdapter(list);
                        drawerList.setAdapter(adapter);
                    }
                });
    }
}
