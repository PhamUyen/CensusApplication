package com.uyenpham.censusapplication.ui.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.MemberDAO;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.locality.ReligionDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.adapters.MultiSelectAdapter;
import com.uyenpham.censusapplication.ui.adapters.RadioButtonAdapter;
import com.uyenpham.censusapplication.ui.adapters.SpinnerAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRadioButtonClick;
import com.uyenpham.censusapplication.ui.interfaces.IRecyclerViewListener;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.StringUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class SingleSelectFragment extends BaseTypeFragment implements IRecyclerViewListener,
        INextQuestion, IPreviousQuestion, IRadioButtonClick {
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

    @Bind(R.id.spinner)
    Spinner spinner;

    private QuestionDTO questionDTO;
    private AnswerDTO answerDTO;
    private ArrayList<PeopleDetailDTO> listMember;
    private MultiSelectAdapter adapter;
    private ArrayList<PeopleDetailDTO> listSelected;
    private boolean isYes;
    private ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter radioButtonAdapter;
    private int posMember;
    private MemberDTO memberDTO;
    private WomanDTO womanDTO;
    private DeadDTO deadDTO;
    private HouseDTO houseDTO;

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
        posMember = getPosMember();
        if (questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST) {
            adapter = new MultiSelectAdapter(listMember, true);
        } else if (questionDTO.getType() == Constants.TYPE_MIX) {
            adapter = new MultiSelectAdapter(listMember, false);
        }
        if (questionDTO.getSurvey().equals(Constants.SURVEY_MEMBER)) {
            memberDTO = Constants.mStaticObject.getMemberDTO().get(posMember);
        } else if (Constants.SURVEY_WOMAN.equals(questionDTO.getSurvey())) {
            womanDTO = Constants.mStaticObject.getWomanDTO().get(posMember);
        } else if (Constants.SURVEY_DEAD.equals(questionDTO.getSurvey())) {
            deadDTO = Constants.mStaticObject.getDeadDTO().get(posMember);
        }else if (Constants.SURVEY_HOUSE.equals(questionDTO.getSurvey())) {
            houseDTO = Constants.mStaticObject.getHouseDTO();
        }
        loadQuestion(questionDTO);
    }

    public boolean loadQuestion(final QuestionDTO question) {
        listOption = new ArrayList<>();
        tvQuestion.setText(question.getName() + "." + question.getQuestion());
        if (question.getId().equals(Constants.QUESTION_C02) && posMember == 0) {
            memberDTO.setmC02(1);
            listOption.add(new OptionDTO("CHỦ HỘ", "NORMAL", true));
            radioButtonAdapter = new RadioButtonAdapter(listOption);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            radioGroup.setLayoutManager(gridLayoutManager);
            radioGroup.setAdapter(radioButtonAdapter);
            radioButtonAdapter.setListener(this);
        } else {
            listOption = question.getOptions();
            if (!listOption.isEmpty()) {
                if (question.getSurvey().equals(Constants.SURVEY_MEMBER) && memberDTO != null &&
                        memberDTO.get(question.getId()) != null) {
                    listOption.get((int) memberDTO.get(question.getId()) - 1).setSelected(true);
                } else if (question.getSurvey().equals(Constants.SURVEY_WOMAN) && memberDTO !=
                        null && memberDTO.get(question.getId()) != null) {
                    listOption.get((int) womanDTO.get(question.getId()) - 1).setSelected(true);
                } else if (question.getSurvey().equals(Constants.SURVEY_DEAD) && memberDTO !=
                        null && memberDTO.get(question.getId()) != null) {
                    listOption.get((int) deadDTO.get(question.getId()) - 1).setSelected(true);
                }
                radioButtonAdapter = new RadioButtonAdapter(listOption);
                if (listOption.size() > 2) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

                    radioGroup.setLayoutManager(linearLayoutManager);
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                            2);
                    radioGroup.setLayoutManager(gridLayoutManager);
                }
                radioGroup.setAdapter(radioButtonAdapter);
                radioButtonAdapter.setListener(this);
            } else {
                radioGroup.setVisibility(View.GONE);
                rcvSelect.setVisibility(View.VISIBLE);
                setupListSelected();
            }
        }
        return true;
    }

    private void setupListSelected() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSelect.setLayoutManager(linearLayoutManager);
        listMember = Constants.mStaticObject.getPeopleDetailDTO();
        adapter = new MultiSelectAdapter(listMember, true);
        adapter.setListener(this);
        rcvSelect.setAdapter(adapter);
    }

    private void setupListAdded() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvSelect.setLayoutManager(linearLayoutManager);
        adapter = new MultiSelectAdapter(listMember, false);
        adapter.setListener(this);
        rcvSelect.setAdapter(adapter);
    }

    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        WarningDTO messge = null;
        switch (question.getSurvey()) {
            case Constants.SURVEY_PEOPLE:
                if (!isYes || (isYes && listSelected.size() != 0) || (Constants.mStaticObject.getPeopleDTO
                        ().get(questionDTO.getName()) != null || (isYes && listMember.size() != 0))) {
                } else {
                }
            case Constants.SURVEY_MEMBER:
            case Constants.SURVEY_WOMAN:
            case Constants.SURVEY_DEAD:
                String idTV = Constants.mStaticObject.getIdHo() + Constants.mStaticObject
                        .getPeopleDetailDTO().get(0).getSTT();
                MemberDTO chuho = MemberDAO.getInstance().findById(idTV);
                switch (question.getId()) {
                    case Constants.QUESTION_C02:
                        if(memberDTO.getmC02() == 1 && chuho.getmC22() ==1){
                            return new WarningDTO(getString(R.string.txt_invalid_relation,posMember+1,memberDTO.getmC01(),listOption.get(memberDTO.getmC02()-1),listOption.get(0).getOption()),Constants.TYPE_NOTI);
                        }else if(isOverNumberWife() && memberDTO.getmC02() ==1){
                            return new WarningDTO(getString(R.string.txt_number_couple,2),Constants.TYPE_CONFIRM);
                        }
                        break;
                    case Constants.QUESTION_C03:
                        if (chuho != null && chuho.getmC03() == memberDTO.getmC03() && memberDTO.getmC02() ==1) {
                            return new WarningDTO(getString(R.string.txt_invalid_sex,posMember+1,memberDTO.getmC01(),listOption.get(memberDTO.getmC03()-1).getOption(),listOption.get(memberDTO.getmC03()-1).getOption()),Constants.TYPE_NOTI);
                        }
                        break;

                    case Constants.QUESTION_C6A:
                        if (memberDTO.getmC6A() == 2 && StringUtils.isEmpty(memberDTO.getmC6B())) {
                            return new WarningDTO(getString(R.string.txt_empty_folk,posMember+1,memberDTO.getmC01()),Constants.TYPE_NOTI);
                        }
                        if(posMember != 0 && !memberDTO.getmC6C().equals(chuho.getmC6C())){
                            return new WarningDTO(getString(R.string.txt_invalid_folk,posMember+1,memberDTO.getmC01(),memberDTO.getmC6C(),chuho.getmC6C()),Constants.TYPE_NOTI);
                        }
                        break;
                    case Constants.QUESTION_C14:
                        if (memberDTO.getmC14() == 1 &&memberDTO.getmC05() == 60) {
                            return new WarningDTO(getString(R.string.txt_confirm_continue_study,posMember+1,
                                    memberDTO.getmC01(),memberDTO.getmC4T() == null ? 0: memberDTO.getmC4T(), memberDTO.getmC4N() == null ? 0: memberDTO.getmC4N()),Constants.TYPE_CONFIRM);
                        }
                    case Constants.QUESTION_C15:
                        if ((memberDTO.getmC4N() > 2012 && (memberDTO.getmC15() == 1 || memberDTO.getmC15() == 2))
                                || (memberDTO.getmC15() == 3 && ((memberDTO.getmC4N() <= 2012
                                && memberDTO.getmC4N() >= 2008)
                                || (memberDTO.getmC05() >= 6 && memberDTO.getmC05() <= 10)))
                                || (memberDTO.getmC15() == 4 && ((memberDTO.getmC4N() <= 2007
                                && memberDTO.getmC4N() >= 2004)
                                || (memberDTO.getmC05() >= 11 && memberDTO.getmC05() <= 14)))
                                || (memberDTO.getmC15() == 5 && ((memberDTO.getmC4N() <= 2003
                                && memberDTO.getmC4N() >= 2001)
                                || (memberDTO.getmC05() >= 15 && memberDTO.getmC05() <= 17)))
                                || (memberDTO.getmC15() == 6 && (memberDTO.getmC4N() <= 2007 ||
                                memberDTO.getmC05() >= 11))
                                || ((memberDTO.getmC15() > 6 && memberDTO.getmC15() < 12) &&
                                (memberDTO.getmC4N() > 2000 || memberDTO.getmC05() < 15))) {
                            return new WarningDTO(getString(R.string.txt_invalid_age_study,
                                    posMember+1,memberDTO.getmC01(),memberDTO.getmC4T(),memberDTO.getmC4N()
                            ,listOption.get(memberDTO.getmC15()-1).getOption()),Constants.TYPE_CONFIRM);
                        }
                        if(memberDTO.getmC13A() ==4 && memberDTO.getmC13B() ==4 && memberDTO.getmC13C() ==4
                                || memberDTO.getmC13D() ==4 || memberDTO.getmC13E() ==4 && memberDTO.getmC13F() ==4){
                            return new WarningDTO(getString(R.string.txt_invalid_study,
                                    posMember+1,memberDTO.getmC01()
                                    ,listOption.get(memberDTO.getmC15()-1).getOption()),Constants.TYPE_NOTI);
                        }
                    case Constants.QUESTION_C16:
                            return checkLevelStudy(memberDTO);
                    case Constants.QUESTION_C17:
                       if(memberDTO.getmC05() <8 && memberDTO.getmC17() ==1){
                           return new WarningDTO(getString(R.string.txt_error_job,
                                   posMember+1,memberDTO.getmC01(),memberDTO.getmC4T(),memberDTO.getmC4N()
                                   ,listOption.get(memberDTO.getmC15()-1).getOption()),Constants.TYPE_NOTI);
                       }
                        if(8 <memberDTO.getmC05() && memberDTO.getmC05() <15 && memberDTO.getmC17() ==1){
                            return new WarningDTO(getString(R.string.txt_warning_job,
                                    posMember+1,memberDTO.getmC01(),memberDTO.getmC4T(),memberDTO.getmC4N()
                                    ,listOption.get(memberDTO.getmC15()-1).getOption()),Constants.TYPE_CONFIRM);
                        }
                    case Constants.QUESTION_C18:
                        if (memberDTO.getmC14() == 3 && (memberDTO.getmC18() == 1 || memberDTO.getmC18() == 2)) {
                            return new WarningDTO(getString(R.string.txt_invalid_level_job,posMember+1,memberDTO.getmC01(),listOption.get(memberDTO.getmC17()-1).getOption()),Constants.TYPE_NOTI);
                        }
                        if((memberDTO.getmC16() ==1 || memberDTO.getmC16() ==2) &&(memberDTO.getmC18() ==4 || memberDTO.getmC18() ==5)){
                            return new WarningDTO(getString(R.string.txt_error_level_job,posMember+1, memberDTO.getmC01(),memberDTO.getmC16()
                            ,listOption.get(memberDTO.getmC18()-1).getOption()), Constants.TYPE_NOTI);
                        }
                    case Constants.QUESTION_C22:
                        if (memberDTO.getmSTTNKTT() != 1 && memberDTO.getmC02() == 2 && memberDTO.getmC22() != 2) {
                            return false;
                        } else if (memberDTO.getmC12() != null && memberDTO.getmC12() == 4 &&
                                memberDTO.getmC22() == 1) {
                            return false;
                        } else {
                            return true;
                        }

                }
        }
        return messge;
    }

    private WarningDTO checkLevelStudy(MemberDTO memberDTO){
        if ((memberDTO.getmC16() ==1 && memberDTO.getmC15() ==3)
        || (memberDTO.getmC16() == 2 && memberDTO.getmC15() == 4)
        || (memberDTO.getmC16() == 3  && memberDTO.getmC15() ==5)
        || (memberDTO.getmC16() ==4 && 7 <= memberDTO.getmC15() && memberDTO.getmC15() <=11)
        || (5<=memberDTO.getmC16() && memberDTO.getmC16() <= 7 && 7 <= memberDTO.getmC15() && memberDTO.getmC15() <=11)
        ||((memberDTO.getmC16() ==8 || memberDTO.getmC16() ==9) && (memberDTO.getmC15() == 9 || memberDTO.getmC15() ==10 || memberDTO.getmC15() ==11))){
            return new WarningDTO(getString(R.string.txt_invalid_max_study,posMember+1,memberDTO.getmC01(),
                    memberDTO.getmC4T(), memberDTO.getmC4N(),memberDTO.getmC05(),memberDTO.getmC15(),listOption.get(memberDTO.getmC16()-1).getOption()),Constants.TYPE_CONFIRM);
        }
        if ((memberDTO.getmC16() ==2 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=2009)
                || (memberDTO.getmC16() == 3 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=2005)
                || (memberDTO.getmC16() ==4 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=2002)
                || (memberDTO.getmC16() ==5 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=2005)
                || (memberDTO.getmC16() ==6 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=2000)
                ||(memberDTO.getmC16() ==7 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=1999)
                ||(memberDTO.getmC16() ==8 && memberDTO.getmC4N()!= null && memberDTO.getmC4N() <=1998)){
            return new WarningDTO(getString(R.string.txt_invalid_age_for_level,posMember+1,memberDTO.getmC01(),
                    memberDTO.getmC4T(), memberDTO.getmC4N(),memberDTO.getmC05(),listOption.get(memberDTO.getmC16()+1).getOption()),Constants.TYPE_NOTI);
        }
        if(memberDTO.getmC13A() ==4 && memberDTO.getmC13B() ==4 && memberDTO.getmC13C() ==4
                || memberDTO.getmC13D() ==4 || memberDTO.getmC13E() ==4 && memberDTO.getmC13F() ==4){
            return new WarningDTO(getString(R.string.txt_disabilities,
                    posMember+1,memberDTO.getmC01()
                    ,listOption.get(memberDTO.getmC16()-1).getOption()),Constants.TYPE_NOTI);
        }
        return null;

    }

    private boolean isOverNumberWife(){
        for(MemberDTO memberDTO1 : Constants.mStaticObject.getMemberDTO()){
            if(memberDTO1.getmC02() ==1){
                return true;
            }
        }
        return false;
    }

    protected RadioButton getRadioButton(Integer id, String text, String tag, String value) {
        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setId(id);
        radioButton.setText(text);
        radioButton.setTag(tag);
        radioButton.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_tiny);
        radioButton.setLayoutParams(getLayoutParams(margin, margin / 2, margin, margin / 2));
        radioButton.setButtonDrawable(R.drawable.bg_radio_button);
        radioButton.setPadding(margin, 0, 0, 0);
        if (value != null && value.equals(tag)) {
            radioButton.setChecked(true);
        } else {
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
        PeopleDetailDTO peopleSelected = listMember.get(postion);
        if (peopleSelected.isSelected()) {
            peopleSelected.setSelected(false);
            if (listSelected.contains(peopleSelected)) {
                listSelected.remove(peopleSelected);
            }
        } else {
            peopleSelected.setSelected(true);
            addListNotDuplicate(peopleSelected);
        }
        adapter.notifyDataSetChanged();
    }

    private void addListNotDuplicate(PeopleDetailDTO people) {
        if (listSelected != null && listSelected.size() > 0) {
            if (!listSelected.contains(people)) {
                listSelected.add(people);
            }
        } else {
            listSelected.add(people);
        }
    }

    @Override
    public void onLongClickItem(View v, int position) {

    }

    @Override
    public void next() {
        if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
            if (questionDTO.getId().equals(Constants.QUESTION_Q9)) {
                if (Constants.mStaticObject.getPeopleDetailDTO().size() > 0) {
                    activity.survey = Constants.SURVEY_MEMBER;
                    activity.isMember = true;
                    activity.setListPeople(0);
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getPeopleDetailDTO().get(0).getQ1());
                } else {
                    nextFragment();
                }
            } else {
                if (validateQuaetion(questionDTO, answerDTO) == null) {
                    if (questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST) {
                        for (PeopleDetailDTO peopleDetailDTO : listSelected) {
                            Constants.mStaticObject.getPeopleDetailDTO().remove(peopleDetailDTO);
                        }
                    } else if (questionDTO.getType() == Constants.TYPE_MIX) {
                        if(questionDTO.getId().equals(Constants.QUESTION_Q7)){
                            for (PeopleDetailDTO peopleDetailDTO : listSelected) {
                                DeadDTO deadDTO = new DeadDTO(peopleDetailDTO.getQ1(),peopleDetailDTO.getIDHO()+peopleDetailDTO.getSTT());
                                Constants.mStaticObject.getDeadDTO().add(deadDTO);
                            }
                        }
                        Constants.mStaticObject.getPeopleDetailDTO().addAll(listMember);
                    }
                    nextFragment();
                } else {
                    String message = activity.getString(R.string.txt_select_require);
                    if (questionDTO.getType() == Constants.TYPE_MIX) {
                        message = activity.getString(R.string.txt_input_require);
                    }
                    DialogUtils.showErrorAlert(activity, message);
                }
            }

        } else if (Constants.SURVEY_HOUSE.equals(questionDTO.getSurvey())) {

        } else {
            if (validateQuaetion(questionDTO, answerDTO)== null) {
                switch (questionDTO.getSurvey()) {
                    case Constants.SURVEY_MEMBER:
                        Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
                        break;
                    case Constants.SURVEY_WOMAN:
                        Constants.mStaticObject.getWomanDTO().set(posMember, womanDTO);
                        break;
                    case Constants.SURVEY_DEAD:
                        Constants.mStaticObject.getDeadDTO().set(posMember, deadDTO);
                        break;
                    default:
                        break;
                }
                nextFragment();
            }
        }
    }

    private void nextFragment() {
        if (currentIndex < getListQuestion().size() - 1) {
            if (Constants.SURVEY_MEMBER.equals(questionDTO.getSurvey()) || Constants.SURVEY_WOMAN
                    .equals(questionDTO.getSurvey()) || Constants.SURVEY_DEAD.equals(questionDTO
                    .getSurvey())) {
                currentIndex = getStep(questionDTO, answerDTO);
            } else {
                currentIndex++;
            }

            if (currentIndex == -1) {
                nextMember();
            } else {
                if (currentIndex < getListQuestion().size()) {
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                } else {
                    nextMember();
                }
            }
        }
    }

    private void nextMember() {
        posMember++;
        setPosMember(posMember);
        switch (questionDTO.getSurvey()) {
            case Constants.SURVEY_MEMBER:
                if (posMember < Constants.mStaticObject.getMemberDTO().size()) {
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getPeopleDetailDTO().get(posMember).getQ1());
                } else {
                    if (Constants.mStaticObject.getWomanDTO().size() > 0) {
                        activity.survey = Constants.SURVEY_WOMAN;
                        activity.isMember = true;
                        activity.setListPeople(posMember);
                        activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                .getWomanDTO().get(0).getTenTV());
                    } else {
                        if (Constants.mStaticObject.getDeadDTO().size() > 0) {
                            activity.survey = Constants.SURVEY_DEAD;
                            activity.isMember = true;
                            activity.setListPeople(posMember);
                            activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                    .getDeadDTO().get(0).getmC43());
                        } else {
                            if (currentIndex < getListQuestion().size()) {
                                activity.makeListQuestion();
                                currentIndex =22;
                                Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                                        getListQuestion(), activity.mFragmentManager, -1);
                            }
                        }
                    }
                }
                break;
            case Constants.SURVEY_WOMAN:
                if (posMember < Constants.mStaticObject.getWomanDTO().size()) {
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getWomanDTO().get(posMember).getTenTV());
                } else {
                    if (Constants.mStaticObject.getDeadDTO().size() > 0) {
                        activity.survey = Constants.SURVEY_DEAD;
                        activity.isMember = true;
                        activity.setListPeople(posMember);
                        activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                .getDeadDTO().get(0).getmC43());
                    } else {
                        if (currentIndex < getListQuestion().size()) {
                            activity.makeListQuestion();
                            currentIndex =22;
                            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                                    getListQuestion(), activity.mFragmentManager, -1);
                        }
                    }
                }
                break;
            case Constants.SURVEY_DEAD:
                if (posMember < Constants.mStaticObject.getDeadDTO().size()) {
                    currentIndex = 0;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, getPosMember());
                    activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                            .getDeadDTO().get(posMember).getmC43());
                } else {
                    activity.makeListQuestion();
                    currentIndex =22;
                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                            getListQuestion(), activity.mFragmentManager, -1);
                }
                break;
            default:
                break;
        }

    }

    private int getStep(QuestionDTO question, AnswerDTO answerDTO) {
        int index = currentIndex;
        switch (question.getId()) {
            case Constants.QUESTION_C7A:
                if (memberDTO.getmC05() < 5 || (memberDTO.getmC4N() != 9998 && memberDTO.getmC4N
                        () > 2013)
                        || (memberDTO.getmC4N() == 2013 && memberDTO.getmC4T() > Calendar
                        .getInstance().get(Calendar.MONTH))) {
                    index++;
                } else {
                    index += 2;
                }
                break;
            case Constants.QUESTION_C09:
                if (memberDTO.getmC09() == 1 || memberDTO.getmC09() == 2) {
                    index += 4;
                } else if (memberDTO.getmC09() == 3) {
                    index += 2;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C14:
                if (memberDTO.getmC14() == 1) {
                    index++;
                } else if (memberDTO.getmC14() == 2) {
                    index += 2;
                } else {
                    index += 3;
                }
                break;
            case Constants.QUESTION_C15:
                if (memberDTO.getmC15() == 1 || memberDTO.getmC15() == 2) {
                    index += 6;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C17:
                if (memberDTO.getmC17() == 2) {
                    index = KT3(index);
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C18:
                index = KT2(index);
                break;
            case Constants.QUESTION_C22:
                if (memberDTO.getmC22() == 1) {
                    index += 3;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C25:
                if (memberDTO.getmC25() == 1) {
                    index += 3;
                } else if (memberDTO.getmC25() == 3) {
                    index = -1;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C26:
                if (memberDTO.getmC26() == 1) {
                    index++;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C30:
                index = -1;
                break;
            case Constants.QUESTION_C31:
                if (memberDTO.getmC31() == 1) {
                    index += 2;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C34:
                if (womanDTO.getC34() == 2) {
                    index = -1;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C50:
                if (houseDTO.getC50() == 2 || houseDTO.getC50() == 3) {
                    index += 10;
                } else {
                    index++;
                }
                break;
            case Constants.QUESTION_C51:
                if (houseDTO.getC51() == 2) {
                    index += 4;
                } else {
                    index++;
                }
                break;

            default:
                index++;
                break;

        }
        return index;
    }

    private int KT3(int currentIndex) {
        if (memberDTO.getmC16() != null && memberDTO.getmC16() == 1) {
            return currentIndex += 4;
        } else {
            return KT4(currentIndex);
        }
    }

    private int KT2(int index) {
        if (memberDTO.getmC14() != null && memberDTO.getmC14() == 3) {
            return index += 3;
        } else {
            return index++;
        }
    }

    private int KT4(int index) {
        if (memberDTO.getmC05() != null && memberDTO.getmC05() >= 15) {
            return index += 4;
        } else {
            return -1;
        }
    }

    @Override
    public void previuos() {
        if (currentIndex > 0) {
            currentIndex--;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), false,
                    getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }

    private void resetAllListOption() {
        for (OptionDTO optionDTO : listOption) {
            optionDTO.setSelected(false);
        }
    }

    @Override
    public void onRadioClick(int pos, boolean isSelected) {
        resetAllListOption();
        OptionDTO option = listOption.get(pos);
        option.setSelected(!isSelected);
        radioGroup.post(new Runnable() {
            @Override
            public void run() {
                radioButtonAdapter.notifyDataSetChanged();
            }
        });
        if (option.isSelected() && option.getType().toLowerCase().equals(Constants.TYPE_SHOW)) {
            {
                isYes = true;
                if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
                    Constants.mStaticObject.getPeopleDTO().set(questionDTO.getId(), pos + 1);
                } else if (Constants.SURVEY_MEMBER.equals(questionDTO.getSurvey())) {
                    memberDTO.set(questionDTO.getId(), pos + 1);
                }
                if (questionDTO.getType() == Constants.TYPE_MIX) {
                    edOther.setVisibility(View.VISIBLE);
                    rcvSelect.setVisibility(View.VISIBLE);
                    setupListAdded();
                    edOther.setSingleLine();
                    edOther.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    edOther.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                            if (actionId == EditorInfo.IME_ACTION_DONE) {
                                if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
                                    int index = Constants.mStaticObject.getPeopleDetailDTO().size();
                                    PeopleDetailDTO peopleDetailDTO = new PeopleDetailDTO();
                                    peopleDetailDTO.setIDHO(Constants.mStaticObject.getIdHo());
                                    peopleDetailDTO.setHOSO(Constants.mStaticObject.getPeopleDTO
                                            ().getHOSO());
                                    peopleDetailDTO.setQ1(edOther.getText().toString());
                                    peopleDetailDTO.setSTT(index);
                                    peopleDetailDTO.setChuho(1);
                                    peopleDetailDTO.setID(Constants.mStaticObject.getIdHo() +
                                            index);

                                    Constants.mStaticObject.getPeopleDetailDTO().add
                                            (peopleDetailDTO);
                                    listMember.add(peopleDetailDTO);
                                    adapter.notifyDataSetChanged();
                                }
                                edOther.setText(null);
                                return true;
                            }
                            return false;
                        }
                    });
                } else if (questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST) {
                    rcvSelect.setVisibility(View.VISIBLE);
                    setupListSelected();
                } else if (questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_AUTO) {
                    spinner.setVisibility(View.VISIBLE);
                    SparseArray<String> listReligion = StringUtils.parseStringArray(R.array
                            .religion, activity);
                    ArrayList<ReligionDTO> listOptionSpinner = new ArrayList<>();
                    for (int i = 0; i < listReligion.size(); i++) {
                        int key = listReligion.keyAt(i);
                        listOptionSpinner.add(new ReligionDTO(key, listReligion.get(key)));
                    }

                    SpinnerAdapter adapter = new SpinnerAdapter(activity, listOptionSpinner);
                    spinner.setAdapter(adapter);
                }
            }
        } else {
            edOther.setVisibility(View.GONE);
            rcvSelect.setVisibility(View.GONE);
            if (questionDTO.getType() == Constants.TYPE_MIX) {
                listMember.clear();
                adapter.notifyDataSetChanged();
                isYes = false;
            } else if (Constants.TYPE_SINGLE_SELECT_LIST == questionDTO.getType()) {
                for (PeopleDetailDTO peopleDetailDTO : listMember) {
                    peopleDetailDTO.setSelected(false);
                }
                adapter.notifyDataSetChanged();
                isYes = false;
                listSelected.clear();
            }
            if (questionDTO.getSurvey().equals(Constants.SURVEY_PEOPLE)) {
                Constants.mStaticObject.getPeopleDTO().set(questionDTO.getId(), pos + 1);
            } else if (Constants.SURVEY_MEMBER.equals(questionDTO.getSurvey())) {
                Constants.mStaticObject.getMemberDTO().get(posMember).set(questionDTO.getId
                        (), pos + 1);
                memberDTO.set(questionDTO.getId(), pos + 1);
            }
        }
    }
}
