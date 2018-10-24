package com.uyenpham.censusapplication.ui.fragments;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.DeadDAO;
import com.uyenpham.censusapplication.db.FamilyDAO;
import com.uyenpham.censusapplication.db.FamilyDetailDAO;
import com.uyenpham.censusapplication.db.MemberDAO;
import com.uyenpham.censusapplication.db.PeopleDAO;
import com.uyenpham.censusapplication.db.PeopleDetailDAO;
import com.uyenpham.censusapplication.db.WomanDAO;
import com.uyenpham.censusapplication.models.SyncDTO;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.SingleFamilyResponse;
import com.uyenpham.censusapplication.service.BaseCallback;
import com.uyenpham.censusapplication.service.ServiceBuilder;
import com.uyenpham.censusapplication.ui.activities.MainActivity;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.FragmentHelper;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.views.CustomNavigationBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MenuFragment extends BaseFragment {
    private MainActivity main;
    private CustomNavigationBar navigationBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (MainActivity) activity;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void createView(View view) {
    }

    @OnClick({R.id.btn_interview, R.id.btn_sync, R.id.btn_search, R.id.btn_update, R.id.btn_logout, R.id.btn_exit})
    void onClick(View v){
        switch (v.getId()){
            case R.id.btn_interview:
                LocalityFragment localityFragment = new LocalityFragment();
                FragmentHelper.replaceFragmentAddToBackStack(localityFragment, main.mFragmentManager);
                break;
            case R.id.btn_search:
                break;
            case R.id.btn_sync:
                syncNewFamily();
                break;
            case R.id.btn_logout:
                break;
            case R.id.btn_exit:
                break;
            case R.id.btn_update:
                break;
        }
    }
    private void syncNewFamily(){
        String idUser = SharedPrefsUtils.getStringPreference(getContext(),Constants.KEY_INVESTIGATE_USER);
        List<FamilyDTO> listNewFamily = FamilyDAO.getInstance().findNewFamilyByUser(idUser);
        if(!listNewFamily.isEmpty()){
            for(FamilyDTO familyDTO : listNewFamily){
                SyncDTO syncDTO = genSycnNewFamily(familyDTO);
                ServiceBuilder.getApiServiceNormal().syncNew(syncDTO).enqueue(new BaseCallback<SingleFamilyResponse>() {


                    @Override
                    public void onNetworkError() {
                        DialogUtils.showAlert(main,getString(R.string.msg_network_lost));
                        super.onNetworkError();
                    }

                    @Override
                    protected void onError(String errorCode, String errorMessage) {
                        DialogUtils.showAlert(main,errorMessage);
                    }

                    @Override
                    protected void onSuccess(SingleFamilyResponse data) {
                        Toast.makeText(main, "onSuccess", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    private SyncDTO genSycnNewFamily(FamilyDTO familyDTO){
        SyncDTO syncDTO = new SyncDTO();
        syncDTO.setIDHO(familyDTO.getIDHO());
        syncDTO.setTHONGTINCHET(DeadDAO.getInstance().findById(familyDTO.getIDHO()));
        syncDTO.setTHONGTINHO(FamilyDetailDAO.getInstance().findById(familyDTO.getIDHO()));
        ArrayList<PeopleDTO> list = new ArrayList<>();
        list.add(PeopleDAO.getInstance().findById(familyDTO));
        syncDTO.setTHONGTINNHANKHAUHO(list);
        syncDTO.setTHONGTINTHANHVIEN(MemberDAO.getInstance().findByIdHo(familyDTO.getIDHO()));
        syncDTO.setTHONGTINPHUNU(WomanDAO.getInstance().findById(familyDTO.getIDHO()));
        syncDTO.setTHONGTINNHANKHAU(PeopleDetailDAO.getInstance().findById(familyDTO.getIDHO()));
        return syncDTO;
    }
    private void initNavigationBar() {
        navigationBar = main.getNavigationBar();
        navigationBar.reSetAll();
        navigationBar.hideImgright();
        navigationBar.hideImgLeft();
        navigationBar.setTitle(getString(R.string.txt_menu_interview));
    }

    @Override
    public void onResume() {
        super.onResume();
        initNavigationBar();
    }
}
