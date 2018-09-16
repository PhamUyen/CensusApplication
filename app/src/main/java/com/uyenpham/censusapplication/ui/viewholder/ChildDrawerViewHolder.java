package com.uyenpham.censusapplication.ui.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.interfaces.IChildDrawerClick;

public class ChildDrawerViewHolder extends ChildViewHolder {

    private TextView childTextView;
    private TextView tvChildDetail;
    private LinearLayout contentChild;
    private IChildDrawerClick listener;

    public ChildDrawerViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.tv_child_name);
        tvChildDetail = itemView.findViewById(R.id.tv_child_info);
        contentChild = itemView.findViewById(R.id.content_child);
    }

    public void setChildInfo(String name,String info) {
        childTextView.setText(name);
        tvChildDetail.setText(info);
    }

    public void setEvenClickItem(final QuestionDTO questionDTO){
        contentChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClick(questionDTO);
                }
            }
        });
    }

    public void setListener(IChildDrawerClick listener) {
        this.listener = listener;
    }
}
