package com.uyenpham.censusapplication.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.LocalityDAO;
import com.uyenpham.censusapplication.models.locality.LocalityDTO;
import com.uyenpham.censusapplication.models.locality.LocalityResponse;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.ui.activities.MainActivity;
import com.uyenpham.censusapplication.ui.adapters.LocalityAdapter;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.utils.Utils;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;

public class LocalityFragment extends BaseFragment implements
        CustomNavigationBar.INavigationOnClick, IRecyclerViewListener {
    private MainActivity main;
    private CustomNavigationBar navigationBar;
    private RecyclerView rcvLocality;
    private ArrayList<LocalityDTO> list;
    private LocalityAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (MainActivity) activity;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_locality;
    }

    @Override
    protected void createView(View view) {
//        dummyData();
        list = new ArrayList<>();
        rcvLocality = view.findViewById(R.id.rcvLocality);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rcvLocality.setLayoutManager(manager);
        adapter = new LocalityAdapter(list);
        adapter.setListener(this);
        rcvLocality.setAdapter(adapter);

        getLocalities();
    }

    @Override
    public void onResume() {
        super.onResume();
        initNavigationBar();
    }

    private void initNavigationBar() {
        navigationBar = main.getNavigationBar();
        navigationBar.reSetAll();
        navigationBar.hideImgright();
        navigationBar.setShowLeft();
        navigationBar.setINavigationOnClick(this);
        navigationBar.setTitle(getString(R.string.txt_locality_list));
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {
        main.getSupportFragmentManager().popBackStack();
    }

//    private void dummyData(){
//        list = new ArrayList<>();
//        list.add(new LocalityDTO("98201002 : Địa bàn tổ dân phố sô 98201-2","Số hộ: 60"));
//    }

    @Override
    public void onClickItem(View view, int postion) {
        FamilyFragment familyFragment = new FamilyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_IDDB, list.get(postion).getIDDB());
        familyFragment.setArguments(bundle);
        FragmentHelper.replaceFragmentAddToBackStackByTag(familyFragment, main.mFragmentManager,
                FamilyFragment.class.getSimpleName());
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }

    private void getLocalities() {
        DialogUtils.showProgressDialog(main);
        if (Utils.isOnline(main)) {
            ServiceBuilder.getApiServiceNormal().getListLocality()
                    .enqueue(new BaseCallback<LocalityResponse>() {
                        @Override
                        protected void onError(String errorCode, String errorMessage) {
                            DialogUtils.dismissProgressDialog();
                            DialogUtils.showAlert(main, errorMessage);
                        }

                        @Override
                        protected void onSuccess(LocalityResponse data) {
                            DialogUtils.dismissProgressDialog();
                            list.addAll(data.getListLocality());
                            insertDB();
                            adapter.notifyDataSetChanged();
                        }
                    });
        } else {
            if (LocalityDAO.getInstance().findByUserId(
                    SharedPrefsUtils.getStringPreference(main, Constants.KEY_INVESTIGATE_USER))
                    != null) {
                list.addAll(LocalityDAO.getInstance().findByUserId(SharedPrefsUtils
                        .getStringPreference(main, Constants.KEY_INVESTIGATE_USER)));
            }
            adapter.notifyDataSetChanged();
            DialogUtils.dismissProgressDialog();
        }

    }
    private void insertDB(){
        for(LocalityDTO family : list){
            family.setUser(SharedPrefsUtils.getStringPreference(getContext(),Constants.KEY_INVESTIGATE_USER));
            LocalityDAO.getInstance().insert(family);
        }
    }
}
