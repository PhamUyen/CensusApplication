package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.MemberDAO;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.locality.NationDTO;
import com.uyenpham.censusapplication.models.locality.SpinnerDTO;
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
import com.uyenpham.censusapplication.utils.SharedPrefsUtils;
import com.uyenpham.censusapplication.utils.StringUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C53A;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C56;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C57;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C58A;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C61;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C62;
import static com.uyenpham.censusapplication.utils.Constants.QUESTION_C63;
import static com.uyenpham.censusapplication.utils.Constants.TYPE_SELECT_INPUT;

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

    private AnswerDTO answerDTO;
    private ArrayList<PeopleDetailDTO> listMember;
    private MultiSelectAdapter adapter;
    private ArrayList<PeopleDetailDTO> listSelected;
    private Boolean isYes;
    private ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter radioButtonAdapter;
    private int posMember;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_single_selection;
    }

    @Override
    public void initData() {
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
        loadQuestion(questionDTO);
    }

    private MemberDTO getMemberById(String id) {
        for (MemberDTO member : Constants.mStaticObject.getMemberDTO()) {
            if (member.getmIDTV().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null;
    }

    public boolean loadQuestion(final QuestionDTO question) {
        listOption = new ArrayList<>();
        setContentQuestion(tvQuestion);
        if (question.getId().equals(Constants.QUESTION_C02) && posMember == 0) {
            isYes =true;
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
        switch (question.getSurvey()) {
            case Constants.SURVEY_PEOPLE:
                if (isYes == null || (isYes && (listSelected.isEmpty() && listMember.isEmpty())) || (Constants.mStaticObject.getPeopleDTO
                        ().get(questionDTO.getId()) == null )) {
                    return new WarningDTO(getString(R.string.txt_invalid_info), Constants.TYPE_NOTI);
                }
                break;
            case Constants.SURVEY_MEMBER:
            case Constants.SURVEY_WOMAN:
            case Constants.SURVEY_DEAD:
                if(isYes == null){
                    return new WarningDTO(getString(R.string.txt_invalid_info), Constants.TYPE_NOTI);
                }else {
                    String idTV = Constants.mStaticObject.getIdHo() + Constants.mStaticObject
                            .getPeopleDetailDTO().get(0).getSTT();
                    MemberDTO chuho= null;
                    if(posMember != 0){
                        chuho = MemberDAO.getInstance().findById(idTV);
                    }
                    switch (question.getId()) {
                        case Constants.QUESTION_C02:
                            if(posMember!= 0){
                                if (memberDTO.getmC02() == 1 && (chuho != null&&chuho.getmC22() == 1)) {
                                    return new WarningDTO(getString(R.string.txt_invalid_relation, posMember + 1, memberDTO.getmC01(), listOption.get(memberDTO.getmC02() - 1), listOption.get(0).getOption()), Constants.TYPE_NOTI);
                                } else if (isOverNumberWife() && memberDTO.getmC02() == 1) {
                                    return new WarningDTO(getString(R.string.txt_number_couple, 2), Constants.TYPE_CONFIRM);
                                }
                            }
                            break;
                        case Constants.QUESTION_C03:
                            if(posMember != 0){
                                if (chuho != null && chuho.getmC03().equals(memberDTO.getmC03()) && memberDTO.getmC02() == 1) {
                                    return new WarningDTO(getString(R.string.txt_invalid_sex, posMember + 1, memberDTO.getmC01(), listOption.get(memberDTO.getmC03() - 1).getOption(), listOption.get(memberDTO.getmC03() - 1).getOption()), Constants.TYPE_NOTI);
                                }
                            }
                            break;

                        case Constants.QUESTION_C6A:
                            if (memberDTO.getmC6A() == 2 && StringUtils.isEmpty(memberDTO.getmC6B())) {
                                return new WarningDTO(getString(R.string.txt_empty_folk, posMember + 1, memberDTO.getmC01()), Constants.TYPE_NOTI);
                            }
                            if (posMember != 0 && !memberDTO.getmC6C().equals(chuho.getmC6C())) {
                                return new WarningDTO(getString(R.string.txt_invalid_folk, posMember + 1, memberDTO.getmC01(), memberDTO.getmC6C(), chuho.getmC6C()), Constants.TYPE_NOTI);
                            }
                            break;
                        case Constants.QUESTION_C14:
                            if (memberDTO.getmC14() == 1 && memberDTO.getmC05() == 60) {
                                return new WarningDTO(getString(R.string.txt_confirm_continue_study, posMember + 1,
                                        memberDTO.getmC01(), memberDTO.getmC4T() == null ? 0 : memberDTO.getmC4T(), memberDTO.getmC4N() == null ? 0 : memberDTO.getmC4N()), Constants.TYPE_CONFIRM);
                            }
                            break;
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
                                        posMember + 1, memberDTO.getmC01(), memberDTO.getmC4T(), memberDTO.getmC4N()
                                        , listOption.get(memberDTO.getmC15() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }
                            if (memberDTO.getmC13A() == 4 && memberDTO.getmC13B() == 4 && memberDTO.getmC13C() == 4
                                    || memberDTO.getmC13D() == 4 || memberDTO.getmC13E() == 4 && memberDTO.getmC13F() == 4) {
                                return new WarningDTO(getString(R.string.txt_invalid_study,
                                        posMember + 1, memberDTO.getmC01()
                                        , listOption.get(memberDTO.getmC15() - 1).getOption()), Constants.TYPE_NOTI);
                            }
                            break;
                        case Constants.QUESTION_C16:
                            return checkLevelStudy(memberDTO);
                        case Constants.QUESTION_C17:
                            if (memberDTO.getmC05() < 8 && memberDTO.getmC17() == 1) {
                                return new WarningDTO(getString(R.string.txt_error_job,
                                        posMember + 1, memberDTO.getmC01(), memberDTO.getmC4T(), memberDTO.getmC4N()
                                        , listOption.get(memberDTO.getmC15() - 1).getOption()), Constants.TYPE_NOTI);
                            }
                            if (8 < memberDTO.getmC05() && memberDTO.getmC05() < 15 && memberDTO.getmC17() == 1) {
                                return new WarningDTO(getString(R.string.txt_warning_job,
                                        posMember + 1, memberDTO.getmC01(), memberDTO.getmC4T(), memberDTO.getmC4N()
                                        , listOption.get(memberDTO.getmC15() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }
                            break;
                        case Constants.QUESTION_C18:
                            if (memberDTO.getmC14() == 3 && (memberDTO.getmC18() == 1 || memberDTO.getmC18() == 2)) {
                                return new WarningDTO(getString(R.string.txt_invalid_level_job, posMember + 1, memberDTO.getmC01(), listOption.get(memberDTO.getmC17() - 1).getOption()), Constants.TYPE_NOTI);
                            }
                            if ((memberDTO.getmC16() == 1 || memberDTO.getmC16() == 2) && (memberDTO.getmC18() == 4 || memberDTO.getmC18() == 5)) {
                                return new WarningDTO(getString(R.string.txt_error_level_job, posMember + 1, memberDTO.getmC01(), memberDTO.getmC16()
                                        , listOption.get(memberDTO.getmC18() - 1).getOption()), Constants.TYPE_NOTI);
                            }
                            break;

                        //tinh trang hon nhan
                        case Constants.QUESTION_C22:
                            //co quan he vo/chong voi chu ho nhung tinh trang hon nhan khong phai la ket hon
                            if (memberDTO.getmSTTNKTT() != 1 && memberDTO.getmC02() == 2 && memberDTO.getmC22() != 2) {
                                return new WarningDTO(getString(R.string.txt_invalid_marital, posMember + 1, memberDTO.getmC01(), listOption.get(memberDTO.getmC22() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }

                            //chuyen den ho voi li do ket hon nhung tinh trang hon nhan laf chua ket hon
                            if (memberDTO.getmC12() != null && memberDTO.getmC12() == 5 &&
                                    memberDTO.getmC22() == 1) {
                                return new WarningDTO(getString(R.string.txt_wrong_marital, posMember + 1, memberDTO.getmC01(),
                                        listOption.get(memberDTO.getmC22() - 1).getOption()), Constants.TYPE_NOTI);
                            }

                            //tuoi  nho hon 18 nhung da ket hon
                            if (memberDTO.getmC05() < 18 && 1 < memberDTO.getmC22() && memberDTO.getmC22() <= 5) {
                                return new WarningDTO(getString(R.string.txt_invalid_age_for_marital, posMember + 1, memberDTO.getmC01(),
                                        memberDTO.getmC05(), listOption.get(memberDTO.getmC22() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case Constants.QUESTION_C25:
                            if (memberDTO.getmC13A() == 4 && memberDTO.getmC13B() == 4 && memberDTO.getmC13C() == 4
                                    || memberDTO.getmC13D() == 4 || memberDTO.getmC13E() == 4 && memberDTO.getmC13F() == 4 && memberDTO.getmC25() == 1) {
                                return new WarningDTO(getString(R.string.txt_invalid_work_state,
                                        posMember + 1, memberDTO.getmC01()), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case Constants.QUESTION_C30:
                            if (memberDTO.getmC05() < 18 && (memberDTO.getmC30() == 1 || memberDTO.getmC30() == 4)) {
                                return new WarningDTO(getString(R.string.txt_invalid_work_position, posMember + 1, memberDTO.getmC01()
                                        , listOption.get(memberDTO.getmC30() - 1).getOption(), memberDTO.getmC05()), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case Constants.QUESTION_C34:
                            if (womanDTO.getC34() == 1 && memberDTO.getmC05() < 12) {
                                return new WarningDTO(getString(R.string.txt_age_have_child, posMember + 1, womanDTO.getTenTV(), memberDTO.getmC05()), Constants.TYPE_CONFIRM);
                            }
                            break;

                        //Cảnh báo: Hộ có Tổng số nhân khẩu thực tế thường trú trong hộ =0 và cũng không có người chết. Có đúng không?
                        //LỖI: Hộ không có người chết (C42=2) mà Tình trạng phỏng vấn ban đầu = 5 (Chết cả hộ)
                        //LỖI: Hộ có người chết (C42=1) nhưng số người chết =0. Hãy nhập lại!
                        case Constants.QUESTION_C48:
                            if (deadDTO.getmC47() <= 5 && 0 < deadDTO.getmC47() && (deadDTO.getmC48() == 2 || deadDTO.getmC48() == 5)) {
                                return new WarningDTO(getString(R.string.txt_warning_die, posMember + 1, deadDTO.getmC43()
                                        , deadDTO.getmC47(), listOption.get(deadDTO.getmC48() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }
                            if (deadDTO.getmC48() == 6 && StringUtils.isEmpty(deadDTO.getmC48K())) {
                                return new WarningDTO(getString(R.string.txt_invalid_reason_die, posMember + 1, deadDTO.getmC43()), Constants.TYPE_NOTI);
                            }
                            break;

                        case Constants.QUESTION_C49:
                            if (deadDTO.getmC49() == 5 && StringUtils.isEmpty(deadDTO.getmC49K())) {
                                return new WarningDTO(getString(R.string.txt_invalid_case_die, posMember + 1, deadDTO.getmC43()), Constants.TYPE_NOTI);
                            }
                            break;
                        case QUESTION_C53A:
                            if (houseDTO.getC53B() >= 10) {
                                return new WarningDTO(getString(R.string.txt_warnig_number_room, houseDTO.getC53B()), Constants.TYPE_CONFIRM);
                            }
                            if (houseDTO.getC53B() <= 0) {
                                return new WarningDTO(getString(R.string.txt_warning_no_room), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case Constants.QUESTION_C55:
                            if (houseDTO.getC53A() == 1 && houseDTO.getC55() == 2) {
                                return new WarningDTO(getString(R.string.txt_warning_physical), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case QUESTION_C56:
                            if (houseDTO.getC55() == 2 && houseDTO.getC56() == 1) {
                                return new WarningDTO(getString(R.string.txt_warning_roof_physical), Constants.TYPE_CONFIRM);
                            }

                            if (houseDTO.getC56() == 2 && houseDTO.getC53A() == 1) {
                                return new WarningDTO(getString(R.string.txt_warning_physical_department), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case QUESTION_C57:
                            if (houseDTO.getC57() == 2 && houseDTO.getC53A() == 1) {
                                return new WarningDTO(getString(R.string.txt_warning_physical_wall), Constants.TYPE_CONFIRM);
                            }
                            break;

                        case QUESTION_C58A:
                            if (houseDTO.getC58A() == 1 && houseDTO.getC53A() == 1) {
                                return new WarningDTO(getString(R.string.txt_year_use, listOption.get(houseDTO.getC58A() - 1).getOption()), Constants.TYPE_CONFIRM);
                            }

                            if (houseDTO.getC58A() == 1 && (Integer.parseInt(houseDTO.getC58B()) < 2010 || 2018 < Integer.parseInt(houseDTO.getC58B()))) {
                                return new WarningDTO(getString(R.string.txt_invalid_year_use, houseDTO.getC58B()), Constants.TYPE_NOTI);
                            }
                            break;

                        case QUESTION_C61:
                            if (houseDTO.getC61() == 3 && houseDTO.getC53A() == 1) {
                                return new WarningDTO(getString(R.string.txt_warning_light_energy), Constants.TYPE_CONFIRM);
                            }
                            if (houseDTO.getC61() == 5 && StringUtils.isEmpty(houseDTO.getC61A())) {
                                return new WarningDTO(getString(R.string.txt_unknow_energy_light), Constants.TYPE_NOTI);
                            }
                            break;

                        case QUESTION_C62:
                            if (houseDTO.getC62() == 6) {
                                return new WarningDTO(getString(R.string.txt_confirm_no_cook_energy), Constants.TYPE_CONFIRM);
                            }

                            if (houseDTO.getC62() == 1 && (houseDTO.getC61() == 3 || houseDTO.getC61() == 4)) {
                                return new WarningDTO(getString(R.string.txt_invalid_energy, String.valueOf(houseDTO.getC61())), Constants.TYPE_CONFIRM);
                            }
                            if (houseDTO.getC62() == 4 && houseDTO.getC53A() == 1) {
                                return new WarningDTO(getString(R.string.txt_wrong_energy), Constants.TYPE_CONFIRM);
                            }
                            if (houseDTO.getC62() == 5 && StringUtils.isEmpty(houseDTO.getC62A())) {
                                return new WarningDTO(getString(R.string.txt_other_cook_energy), Constants.TYPE_NOTI);
                            }
                            break;

                        case QUESTION_C63:
                            if (houseDTO.getC63() == 8 && StringUtils.isEmpty(houseDTO.getC63A())) {
                                return new WarningDTO(getString(R.string.txt_other_water), Constants.TYPE_NOTI);
                            }
                            break;

                        default:
                            break;

                    }
                }
                break;
                default:
                    break;

        }
        return null;
    }

    private WarningDTO checkLevelStudy(MemberDTO memberDTO) {
        if ((memberDTO.getmC16()!= null && memberDTO.getmC15()!= null)&&((memberDTO.getmC16() == 1 && memberDTO.getmC15() == 3)
                || (memberDTO.getmC16() == 2 && memberDTO.getmC15() == 4)
                || (memberDTO.getmC16() == 3 && memberDTO.getmC15() == 5)
                || (memberDTO.getmC16() == 4 && 7 <= memberDTO.getmC15() && memberDTO.getmC15() <= 11)
                || (5 <= memberDTO.getmC16() && memberDTO.getmC16() <= 7 && 7 <= memberDTO.getmC15() && memberDTO.getmC15() <= 11)
                || ((memberDTO.getmC16() == 8 || memberDTO.getmC16() == 9) && (memberDTO.getmC15() == 9 || memberDTO.getmC15() == 10 || memberDTO.getmC15() == 11)))) {
            return new WarningDTO(getString(R.string.txt_invalid_max_study, posMember + 1, memberDTO.getmC01(),
                    memberDTO.getmC4T(), memberDTO.getmC4N(), memberDTO.getmC05(), memberDTO.getmC15(), listOption.get(memberDTO.getmC16() - 1).getOption()), Constants.TYPE_CONFIRM);
        }
        if ((memberDTO.getmC16()!= null )&&((memberDTO.getmC16() == 2 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 2009)
                || (memberDTO.getmC16() == 3 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 2005)
                || (memberDTO.getmC16() == 4 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 2002)
                || (memberDTO.getmC16() == 5 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 2005)
                || (memberDTO.getmC16() == 6 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 2000)
                || (memberDTO.getmC16() == 7 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 1999)
                || (memberDTO.getmC16() == 8 && memberDTO.getmC4N() != null && memberDTO.getmC4N() <= 1998))) {
            return new WarningDTO(getString(R.string.txt_invalid_age_for_level, posMember + 1, memberDTO.getmC01(),
                    memberDTO.getmC4T(), memberDTO.getmC4N(), memberDTO.getmC05(), listOption.get(memberDTO.getmC16() + 1).getOption()), Constants.TYPE_NOTI);
        }
        if (memberDTO.getmC13A() == 4 && memberDTO.getmC13B() == 4 && memberDTO.getmC13C() == 4
                || memberDTO.getmC13D() == 4 || memberDTO.getmC13E() == 4 && memberDTO.getmC13F() == 4) {
            return new WarningDTO(getString(R.string.txt_disabilities,
                    posMember + 1, memberDTO.getmC01()
                    , listOption.get(memberDTO.getmC16() - 1).getOption()), Constants.TYPE_NOTI);
        }
        return null;

    }

    private boolean isOverNumberWife() {
        for (MemberDTO memberDTO1 : Constants.mStaticObject.getMemberDTO()) {
            if (memberDTO1.getmC02() == 1) {
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
            if (validateQuaetion(questionDTO, answerDTO) == null) {
                if (questionDTO.getType() == Constants.TYPE_SINGLE_SELECT_LIST) {
                    for (PeopleDetailDTO peopleDetailDTO : listSelected) {
                        Constants.mStaticObject.getPeopleDetailDTO().remove(peopleDetailDTO);
                    }
                } else if (questionDTO.getType() == Constants.TYPE_MIX) {
                    if (questionDTO.getId().equals(Constants.QUESTION_Q7)) {
                        for (PeopleDetailDTO peopleDetailDTO : listSelected) {
                            DeadDTO deadDTO = new DeadDTO(peopleDetailDTO.getQ1(), peopleDetailDTO.getIDHO() + peopleDetailDTO.getSTT());
                            Constants.mStaticObject.getDeadDTO().add(deadDTO);
                        }
                    }
                    Constants.mStaticObject.getPeopleDetailDTO().addAll(listMember);
                }
                nextFragment();
            } else {
                WarningDTO warning = validateQuaetion(questionDTO, null);
                if (warning.getType() == Constants.TYPE_CONFIRM) {
                    DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string.txt_yes, R.string.txt_no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    saveInfo();
                                    nextFragment();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //dissmiss dialog
                                }
                            });
                } else {
                    DialogUtils.showErrorAlert(activity, warning.getMessage());
                }
            }

        } else {
            if (validateQuaetion(questionDTO, answerDTO) == null) {
//                saveInfo();
                nextFragment();
            }else {
                WarningDTO warning = validateQuaetion(questionDTO, null);
                if (warning.getType() == Constants.TYPE_CONFIRM) {
                    DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string.txt_yes, R.string.txt_no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    saveInfo();
                                    nextFragment();
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //dissmiss dialog
                                }
                            });
                } else {
                    DialogUtils.showErrorAlert(activity, warning.getMessage());
                }
            }
        }
    }

    private void saveInfo() {
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
            case Constants.SURVEY_HOUSE:
                Constants.mStaticObject.setHouseDTO(houseDTO);
                break;
            default:
                break;
        }
    }

    private void nextFragment() {
        saveAnswerToSurvey(questionDTO,posMember);
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
                if(memberDTO.getmC18() ==5){
                    index+=2;
                }else {
                    index = KT2(index);
                }
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
            return ++index;
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
        if (option.isSelected() && option.getType().equalsIgnoreCase(Constants.TYPE_SHOW)) {
            isYes = true;
            setValue(pos);
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
                                if(questionDTO.getId().equals(Constants.QUESTION_Q7)){
                                    DeadDTO deadDTO= new DeadDTO();
                                    deadDTO.setmC43(edOther.getText().toString());
                                    Constants.mStaticObject.getDeadDTO().add
                                            (deadDTO);
                                }
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
                final ArrayList<SpinnerDTO> listOptionSpinner = new ArrayList<>();
                if(questionDTO.getId().equals(Constants.QUESTION_C7A)){
                    SparseArray<String> listReligion = StringUtils.parseStringArray(R.array
                            .religion, activity);
                    for (int i = 0; i < listReligion.size(); i++) {
                        int key = listReligion.keyAt(i);
                        listOptionSpinner.add(new SpinnerDTO(String.valueOf(key), listReligion.get(key)));
                    }
                }else if(questionDTO.getId().equals(Constants.QUESTION_C6A)) {
                    String json= SharedPrefsUtils.getStringPreference(activity,"nation");
                    List<NationDTO> list =new Gson().fromJson(json, new TypeToken<List<NationDTO>>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        listOptionSpinner.add(new SpinnerDTO(list.get(i).getId(), list.get(i).getName()));
                    }
                }

                SpinnerAdapter adapter = new SpinnerAdapter(activity, listOptionSpinner);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(questionDTO.getId().equals(Constants.QUESTION_C7A)){
                            memberDTO.setmC7B(listOptionSpinner.get(i).getId());
                        }else if(questionDTO.getId().equals(Constants.QUESTION_C6A)){
                            memberDTO.setmC6B(listOptionSpinner.get(i).getId());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            } else if (questionDTO.getType() == TYPE_SELECT_INPUT) {
                edOther.setVisibility(View.VISIBLE);
                edOther.setSingleLine();
                edOther.setImeOptions(EditorInfo.IME_ACTION_DONE);
                edOther.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_DONE) {
                            if (questionDTO.getId().equals(Constants.QUESTION_C48)) {
                                deadDTO.setmC48K(edOther.getText().toString());
                            } else if (questionDTO.getId().equals(Constants.QUESTION_C49)) {
                                deadDTO.setmC49K(edOther.getText().toString());
                            }
                        }
                        return false;
                    }
                });
            }
        } else {
            isYes = false;
            edOther.setVisibility(View.GONE);
            rcvSelect.setVisibility(View.GONE);
            if (questionDTO.getType() == Constants.TYPE_MIX) {
                listMember.clear();
                adapter.notifyDataSetChanged();
            } else if (Constants.TYPE_SINGLE_SELECT_LIST == questionDTO.getType()) {
                for (PeopleDetailDTO peopleDetailDTO : listMember) {
                    peopleDetailDTO.setSelected(false);
                }
                adapter.notifyDataSetChanged();
                listSelected.clear();
            }
            setValue(pos);
        }
    }

    private void setValue(int pos) {
        switch (questionDTO.getSurvey()) {
            case Constants.SURVEY_PEOPLE:
                Constants.mStaticObject.getPeopleDTO().set(questionDTO.getId(), pos + 1);
                break;
            case Constants.SURVEY_MEMBER:
                if (questionDTO.getId().equalsIgnoreCase(Constants.QUESTION_C02)) {
                    memberDTO.set(questionDTO.getId(), pos + 2);
                } else {
                    memberDTO.set(questionDTO.getId(), pos + 1);
                }
                break;
            case Constants.SURVEY_DEAD:
                deadDTO.set(questionDTO.getId(), pos + 1);
                break;
            case Constants.SURVEY_WOMAN:
                womanDTO.set(questionDTO.getId(), pos + 1);
                break;
            case Constants.SURVEY_HOUSE:
                if(houseDTO != null){
                    houseDTO.set(questionDTO.getId(), pos + 1);
                }
                familyDetailDTO.set(questionDTO.getId(), pos + 1);
                break;
            default:
                break;
        }
    }
}
