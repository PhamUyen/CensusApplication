package com.uyenpham.censusapplication.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.uyenpham.censusapplication.R;

public class ChildDrawerViewHolder extends ChildViewHolder {

    private TextView childTextView;
    private TextView tvChildDetail;

    public ChildDrawerViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.tv_child_name);
        tvChildDetail = itemView.findViewById(R.id.tv_child_info);
    }

    public void setChildInfo(String name,String info) {
        childTextView.setText(name);
        tvChildDetail.setText(info);
    }
}
