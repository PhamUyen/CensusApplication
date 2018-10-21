package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.locality.DistrictDTO;
import com.uyenpham.censusapplication.models.locality.ProvinceDTO;
import com.uyenpham.censusapplication.models.locality.SpinnerDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.adapters.SpinnerAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;
import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.previousIndex;

public class ProvinceDistrictFragment extends BaseTypeFragment implements EditText
        .OnEditorActionListener,
        INextQuestion, IPreviousQuestion {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;
    @Bind(R.id.edCodeProvince)
    EditText edCodeProvince;
    @Bind(R.id.edCodeDistrict)
    EditText eeCodeDistrict;
    @Bind(R.id.spinnerDistrict)
    Spinner spinnerDistrict;
    @Bind(R.id.spinnerProvince)
    Spinner spinnerProvince;

    private List<DistrictDTO> listDistrict;
    private List<ProvinceDTO> listProvince;
    private int posMember;
    SpinnerAdapter adapterDistrict;
    private ArrayList<SpinnerDTO> listOptionSpinnerDistrict;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_province_district;
    }

    @Override
    public void initData() {
        activity.setiNext(this);
        activity.setiPrevious(this);
        posMember = getPosMember();
        listDistrict = new ArrayList<>();


        String jsonProvince= SharedPrefsUtils.getStringPreference(activity,Constants.KEY_PROVINCE);
        listProvince =new Gson().fromJson(jsonProvince, new TypeToken<List<ProvinceDTO>>(){}.getType());

        edCodeProvince.setOnEditorActionListener(this);
        eeCodeDistrict.setOnEditorActionListener(this);
        loadQuestion(questionDTO);
    }

    private void setSpinnerDistrict(){
        listOptionSpinnerDistrict = new ArrayList<>();
        for (int i = 0; i < listDistrict.size(); i++) {
            listOptionSpinnerDistrict.add(new SpinnerDTO(listDistrict.get(i).getDistrictCode(), listDistrict.get(i).getName()));
        }

        adapterDistrict = new SpinnerAdapter(activity, listOptionSpinnerDistrict);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                eeCodeDistrict.setText(listOptionSpinnerDistrict.get(i).getId());
                memberDTO.setmC10B(listOptionSpinnerDistrict.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
            }
        });
    }
    private void setSpinnerProvince(){
        final ArrayList<SpinnerDTO> listProvinceSpinner = new ArrayList<>();

        for (int i = 0; i < listProvince.size(); i++) {
            listProvinceSpinner.add(new SpinnerDTO(listProvince.get(i).getId(), listProvince.get(i).getName()));
        }

        SpinnerAdapter adapter = new SpinnerAdapter(activity, listProvinceSpinner);
        spinnerProvince.setAdapter(adapter);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edCodeProvince.setText(listProvinceSpinner.get(i).getId());
                memberDTO.setmC10A(listProvinceSpinner.get(i).getId());
                genListDistrict(listProvinceSpinner.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
            }
        });
    }

    private DistrictDTO getDistrictById(String id){
        for(DistrictDTO districtDTO : listDistrict){
            if(districtDTO.getDistrictCode().equalsIgnoreCase(id)){
                return districtDTO;
            }
        }
        return null;
    }
    private ProvinceDTO getProvinceById(String id){
        for(ProvinceDTO provinceDTO : listProvince){
            if(provinceDTO.getId().equalsIgnoreCase(id)){
                return provinceDTO;
            }
        }
        return null;
    }
    @Override
    public boolean loadQuestion(QuestionDTO question) {
        setContentQuestion(tvQuestion);
        edCodeProvince.setOnEditorActionListener(this);
        eeCodeDistrict.setOnEditorActionListener(this);
        setSpinnerDistrict();
        setSpinnerProvince();
        return true;
    }

    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        return null;
    }

    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
        switch (editText.getId()){
            case R.id.edCodeDistrict:
                if(memberDTO.getmC10A() == null){
                        DialogUtils.showAlert(activity,"Chọn tỉnh trước");
                }else {
                    DistrictDTO districtDTO = getDistrictById(editText.getText().toString());
                    if(districtDTO != null){
                        spinnerDistrict.setSelection(listDistrict.indexOf(districtDTO));
                        memberDTO.setmC10B(districtDTO.getDistrictCode());
                    }else {
                        DialogUtils.showAlert(activity,"Mã huyện không hợp lệ!");
                    }
                }
                break;
            case R.id.edCodeProvince:
                ProvinceDTO provinceDTO = getProvinceById(editText.getText().toString());
                if(provinceDTO != null){
                    spinnerDistrict.setSelection(listProvince.indexOf(provinceDTO));
                    memberDTO.setmC10A(provinceDTO.getId());
                    genListDistrict(provinceDTO.getId());
                }else {
                    DialogUtils.showAlert(activity,"Mã tỉnh không hợp lệ!");
                }
                break;
        }
        return false;
    }

    private void genListDistrict(String idProvince) {
        listDistrict.clear();
        listOptionSpinnerDistrict.clear();
        String jsonDistrict = SharedPrefsUtils.getStringPreference(activity, Constants.KEY_DISTRICT);

        ArrayList<DistrictDTO>listAllDistrict = new Gson().fromJson(jsonDistrict, new TypeToken<List<DistrictDTO>>() {
        }.getType());
        for(DistrictDTO districtDTO : listAllDistrict){
            if(districtDTO.getProvinceCode().equals(idProvince)){
                listDistrict.add(districtDTO);
            }
        }
        for (int i = 0; i < listDistrict.size(); i++) {
            listOptionSpinnerDistrict.add(new SpinnerDTO(listDistrict.get(i).getDistrictCode(), listDistrict.get(i).getName()));
        }
        adapterDistrict.notifyDataSetChanged();

    }
    @Override
    public void next() {
        Utils.hideKeyboard(activity, lnContent);
        if (validateQuaetion(questionDTO, null) == null) {
            Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
            if (currentIndex < getListQuestion().size() - 1) {
                saveAnswerToSurvey(questionDTO,posMember);
                previousIndex =currentIndex;
                currentIndex++;
                Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true, getListQuestion(), activity.mFragmentManager, getPosMember());
            }
        } else {
            WarningDTO warning = validateQuaetion(questionDTO, null);
            if (warning.getType() == Constants.TYPE_CONFIRM) {
                DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string.txt_yes, R.string.txt_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
                                if (currentIndex < getListQuestion().size() - 1) {
                                    saveAnswerToSurvey(questionDTO,posMember);
                                    previousIndex =currentIndex;
                                    currentIndex++;
                                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true, getListQuestion(), activity.mFragmentManager, getPosMember());
                                }
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dissmiss dialog
                            }
                        });
            } else {
                DialogUtils.showAlert(activity, warning.getMessage());
            }
        }
    }

    @Override
    public void previuos() {
        Utils.hideKeyboard(activity, lnContent);
        if (previousIndex != -1) {
            Utils.replcaeFragmentByType(getListQuestion().get(previousIndex), false, getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }

}
