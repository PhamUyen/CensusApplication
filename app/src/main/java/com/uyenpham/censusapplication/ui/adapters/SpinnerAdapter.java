package com.uyenpham.censusapplication.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.locality.SpinnerDTO;

import java.util.ArrayList;

public class SpinnerAdapter  extends ArrayAdapter<String>{

    private final LayoutInflater mInflater;
    private final ArrayList<SpinnerDTO> items;

    public SpinnerAdapter(Context context,ArrayList<SpinnerDTO> list) {
        super(context,R.layout.item_list_spinner);
        mInflater = LayoutInflater.from(context);
        items = list;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(R.layout.item_list_spinner, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.text);

        SpinnerDTO religionDTO = items.get(position);

        offTypeTv.setText(religionDTO.getId() + " - " + religionDTO.getName());
        return view;
    }
    @Override
    public int getCount() {
        return (items.size());
    }
}
