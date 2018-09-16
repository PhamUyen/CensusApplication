package com.uyenpham.censusapplication.models.drawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;

import java.util.ArrayList;

public class GroupDrawer extends ExpandableGroup<QuestionDTO> {
    String name;
    private ArrayList<QuestionDTO> listItem;

    public GroupDrawer(String name, ArrayList<QuestionDTO> listItem) {
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

    public ArrayList<QuestionDTO> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<QuestionDTO> listItem) {
        this.listItem = listItem;
    }
}
