package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.locality.LocalityDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;

public class LocalityAdapter extends RecyclerView.Adapter<LocalityAdapter.LocalityViewHolder> {

    private ArrayList<LocalityDTO> listLocality;
    private IRecyclerViewListener listener;

    public LocalityAdapter(ArrayList<LocalityDTO> listLocality) {
        this.listLocality = listLocality;
    }

    @NonNull
    @Override
    public LocalityAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locality, parent,
                false);
        return new LocalityAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalityAdapter.LocalityViewHolder holder, final int position) {
        LocalityDTO local = listLocality.get(position);
         holder.tvLocality.setText(local.getIDDB() + " : " + local.getTENDIABAN());
        holder.tvNumber.setText("Số hộ : " + local.getSOHO());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClickItem(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLocality.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocality;
        private TextView tvNumber;
        private LinearLayout item;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            tvLocality = itemView.findViewById(R.id.tv_locality);
            tvNumber = itemView.findViewById(R.id.tv_number);
            item = itemView.findViewById(R.id.item_locality);
        }
    }
    public void setListener(IRecyclerViewListener listener) {
        this.listener = listener;
    }
}
