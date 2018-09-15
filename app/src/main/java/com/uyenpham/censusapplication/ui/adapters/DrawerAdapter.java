package com.uyenpham.censusapplication.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.ChildDrawer;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.ui.viewholder.ChildDrawerViewHolder;
import com.uyenpham.censusapplication.ui.viewholder.GroupDrawerViewHolder;

import java.util.List;

public class DrawerAdapter extends ExpandableRecyclerViewAdapter<GroupDrawerViewHolder, ChildDrawerViewHolder> {

    public DrawerAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public GroupDrawerViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        return new GroupDrawerViewHolder(view);
    }

    @Override
    public ChildDrawerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_child, parent, false);
        return new ChildDrawerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildDrawerViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final ChildDrawer artist = ((GroupDrawer) group).getItems().get(childIndex);
        holder.setChildInfo(artist.getName(), artist.getDetail());
    }

    @Override
    public void onBindGroupViewHolder(GroupDrawerViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setGenreTitle(group);
    }
}