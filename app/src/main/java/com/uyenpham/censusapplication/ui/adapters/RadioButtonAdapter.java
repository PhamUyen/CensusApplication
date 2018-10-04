package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRadioButtonClick;

import java.util.ArrayList;

public class RadioButtonAdapter extends RecyclerView.Adapter<RadioButtonAdapter.LocalityViewHolder> {

    private ArrayList<OptionDTO> listOption;
    private IRadioButtonClick listener;

    public RadioButtonAdapter(ArrayList<OptionDTO> listOption){
        this.listOption = listOption;
    }

    @NonNull
    @Override
    public RadioButtonAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_radio_group, parent,
                false);
        return new RadioButtonAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final RadioButtonAdapter.LocalityViewHolder holder, final int position) {
        holder.rb.setText(listOption.get(position).getOption());
        holder.rb.setChecked(listOption.get(position).isSelected());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(listener != null){
                   listener.onRadioClick(position,listOption.get(position).isSelected());
                   notifyDataSetChanged();
               }
            }
        });
        holder.rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(listener != null){
                    listener.onRadioClick(position,listOption.get(position).isSelected());
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOption.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rb;
        private RelativeLayout item;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            rb = itemView.findViewById(R.id.radio_btn);
            item = itemView.findViewById(R.id.item_view);
        }
    }
    public void setListener(IRadioButtonClick listener) {
        this.listener = listener;
    }
}
