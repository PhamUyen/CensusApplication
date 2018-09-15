package com.uyenpham.censusapplication.ui.viewholder;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import com.uyenpham.censusapplication.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class GroupDrawerViewHolder extends GroupViewHolder {

    private TextView genreName;
    private ImageView arrow;

    public GroupDrawerViewHolder(View itemView) {
        super(itemView);
        genreName = (TextView) itemView.findViewById(R.id.tv_group_name);
        arrow = (ImageView) itemView.findViewById(R.id.imv_arrow);
    }

    public void setGenreTitle(ExpandableGroup genre) {
            genreName.setText(genre.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
