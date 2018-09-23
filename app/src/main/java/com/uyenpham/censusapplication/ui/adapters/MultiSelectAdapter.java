package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.LocalityViewHolder> {

    private ArrayList<PeopleDetailDTO> listText;
    private IRecyclerViewListener listener;

    public MultiSelectAdapter(ArrayList<PeopleDetailDTO> listLocality) {
        this.listText = listLocality;
    }

    @NonNull
    @Override
    public MultiSelectAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_multiselect, parent,
                false);
        return new MultiSelectAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final MultiSelectAdapter.LocalityViewHolder holder, final int position) {
        holder.tvText.setText(listText.get(position).getQ1());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkBox.setChecked(!holder.checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listText.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        private CheckBox checkBox;
        private RelativeLayout item;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.text);
            checkBox = itemView.findViewById(R.id.checkbox);
            item = itemView.findViewById(R.id.item_view);
        }
    }
    public void setListener(IRecyclerViewListener listener) {
        this.listener = listener;
    }
}
