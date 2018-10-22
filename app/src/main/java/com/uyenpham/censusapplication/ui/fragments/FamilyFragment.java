package com.uyenpham.censusapplication.ui.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.FamilyDAO;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.FamilyResponse;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.ui.activities.MainActivity;
import com.uyenpham.censusapplication.ui.activities.SurveyActivity;
import com.uyenpham.censusapplication.ui.adapters.FamilyAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class FamilyFragment extends BaseFragment implements
        CustomNavigationBar.INavigationOnClick, IRecyclerViewListener, FamilyAdapter.IFamilyClick {
    public static final String TAG= FamilyFragment.class.getSimpleName();
    private MainActivity main;
    private CustomNavigationBar navigationBar;
    private ArrayList<FamilyDTO> list;
    private FamilyAdapter adapter;

    @Bind(R.id.rcvFamily)
    RecyclerView rcvFamily;
    String iddb;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (MainActivity) activity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_family;
    }

    @Override
    protected void createView(View view) {
//        dummyData();

        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rcvFamily.setLayoutManager(manager);
        adapter = new FamilyAdapter(list);
        adapter.setListener(this);
        adapter.setItemListener(this);
        rcvFamily.setAdapter(adapter);

        initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        initNavigationBar();
    }

    @OnClick(R.id.btn_add_family)
    void onAddFamily() {
        Intent intent = new Intent(main, SurveyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_FAMILY,null);
        bundle.putString(Constants.KEY_IDDB,iddb);
        bundle.putInt(Constants.KEY_NUM_HO,list.size());
        intent.putExtra(Constants.KEY_EXTRA_DATA, bundle);
        startActivity(intent);
    }

    private void initNavigationBar() {
        navigationBar = main.getNavigationBar();
        navigationBar.reSetAll();
        navigationBar.hideImgright();
        navigationBar.setShowLeft();
        navigationBar.setINavigationOnClick(this);
        navigationBar.setTitle(getString(R.string.txt_interview_list));
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {
        main.getSupportFragmentManager().popBackStack();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
             iddb = bundle.getString(Constants.KEY_IDDB);
            getListFamily(iddb);
        }

    }
    private void dummyData() {
        list = new ArrayList<>();
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
        list.add(new FamilyDTO("445","001","Phạm Anh Tân",1));
    }

    @Override
    public void onClickItem(View view, int postion) {
        Intent intent = new Intent(main, SurveyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_FAMILY,list.get(postion));
        intent.putExtra(Constants.KEY_EXTRA_DATA, bundle);
        startActivity(intent);
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }

    @Override
    public void onInterviewClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {
        FamilyDTO family = list.get(position);
        String message = String.format(getString(R.string.txt_del_family),family.getIDHO(), family.getTENCHUHO());
        DialogUtils.showAlertAction(main, message,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //delete family
                Toast.makeText(main, "đã xóa!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListFamily(final String id){
        DialogUtils.showProgressDialog(main);
        ServiceBuilder.getApiServiceNormal().getListFamily(1,100,id)
                .enqueue(new BaseCallback<FamilyResponse>() {
                    @Override
                    protected void onError(String errorCode, String errorMessage) {
                        DialogUtils.dismissProgressDialog();
                        DialogUtils.showAlert(main, errorMessage);
                    }

                    @Override
                    protected void onSuccess(FamilyResponse data) {
                        DialogUtils.dismissProgressDialog();
                        list.addAll(data.getListLocality());
                        adapter.notifyDataSetChanged();
                        insertDB();
                    }
                });
    }
    private void insertDB(){
        for(FamilyDTO family : list){
            family.setIdInvestigateUser(SharedPrefsUtils.getStringPreference(getContext(),Constants.KEY_INVESTIGATE_USER));
            family.setNew(false);
            FamilyDAO.getInstance().insert(family);
        }
    }
}
