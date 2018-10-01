package com.uyenpham.censusapplication.ui.fragments;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.AnswerDAO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.ui.activities.MemberActivity;
import com.uyenpham.censusapplication.ui.adapters.MultiSelectAdapter;
import com.uyenpham.censusapplication.ui.adapters.RadioButtonAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRadioButtonClick;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;

import java.util.ArrayList;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class SingleSelectFragment extends BaseTypeFragment implements IRecyclerViewListener,
        INextQuestion, IPreviousQuestion, IRadioButtonClick{
    @Bind(R.id.scroll_view)
    ScrollView scrollView;

    @Bind(R.id.radio_group)
    RecyclerView radioGroup;

    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.ed_other)
    EditText edOther;

    @Bind(R.id.rcv_select)
    RecyclerView rcvSelect;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<PeopleDetailDTO> listMember;
    private MultiSelectAdapter adapter;
    private ArrayList<PeopleDetailDTO> listSelected;
    private boolean isYes;
    private ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter radioButtonAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_selection;
    }

    @Override
    protected void createView(View view) {
        radioGroup.removeAllViews();
        activity.setiNext(this);
        activity.setiPrevious(this);
        listSelected = new ArrayList<>();
        listMember = new ArrayList<>();
        if(questionDTO.getType()== Constants.TYPE_SINGLE_SELECT_LIST){
            adapter = new MultiSelectAdapter(listMember, true);
        }else if(questionDTO.getType() == Constants.TYPE_MIX){
            adapter = new MultiSelectAdapter(listMember, false);
        }
        loadQuestion(questionDTO);
    }
    public boolean loadQuestion(final QuestionDTO question){
        answerDTO = AnswerDAO.getInstance().findById(question.getId(),Constants.mStaticObject.getIdHo());
        tvQuestion.setText(question.getName()+"."+question.getQuestion());
        listOption = question.getOptions();
        if(listOption.size()>0){
            if(answerDTO.getAnswerString() != null){
                listOption.get(answerDTO.getAnswerInt()-1).setSelected(true);
            }
            radioButtonAdapter = new RadioButtonAdapter(listOption);
            if(listOption.size() >2){
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                radioGroup.setLayoutManager(linearLayoutManager);
            }else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                radioGroup.setLayoutManager(gridLayoutManager);
            }
            radioGroup.setAdapter(radioButtonAdapter);
            radioButtonAdapter.setListener(this);
        }else {
            radioGroup.setVisibility(View.GONE);
            rcvSelect.setVisibility(View.VISIBLE);
            setupListSelected();
        }

        return true;
    }

    private void setupListSelected(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSelect.setLayoutManager(linearLayoutManager);
        listMember = Constants.mStaticObject.getPeopleDetailDTO();
        adapter = new MultiSelectAdapter(listMember, true);
        adapter.setListener(this);
        rcvSelect.setAdapter(adapter);
    }
    private void setupListAdded(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSelect.setLayoutManager(linearLayoutManager);
        adapter = new MultiSelectAdapter(listMember, false);
        adapter.setListener(this);
        rcvSelect.setAdapter(adapter);
    }

    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getSurvey()){
            case Constants.SURVEY_PEOPLE:
                if((isYes && listSelected.size() == 0)|| (Constants.mStaticObject.getPeopleDTO().get(questionDTO.getName()) == null || (isYes && listMember.size() == 0))){
                    return false;
                }else {
                    return true;
                }

        }
        return true;
    }

    protected RadioButton getRadioButton(Integer id, String text, String tag, String value) {
        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setId(id);
        radioButton.setText(text);
        radioButton.setTag(tag);
        radioButton.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_tiny);
        radioButton.setLayoutParams(getLayoutParams(margin,margin/2, margin, margin/2));
        radioButton.setButtonDrawable(R.drawable.bg_radio_button);
        radioButton.setPadding(margin,0,0,0);
        if (value != null && value.equals(tag)) {
            radioButton.setChecked(true);
        }
        else {
            radioButton.setChecked(false);
        }
        return radioButton;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }

    @Override
    public void onClickItem(View view, int postion) {
        PeopleDetailDTO peopleSelected= listMember.get(postion);
        if(peopleSelected.isSelected()){
            peopleSelected.setSelected(false);
            if(listSelected.contains(peopleSelected)){
                listSelected.remove(peopleSelected);
            }
        }else {
            peopleSelected.setSelected(true);
            addListNotDuplicate(peopleSelected);
        }
        adapter.notifyDataSetChanged();
    }

    private void addListNotDuplicate(PeopleDetailDTO people){
        if(listSelected != null && listSelected.size() > 0){
            if(!listSelected.contains(people)){
                listSelected.add(people);
            }
        }else {
            listSelected.add(people);
        }
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }

    @Override
    public void next() {
        if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
            if(questionDTO.getId().equals(Constants.QUESTION_Q9)){
//                ArrayList<GroupDrawer> list = new ArrayList<>();
//                for(PeopleDetailDTO peopleDetailDTO : Constants.mStaticObject.getPeopleDetailDTO()){
//                    GroupDrawer drawer = new GroupDrawer(peopleDetailDTO.getQ1(), null);
//                    list.add(drawer);
//                }
//                activity.setList(list);
//                activity.getNavigationBar().setTitle(list.get(0).getName());
//                nextFragment();
                Intent intent = new Intent(activity, MemberActivity.class);
                intent.putExtra("survey","member");
                if(Constants.mStaticObject.getPeopleDetailDTO().size() >0){
                    startActivity(intent);
                }else {
                    nextFragment();
                }
            }else {
                if(validateQuaetion(questionDTO,answerDTO)){
                    if(questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST){
                        for(PeopleDetailDTO peopleDetailDTO :listSelected){
                            Constants.mStaticObject.getPeopleDetailDTO().remove(peopleDetailDTO);
                        }
                    }else if(questionDTO.getType() == Constants.TYPE_MIX){
                        Constants.mStaticObject.getPeopleDetailDTO().addAll(listMember);
                    }
                    nextFragment();
                }else {
                    String message =activity.getString(R.string.txt_select_require);
                    if(questionDTO.getType() == Constants.TYPE_MIX){
                        message = activity.getString(R.string.txt_input_require);
                    }
                    DialogUtils.showErrorAlert(activity,message);
                }
            }

        }
}
    private void nextFragment(){
        if(AnswerDAO.getInstance().checkIsExistDB(answerDTO.getId())){
            AnswerDAO.getInstance().update(answerDTO);
        }else {
            AnswerDAO.getInstance().insert(answerDTO);
        }
        if (currentIndex < getListQuestion().size() - 1) {
            currentIndex++;
            replcaeFragmentByType(getListQuestion().get(currentIndex), true);
        }
    }

    @Override
    public void previuos() {
        if (currentIndex > 0) {
            currentIndex--;
            replcaeFragmentByType(getListQuestion().get(currentIndex), false);
        }
    }
    private void resetAllListOption(){
        for(OptionDTO optionDTO : listOption){
            optionDTO.setSelected(false);
        }
    }

    @Override
    public void onRadioClick(int pos, boolean isSelected) {
        resetAllListOption();
        OptionDTO option= listOption.get(pos);
        option.setSelected(!isSelected);
        radioButtonAdapter.notifyDataSetChanged();
        if(option.isSelected() && option.getType().toLowerCase().equals(Constants.TYPE_SHOW)){
            {
                isYes = true;
                if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                    Constants.mStaticObject.getPeopleDTO().set(questionDTO.getId(),pos+1);
                    answerDTO.setAnswerInt(1);
                }
                if(questionDTO.getType() == Constants.TYPE_MIX){
                    edOther.setVisibility(View.VISIBLE);
                    rcvSelect.setVisibility(View.VISIBLE);
                    setupListAdded();
                    edOther.setSingleLine();
                    edOther.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    edOther.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                                    int index = Constants.mStaticObject.getPeopleDetailDTO().size();
                                    PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
                                    peopleDetailDTO.setIDHO(Constants.mStaticObject.getIdHo());
                                    peopleDetailDTO.setHOSO(Constants.mStaticObject.getPeopleDTO().getHOSO());
                                    peopleDetailDTO.setQ1(edOther.getText().toString());
                                    peopleDetailDTO.setSTT(index);
                                    peopleDetailDTO.setChuho(1);
                                    peopleDetailDTO.setID(Constants.mStaticObject.getIdHo() + index);

                                    Constants.mStaticObject.getPeopleDetailDTO().add(peopleDetailDTO);
                                    listMember.add(peopleDetailDTO);
                                    adapter.notifyDataSetChanged();
                                }
                                edOther.setText(null);
                                return true;
                            }
                            return false;
                        }
                    });
                }else if(questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST){
                    rcvSelect.setVisibility(View.VISIBLE);
                    setupListSelected();
                }
            }
        }else {
            edOther.setVisibility(View.GONE);
            rcvSelect.setVisibility(View.GONE);
            if(questionDTO.getType() == Constants.TYPE_MIX){
                listMember.clear();
                adapter.notifyDataSetChanged();
                isYes = false;
            }else if(Constants.TYPE_SINGLE_SELECT_LIST == questionDTO.getType()){
                for(PeopleDetailDTO peopleDetailDTO : listMember){
                    peopleDetailDTO.setSelected(false);
                }
                adapter.notifyDataSetChanged();
                isYes = false;
                listSelected.clear();
            }
            if(questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)){
                Constants.mStaticObject.getPeopleDTO().set(questionDTO.getId(),pos+1);
                answerDTO.setAnswerInt(2);
            }
        }
    }
}
