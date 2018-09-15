package com.uyenpham.censusapplication.models.drawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;

public class GroupDrawer extends ExpandableGroup<ChildDrawer> {
    String name;
    private ArrayList<ChildDrawer> listItem;

    public GroupDrawer(String name, ArrayList<ChildDrawer> listItem) {
        super(name, listItem);
        this.name = name;
        this.listItem = listItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildDrawer> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<ChildDrawer> listItem) {
        this.listItem = listItem;
    }
}
