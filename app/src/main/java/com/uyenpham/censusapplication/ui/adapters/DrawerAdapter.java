package com.uyenpham.censusapplication.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.interfaces.IChildDrawerClick;
import com.uyenpham.censusapplication.ui.viewholder.ChildDrawerViewHolder;
import com.uyenpham.censusapplication.ui.viewholder.GroupDrawerViewHolder;
import com.uyenpham.censusapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends ExpandableRecyclerViewAdapter<GroupDrawerViewHolder, ChildDrawerViewHolder> {
    private IChildDrawerClick listener;
    private ArrayList<QuestionDTO> listQuest = new ArrayList<>();

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

        final QuestionDTO question = ((GroupDrawer) group).getItems().get(childIndex);
        AnswerDTO answerDTO = AnswerDAO.getInstance().findById(question.getId(),Constants.mStaticObject.getIdHo());
        holder.setChildInfo(question.getQuestion(), (answerDTO== null || answerDTO.getAnswerString() == null) ? "Chưa xác định" : (String)answerDTO.getAnswerString());
        holder.setListener(listener);
        holder.setEvenClickItem(question);
    }

    @Override
    public void onBindGroupViewHolder(GroupDrawerViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {

        holder.setGenreTitle(group);
        if(group.getItems()!= null && group.getItems().size() >0){
                listQuest.addAll(group.getItems());
        };
    }

    public void setListener(IChildDrawerClick listener) {
        this.listener = listener;
    }

    public ArrayList<QuestionDTO> getListQuest() {
        return listQuest;
    }
}