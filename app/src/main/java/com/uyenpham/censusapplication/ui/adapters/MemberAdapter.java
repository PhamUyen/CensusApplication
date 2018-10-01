package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.LocalityViewHolder> {

    private ArrayList<PeopleDetailDTO> listText;
    private IRecyclerViewListener listener;

    public MemberAdapter(ArrayList<PeopleDetailDTO> listLocality) {
        this.listText = listLocality;
    }

    @NonNull
    @Override
    public MemberAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_text, parent,
                false);
        return new MemberAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.LocalityViewHolder holder, final int position) {
        holder.tvText.setText(listText.get(position).getQ1());
            holder.icon.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listText.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        private ImageView icon;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.text);
            icon = itemView.findViewById(R.id.imv_icon);
        }
    }
    public void setListener(IRecyclerViewListener listener) {
        this.listener = listener;
    }

}
