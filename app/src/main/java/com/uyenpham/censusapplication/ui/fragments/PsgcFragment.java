package com.uyenpham.censusapplication.ui.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.Answer;
import com.uyenpham.censusapplication.models.Barangay;
import com.uyenpham.censusapplication.models.Municipality;
import com.uyenpham.censusapplication.models.Province;
import com.uyenpham.censusapplication.models.Question;
import com.uyenpham.censusapplication.models.Region;
import com.uyenpham.censusapplication.models.Submission;
import com.uyenpham.censusapplication.models.Survey;
import com.uyenpham.censusapplication.ui.adapters.BarangayArrayAdapter;
import com.uyenpham.censusapplication.ui.adapters.MunicipalityArrayAdapter;
import com.uyenpham.censusapplication.ui.adapters.ProvinceArrayAdapter;
import com.uyenpham.censusapplication.ui.adapters.RegionArrayAdapter;

import ca.dalezak.androidbase.annotations.Control;
import ca.dalezak.androidbase.annotations.Type;
import ca.dalezak.androidbase.utils.Log;
import ca.dalezak.androidbase.utils.Strings;
import ca.dalezak.androidbase.utils.Toast;

@Type(PsgcFragment.TYPE)
public class PsgcFragment extends WidgetFragment {
    public static final String TYPE = "psgc";

    @Control("spinner_regions")
    public Spinner spinnerRegions;

    @Control("spinner_provinces")
    public Spinner spinnerProvinces;

    @Control("spinner_municipalities")
    public Spinner spinnerMunicipalities;

    @Control("spinner_barangays")
    public Spinner spinnerBarangays;

    private RegionArrayAdapter regionArrayAdapter;
    private ProvinceArrayAdapter provinceArrayAdapter;
    private MunicipalityArrayAdapter municipalityArrayAdapter;
    private BarangayArrayAdapter barangayArrayAdapter;

    public PsgcFragment() {
        super(R.layout.fragment_psgc, 0);
    }


    @Override
    public boolean load(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "load %d %s %s", question.cid, question.name, answer.value);
        regionArrayAdapter = new RegionArrayAdapter(getActivity());
        provinceArrayAdapter = new ProvinceArrayAdapter(getActivity());
        municipalityArrayAdapter = new MunicipalityArrayAdapter(getActivity());
        barangayArrayAdapter = new BarangayArrayAdapter(getActivity());

        spinnerRegions.setOnItemSelectedListener(null);
        spinnerProvinces.setOnItemSelectedListener(null);
        spinnerMunicipalities.setOnItemSelectedListener(null);
        spinnerBarangays.setOnItemSelectedListener(null);

        Region region = Region.all().get(0);
        Province province = null;
        Municipality municipality = null;
        Barangay barangay = null;
        if (!Strings.isNullOrEmpty(answer.value)) {
            Integer psgc = Integer.parseInt(answer.value);
            province = Province.find(psgc);
            municipality = Municipality.find(psgc);
            barangay = Barangay.find(psgc);
            if (barangay != null) {
                municipality = barangay.municipality;
                province = municipality.province;
            }
            else if (municipality != null) {
                province = municipality.province;
            }
        }

        provinceArrayAdapter.setRegion(region);
        municipalityArrayAdapter.setProvince(province);
        barangayArrayAdapter.setMunicipality(municipality);

        spinnerRegions.setAdapter(regionArrayAdapter);
        spinnerProvinces.setAdapter(provinceArrayAdapter);
        spinnerMunicipalities.setAdapter(municipalityArrayAdapter);
        spinnerBarangays.setAdapter(barangayArrayAdapter);

        int position = provinceArrayAdapter.getPosition(province);
        spinnerProvinces.setSelection(position, false);
        Log.i(this, "Province %d %s", position, province);

        position = municipalityArrayAdapter.getPosition(municipality);
        spinnerMunicipalities.setSelection(position, false);
        Log.i(this, "Municipality %d %s", position, municipality);

        position = barangayArrayAdapter.getPosition(barangay);
        spinnerBarangays.setSelection(position, false);
        Log.i(this, "Barangay %d %s", position, barangay);

        spinnerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Region region = regionArrayAdapter.getItem(position);
                provinceArrayAdapter.setRegion(region);
                Log.i(this, "onItemSelected Region %s", region);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                provinceArrayAdapter.setRegion(null);
                Log.i(this, "onNothingSelected Region");
            }
        });
        spinnerProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Province province = provinceArrayAdapter.getItem(position);
                municipalityArrayAdapter.setProvince(province);
                spinnerMunicipalities.setSelection(0, false);
                spinnerBarangays.setSelection(0, false);
                Log.i(this, "onItemSelected Province %s", province);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                municipalityArrayAdapter.setProvince(null);
                Log.i(this, "onNothingSelected Province");
            }
        });
        spinnerMunicipalities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Municipality municipality = municipalityArrayAdapter.getItem(position);
                barangayArrayAdapter.setMunicipality(municipality);
                spinnerBarangays.setSelection(0, false);
                Log.i(this, "onItemSelected Municipality %s", municipality);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                barangayArrayAdapter.setMunicipality(null);
                Log.i(this, "onNothingSelected Municipality");
            }
        });
        spinnerBarangays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Barangay barangay = barangayArrayAdapter.getItem(position);
                Log.i(this, "onItemSelected Barangay %s", barangay);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(this, "onNothingSelected Barangay");
            }
        });

        return true;
    }

    @Override
    public boolean save(Survey survey, Question question, Submission submission, Answer answer) {
        Log.i(this, "save %d %s %s", question.cid, question.name, answer.value);
        if (spinnerBarangays.getSelectedItemPosition() > 0) {
            int position = spinnerBarangays.getSelectedItemPosition();
            Barangay barangay = barangayArrayAdapter.getItem(position);
            if (barangay != null) {
                answer.value = barangay.psgc.toString();
            }
        }
        else if (spinnerMunicipalities.getSelectedItemPosition() > 0) {
            int position = spinnerMunicipalities.getSelectedItemPosition();
            Municipality municipality = municipalityArrayAdapter.getItem(position);
            if (municipality != null) {
                answer.value = municipality.psgc.toString();
            }
        }
        else if (spinnerProvinces.getSelectedItemPosition() > 0) {
            int position = spinnerProvinces.getSelectedItemPosition();
            Province province = provinceArrayAdapter.getItem(position);
            if (province != null) {
                answer.value = province.psgc.toString();
            }
        }
        else if (spinnerRegions.getSelectedItemPosition() > -1) {
            int position = spinnerRegions.getSelectedItemPosition();
            Region region = regionArrayAdapter.getItem(position);
            if (region != null) {
                answer.value = region.psgc.toString();
            }
        }
        else {
            answer.value = null;
        }
        Log.i(this, "Answer %s", answer.value);
//        answer.save();
        if (question.required && Strings.isNullOrEmpty(answer.value)) {
            Toast.showShort(getActivity(), R.string.psgc_required);
            return false;
        }
        return true;
    }
}