package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.ui.interfaces.IClearListener;

import java.util.ArrayList;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.LocalityViewHolder> {

    private ArrayList<String> listText;
    private IClearListener listener;

    public TextAdapter(ArrayList<String> listLocality) {
        this.listText = listLocality;
    }

    @NonNull
    @Override
    public TextAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_text, parent,
                false);
        return new TextAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.LocalityViewHolder holder, final int position) {
        holder.tvText.setText(listText.get(position));
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClear(position);
                }
            }
        });
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
    public void setListener(IClearListener listener) {
        this.listener = listener;
    }
}
