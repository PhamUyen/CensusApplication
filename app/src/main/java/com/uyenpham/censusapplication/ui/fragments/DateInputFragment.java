package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;
import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.previousIndex;

public class DateInputFragment extends BaseTypeFragment implements EditText
        .OnEditorActionListener,
        INextQuestion, IPreviousQuestion {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;
    @Bind(R.id.edYear)
    EditText edYear;
    @Bind(R.id.edMonth)
    EditText edMonth;
    @Bind(R.id.cbNone)
    CheckBox cbNone;


    private int posMember;
    ArrayList<OptionDTO> listOption = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_date_input;
    }

    @Override
    public void initData() {
        activity.setiNext(this);
        activity.setiPrevious(this);
        posMember = getPosMember();
        edYear.setOnEditorActionListener(this);
        edMonth.setOnEditorActionListener(this);
        if (questionDTO.getId().equals(Constants.QUESTION_C38) || questionDTO.getId().equals
                (Constants.QUESTION_C45)) {
            cbNone.setVisibility(View.GONE);
        }
        cbNone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switch (questionDTO.getSurvey()) {
                        case Constants.SURVEY_MEMBER:
                            memberDTO.setmC4N(9998);
                            memberDTO.setmC4T(98);
                            memberDTO.setmC05(null);
                            break;
                        case Constants.SURVEY_DEAD:
                            if (questionDTO.getId().equals(Constants.QUESTION_C46)) {
                                deadDTO.setmC46N(9998);
                                deadDTO.setmC46T(98);
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (questionDTO.getSurvey()) {
                        case Constants.SURVEY_MEMBER:
                            memberDTO.setmC4N(Integer.parseInt(edYear.getText().toString()));
                            memberDTO.setmC4T(Integer.parseInt(edMonth.getText().toString()));
                            break;
                        case Constants.SURVEY_DEAD:
                            if (questionDTO.getId().equals(Constants.QUESTION_C46)) {
                                deadDTO.setmC46N(Integer.parseInt(edYear.getText().toString()));
                                deadDTO.setmC46T(Integer.parseInt(edMonth.getText().toString()));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        loadQuestion(questionDTO);
    }

    @Override
    public boolean loadQuestion(QuestionDTO question) {
        setContentQuestion(tvQuestion);
        return true;
    }

    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        FamilyDetailDTO family = Constants.mStaticObject.getFamilyDetailDTO();
        switch (question.getId()) {
            case Constants.QUESTION_C04:
                if (memberDTO.getmC4T() != null && (memberDTO.getmC4T() < 0 || (memberDTO.getmC4T
                        () > 12 && memberDTO.getmC4T() != 98)) ||
                        (memberDTO.getmC4N() != null && (memberDTO.getmC4N() == Calendar
                                .getInstance().get(Calendar.YEAR) && memberDTO.getmC4T() >
                                Calendar.getInstance().get(Calendar.MONTH))
                        )) {
                    return new WarningDTO(getString(R.string.txt_invalid_month, posMember + 1,
                            memberDTO.getmC01(), memberDTO.getmC4T()), Constants.TYPE_NOTI);
                }
                break;
            case Constants.QUESTION_C21:
                if (memberDTO.getmC21N() != null && memberDTO.getmC21N() == 2018 && memberDTO
                        .getmC21T() != null && memberDTO.getmC21T() > 7) {
                    return new WarningDTO(getString(R.string.txt_time_mariage, posMember + 1,
                            memberDTO.getmC01(), memberDTO.getmC21T()), Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC21N() != null && memberDTO.getmC4N() != null && memberDTO
                        .getmC21N() < memberDTO.getmC4N()) {
                    return new WarningDTO(getString(R.string.txt_marriage_before_born, posMember
                            + 1, memberDTO.getmC01(), memberDTO.getmC21N(), memberDTO.getmC4N()),
                            Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC21N() != null && memberDTO.getmC4N() != null && (memberDTO
                        .getmC21N() - memberDTO.getmC4N()) < 7) {
                    return new WarningDTO(getString(R.string.txt_too_small_to_marriage, posMember
                            + 1, memberDTO.getmC01(), memberDTO.getmC21N(), memberDTO.getmC4N(),
                            (memberDTO.getmC21N() - memberDTO.getmC4N())), Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC05() != null && 7 <= memberDTO.getmC05() && memberDTO.getmC05() <= 12) {
                    return new WarningDTO(getString(R.string.txt_confirm_time_to_marriage,
                            posMember + 1, memberDTO.getmC01(), memberDTO.getmC4N(), memberDTO
                                    .getmC21N()), Constants.TYPE_CONFIRM);
                }
                if (memberDTO.getmC4N() != null && memberDTO.getmC21N() != null && memberDTO
                        .getmC4T() != null && memberDTO.getmC21T() != null
                        && (!memberDTO.getmC21T().equals(memberDTO.getmC4T()) || !memberDTO
                        .getmC21N().equals(memberDTO.getmC4N()))) {
                    return new WarningDTO(getString(R.string.txt_diff_marriage, posMember + 1,
                            memberDTO.getmC01(),
                            memberDTO.getmC21T(), memberDTO.getmC21N(), memberDTO.getmC4T(),
                            memberDTO.getmC4N()), Constants.TYPE_NOTI);
                }
                break;


            case Constants.QUESTION_C38:
                if(womanDTO.getC38N() == null || womanDTO.getC38T() == null){
                    return new WarningDTO(getString(R.string.txt_empty_time), Constants.TYPE_NOTI);
                }
                if (womanDTO.getC38N() == 2018 && womanDTO.getC38T() > 7) {
                    return new WarningDTO(getString(R.string.txt_invalid_month_birth, posMember +
                            1, memberDTO.getmC01(), womanDTO.getC38T()), Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC02() == 2 && (womanDTO.getC35A() + womanDTO.getC35B() + 2 ==
                        family.getmTSKHAU()) && womanDTO.getC38N() > 2018) {
                    return new WarningDTO(getString(R.string.txt_invalid_year_birth, posMember +
                            1, memberDTO.getmC01(), womanDTO.getC38N()), Constants.TYPE_NOTI);
                }
                int birthAge = memberDTO.getmC05() - Calendar.getInstance().get(Calendar.YEAR) -
                        womanDTO.getC38N();
                if (birthAge > 49 || (7 < birthAge && birthAge < 12)) {
                    return new WarningDTO(getString(R.string.txt_warning_birth_age, posMember +
                            1, womanDTO.getTenTV(), womanDTO.getC38T(), womanDTO.getC38N(),
                            birthAge), Constants.TYPE_CONFIRM);
                }
                if (birthAge < 7) {
                    return new WarningDTO(getString(R.string.txt_error_birth_age, posMember + 1,
                            womanDTO.getTenTV(), womanDTO.getC38T(), womanDTO.getC38N(),
                            birthAge), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C45:
                if(deadDTO.getmC45N()== null || deadDTO.getmC45T() == null){
                    return new WarningDTO(getString(R.string.txt_empty_time), Constants.TYPE_NOTI);
                }
                if (1 < deadDTO.getmC45T() && deadDTO.getmC45T() > 12 && deadDTO.getmC45T() != 98) {
                    return new WarningDTO(getString(R.string.txt_invalid_month_die, posMember +
                            1, deadDTO.getmC43(), deadDTO.getmC45T()), Constants.TYPE_NOTI);
                }
                if (deadDTO.getmC45N() == 2018 && deadDTO.getmC45T() > 7) {
                    return new WarningDTO(getString(R.string.txt_invalid_time_die, posMember + 1,
                            deadDTO.getmC43(), deadDTO.getmC45T()), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C46:
                if (1 < deadDTO.getmC45T() && deadDTO.getmC45T() > 12 && deadDTO.getmC45T() != 98) {
                    return new WarningDTO(getString(R.string.txt_invalid_month_birth_die,
                            posMember + 1, deadDTO.getmC43(), deadDTO.getmC46T()), Constants
                            .TYPE_NOTI);
                }
                if (deadDTO.getmC46N() == 2018 && deadDTO.getmC46T() > 7) {
                    return new WarningDTO(getString(R.string.txt_invalid_time_die, posMember + 1,
                            deadDTO.getmC43(), deadDTO.getmC46T()), Constants.TYPE_NOTI);
                }
                if (Integer.valueOf(deadDTO.getmC45N()) < Integer.valueOf(deadDTO.getmC46N())
                        || (Integer.valueOf(deadDTO.getmC45N()).equals(Integer.valueOf(deadDTO
                        .getmC46N())) &&
                        Integer.valueOf(deadDTO.getmC45T()) < Integer.valueOf(deadDTO.getmC46T())
                )) {
                    return new WarningDTO(getString(R.string.txt_die_before_birth, posMember + 1,
                            deadDTO.getmC43(), deadDTO.getmC45T()
                            , deadDTO.getmC45N(), deadDTO.getmC46T(), deadDTO.getmC46N()),
                            Constants.TYPE_NOTI);
                }
                break;

            default:
                break;
        }
        return null;
    }


    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            switch (questionDTO.getSurvey()) {
                case Constants.SURVEY_MEMBER:
                    updateMember(editText.getTag().toString(), editText.getText().toString());
                    break;
                case Constants.SURVEY_WOMAN:
                    updateWoman(editText.getTag().toString(), editText.getText().toString());
                    break;
                case Constants.SURVEY_DEAD:
                    updateDead(editText.getTag().toString(), editText.getText().toString());
                    break;
                default:
                    break;
            }

            return true;
        }
        return false;
    }

    private WomanDTO updateDead(String option, String answer) {
        WomanDTO womanDTO = Constants.mStaticObject.getWomanDTO().get(posMember);
        switch (questionDTO.getId()) {
            case Constants.QUESTION_C46:
                if (option.equals(Constants.MONTH)) {
                    deadDTO.setmC46T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    deadDTO.setmC46N(Integer.parseInt(answer));
                }
                if (deadDTO.getmC46N() != null && deadDTO.getmC46N() != 9998
                        && deadDTO.getmC46T() != null && deadDTO.getmC46T() != 98) {
                    deadDTO.setmC47(Calendar.getInstance().get(Calendar.YEAR) - deadDTO.getmC46N());
                }
                break;
            default:
                break;

        }
        return womanDTO;
    }

    private WomanDTO updateWoman(String option, String answer) {
        switch (questionDTO.getId()) {
            case Constants.QUESTION_C38:
                if (option.equals(Constants.MONTH)) {
                    womanDTO.setC38T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    womanDTO.setC38N(Integer.parseInt(answer));
                }
                break;
            default:
                break;

        }
        return womanDTO;
    }

    private MemberDTO updateMember(String option, String answer) {
        switch (questionDTO.getId()) {
            case Constants.QUESTION_C04:
                if (option.equals(Constants.MONTH)) {
                    memberDTO.setmC4T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    memberDTO.setmC4N(Integer.parseInt(answer));
                }
                if (memberDTO.getmC4N() != null && memberDTO.getmC4N() != 9998
                        && memberDTO.getmC4T() != null && memberDTO.getmC4T() != 98) {
                    memberDTO.setmC05(Calendar.getInstance().get(Calendar.YEAR) - memberDTO
                            .getmC4N());
                }
                if (isWoman(memberDTO)) {
                    Constants.mStaticObject.getWomanDTO().add(new WomanDTO(memberDTO.getmIDTV(),
                            memberDTO.getmC01()));
                }
                break;
            case Constants.QUESTION_C21:
                if (option.equals(Constants.MONTH)) {
                    memberDTO.setmC21T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    memberDTO.setmC21N(Integer.parseInt(answer));
                }
                if (memberDTO.getmC21N() != null && memberDTO.getmC21N() != 9998
                        && memberDTO.getmC21T() != null && memberDTO.getmC21T() != 98) {
                    memberDTO.setmC22(Calendar.getInstance().get(Calendar.YEAR) - memberDTO
                            .getmC21N());
                }
                break;
            default:
                break;
        }
        return memberDTO;
    }

    private boolean isWoman(MemberDTO member) {
        return (member.getmC03() == 3 && (member.getmC4N() < 2008 && member.getmC4T() < Calendar
                .getInstance().get(Calendar.MONTH)) && (member.getmC4N() > 1969 && member.getmC4T
                () > Calendar.getInstance().get(Calendar.MONTH)));
    }

    private int getStep() {
        int index = currentIndex;
        switch (questionDTO.getId()) {
            case Constants.QUESTION_C04:
                if (memberDTO.getmC05() != null) {
                    index += 2;
                }else {
                    index++;
                }
                break;
            case Constants.QUESTION_C21:
                if (memberDTO.getmC22() != null) {
                    index += 2;
                }else {
                    index++;
                }
                break;
            case Constants.QUESTION_C46:
                if (deadDTO.getmC47() != null) {
                    index += 2;
                }else {
                    index++;
                }
                break;
            default:
                break;
        }
        return index;
    }

    @Override
    public void next() {
        Utils.hideKeyboard(activity, lnContent);
        if (validateQuaetion(questionDTO, null) == null) {
            Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
            if (currentIndex < getListQuestion().size() - 1) {
                saveAnswerToSurvey(questionDTO, posMember);
                previousIndex = currentIndex;
                currentIndex = getStep();
                Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                        getListQuestion(), activity.mFragmentManager, getPosMember());
            }
        } else {
            WarningDTO warning = validateQuaetion(questionDTO, null);
            if (warning.getType() == Constants.TYPE_CONFIRM) {
                DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string
                                .txt_yes, R.string.txt_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
                                if (currentIndex < getListQuestion().size() - 1) {
                                    saveAnswerToSurvey(questionDTO, posMember);
                                    currentIndex = getStep();
                                    Utils.replcaeFragmentByType(getListQuestion().get
                                            (currentIndex), true, getListQuestion(), activity
                                            .mFragmentManager, getPosMember());
                                }
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dissmiss dialog
                            }
                        });
            } else {
                DialogUtils.showAlert(activity, warning.getMessage());
            }
        }
    }

    @Override
    public void previuos() {
        Utils.hideKeyboard(activity, lnContent);
        if (previousIndex != -1) {
            Utils.replcaeFragmentByType(getListQuestion().get(previousIndex), false, getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }
}
