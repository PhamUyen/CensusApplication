package com.uyenpham.censusapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;

import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.LocalityViewHolder> {

    private ArrayList<FamilyDTO> listFamily;
    private IRecyclerViewListener listener;
    private IFamilyClick itemListener;

    public FamilyAdapter(ArrayList<FamilyDTO> listLocality) {
        this.listFamily = listLocality;
    }

    @NonNull
    @Override
    public FamilyAdapter.LocalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_family, parent,
                false);
        return new FamilyAdapter.LocalityViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyAdapter.LocalityViewHolder holder, final int position) {
        FamilyDTO family = listFamily.get(position);
        String info = family.getIDDB()+ " : " + mapState(family.getTINHTRANGHO()) + " - "
                + mapName(family.getTENCHUHO())+" - "+mapAddress(family.getDIACHI());
        holder.tvFamily.setText(info);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClickItem(view, position);
                }
            }
        });
        holder.btnInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemListener != null){
                    itemListener.onInterviewClick(position);
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemListener != null){
                    itemListener.onDeleteClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFamily.size();
    }

    public class LocalityViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFamily;
        private LinearLayout item;
        private Button btnInterview;
        private Button btnDelete;
        public LocalityViewHolder(View itemView) {
            super(itemView);
            tvFamily = itemView.findViewById(R.id.tv_family);
            item = itemView.findViewById(R.id.item_family);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnInterview = itemView.findViewById(R.id.btn_interview_family);
        }
    }
    public void setListener(IRecyclerViewListener listener) {
        this.listener = listener;
    }

    public void setItemListener(IFamilyClick itemListener) {
        this.itemListener = itemListener;
    }

    public interface IFamilyClick{
        void onInterviewClick(int position);
        void onDeleteClick(int position);
    }
    private String mapState(long state){
        if(state == 1){
            return Constants.STATE_STAY_LOCALITY;
        }
        return "";
    }
    private String mapName(String name){
        return "Chủ hộ : "+name;
    }
    private String mapAddress(String add){
        return "Địa chỉ : "+ add;
    }
}
