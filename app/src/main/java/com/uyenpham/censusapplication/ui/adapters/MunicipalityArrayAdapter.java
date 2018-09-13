package com.uyenpham.censusapplication.ui.adapters;

import android.content.Context;

import com.uyenpham.censusapplication.models.Municipality;
import com.uyenpham.censusapplication.models.Province;

import java.util.ArrayList;
import java.util.List;

import ca.dalezak.androidbase.adapters.BaseArrayAdapter;

public class MunicipalityArrayAdapter extends BaseArrayAdapter<Municipality> {

    private Province province;

    public MunicipalityArrayAdapter(Context context) {
        super(context, new Municipality());
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
        notifyDataSetChanged();
    }

    @Override
    public List<Municipality> getItems() {
        if (province != null && !province.isBlank()) {
            return province.municipalities();
        }
        return new ArrayList<>();
    }
}