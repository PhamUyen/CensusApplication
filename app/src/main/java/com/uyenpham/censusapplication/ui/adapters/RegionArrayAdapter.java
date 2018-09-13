package com.uyenpham.censusapplication.ui.adapters;

import android.content.Context;

import com.uyenpham.censusapplication.models.Region;

import java.util.List;

import ca.dalezak.androidbase.adapters.BaseArrayAdapter;

public class RegionArrayAdapter extends BaseArrayAdapter<Region> {

    public RegionArrayAdapter(Context context) {
        super(context);
    }

    @Override
    public List<Region> getItems() {
        return Region.all();
    }

}