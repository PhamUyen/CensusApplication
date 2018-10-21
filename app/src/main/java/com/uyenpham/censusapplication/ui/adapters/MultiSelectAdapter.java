package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;

public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.LocalityViewHolder> {

    private ArrayList<MemberDTO> listText;
    private IRecyclerViewListener listener;
    private boolean isSelect;

    public MultiSelectAdapter(ArrayList<MemberDTO> listLocality, boolean isSelect) {
        this.listText = listLocality;
        this.isSelect = isSelect;
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
        holder.tvText.setText(listText.get(position).getmC01());
        if(isSelect){
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.imvClose.setVisibility(View.GONE);
            holder.checkBox.setChecked(listText.get(position).isSlected());
        }else {
            holder.checkBox.setVisibility(View.GONE);
            holder.imvClose.setVisibility(View.VISIBLE);
        }
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
        return listText.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        private CheckBox checkBox;
        private RelativeLayout item;
        private ImageView imvClose;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.text);
            checkBox = itemView.findViewById(R.id.checkbox);
            item = itemView.findViewById(R.id.item_view);
            imvClose = itemView.findViewById(R.id.imvClose);
        }
    }
    public void setListener(IRecyclerViewListener listener) {
        this.listener = listener;
    }
}
