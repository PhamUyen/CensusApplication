package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.db.MemberDAO;
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

public class NumberInputFragment extends BaseTypeFragment implements EditText
        .OnEditorActionListener,
        INextQuestion, IPreviousQuestion {
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;


    private QuestionDTO questionDTO;
    private int posMember;
    //    private MemberDTO memberDTO;
    ArrayList<OptionDTO> listOption = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_number_input;
    }

    @Override
    protected void createView(View view) {
        activity.setiNext(this);
        activity.setiPrevious(this);
        posMember = getPosMember();
        memberDTO = Constants.mStaticObject.getMemberDTO().get(posMember);
        questionDTO = getQuestionDTO();
        loadQuestion(questionDTO);
    }

    @Override
    public boolean loadQuestion(QuestionDTO question) {
        tvQuestion.setText(question.getQuestion());
        listOption = question.getOptions();
        LinearLayout linearLayout = null;
        for (OptionDTO option : listOption) {
            if (question.getSurvey().equals(Constants.SURVEY_MEMBER)) {
                linearLayout = genlayoutInputNumber(option.getOption(), (memberDTO == null || memberDTO
                        .get(question.getId()) == null) ? "" : String.valueOf(memberDTO.get(question
                        .getId())), listOption.indexOf(option));
            } else if (question.getSurvey().equals(Constants.SURVEY_WOMAN)) {
                WomanDTO womanDTO = Constants.mStaticObject.getWomanDTO().get(posMember);
                linearLayout = genlayoutInputNumber(option.getOption(), (womanDTO == null ||
                        womanDTO.get(question.getId()) == null) ? "" : String.valueOf(womanDTO
                        .get(question.getId())), listOption.indexOf(option));
            }
            if (linearLayout != null) {
                lnContent.addView(linearLayout);
            }
        }
        return true;
    }

    private LinearLayout genlayoutInputNumber(final String option, String answer, int posOption) {
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        int padding = mActivity.getResources().getDimensionPixelOffset(R.dimen.padding_small);

        LinearLayout linearLayout = new LinearLayout(mActivity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getLayoutParams(0, margin, 0, margin / 2));

        TextView textView = new TextView(mActivity);
        textView.setLayoutParams(getLayoutParamsWeghtWidth(0, 0, margin, 0, 1f));
        textView.setText(option);
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        linearLayout.addView(textView);

        EditText editText = new EditText(mActivity);
        editText.setLayoutParams(getLayoutParamsWeghtWidth(margin, 0, 0, 0, 1f));
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setBackgroundResource(R.drawable.bg_edit_text);
        editText.setTag(option);
        if (option.equals(Constants.MONTH)) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        } else if (option.equals(Constants.YEAR)) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        }
        if (posOption == listOption.size() - 1) {
            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        } else {
            editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
        if (questionDTO.getId().equals(Constants.QUESTION_C05)) {
            if (memberDTO.getmC4N() != 9998) {
                int age = Calendar.getInstance().get(Calendar.YEAR) - memberDTO.getmC4N();
                editText.setText(String.valueOf(age));
                editText.setEnabled(false);
            } else {
                editText.setText(answer);
                editText.setEnabled(true);
            }
        }
        editText.setText(answer);
        editText.setSingleLine();
        editText.setOnEditorActionListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (questionDTO.getSurvey()) {
                    case Constants.SURVEY_MEMBER:
                        Constants.mStaticObject.getMemberDTO().set(posMember, updateMember(option,
                                charSequence.toString()));
                        break;
                    case Constants.SURVEY_WOMAN:
                        Constants.mStaticObject.getWomanDTO().set(posMember, updateWoman(option,
                                charSequence.toString()));
                        break;
                    case Constants.SURVEY_DEAD:
//                        Constants.mStaticObject.getDeadDTO().set(posMember, updateWoman(option,
//                                charSequence.toString()));
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //do nothing
            }
        });
        editText.setPadding(padding, padding, padding, padding);
        linearLayout.addView(editText);

        return linearLayout;
    }

//    public void setQuestionDTO(QuestionDTO questionDTO) {
//        this.questionDTO = questionDTO;
//    }


    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        String idTV = Constants.mStaticObject.getIdHo() + Constants.mStaticObject
                .getPeopleDetailDTO().get(0).getSTT();
        MemberDTO chuho = MemberDAO.getInstance().findById(idTV);
        FamilyDetailDTO family = Constants.mStaticObject.getFamilyDetailDTO();
        switch (question.getId()) {
            case Constants.QUESTION_C04:
                if (memberDTO.getmC4T() < 0 || (memberDTO.getmC4T() > 12 && memberDTO.getmC4T() != 98) ||
                        (memberDTO.getmC4N() == Calendar.getInstance().get(Calendar.YEAR) && memberDTO.getmC4T() > Calendar.getInstance().get(Calendar.MONTH)
                        )) {
                    return new WarningDTO(getString(R.string.txt_invalid_month, posMember + 1, memberDTO.getmC01(), memberDTO.getmC4T()), Constants.TYPE_NOTI);
                }
                break;
            case Constants.QUESTION_C05:
                if (posMember == 0 && memberDTO.getmC05() < 6) {
                    return new WarningDTO(getString(R.string.txt_age_too_small, memberDTO.getmC01(), memberDTO.getmC05()), Constants.TYPE_CONFIRM);
                }
                if (posMember != 0 && memberDTO.getmC02() == 1 && Math.abs(chuho.getmC05() - memberDTO.getmC05()) >= 20) {
                    return new WarningDTO(getString(R.string.txt_age_couple
                            , posMember + 1, memberDTO.getmC01(), memberDTO.getmC05(), chuho.getmC01(), chuho.getmC05(), Math.abs(chuho.getmC05() - memberDTO.getmC05())), Constants.TYPE_CONFIRM);
                }
                if (posMember != 0 && memberDTO.getmC02() == 4 && chuho.getmC05() >= memberDTO.getmC05()) {
                    return new WarningDTO(getString(R.string.txt_age_parent
                            , posMember + 1, memberDTO.getmC01(), memberDTO.getmC05(), chuho.getmC01(), chuho.getmC05()), Constants.TYPE_NOTI);
                }
                if (posMember != 0 && memberDTO.getmC02() == 3 && chuho.getmC05() <= 30) {
                    return new WarningDTO(getString(R.string.txt_age_grandparent
                            , posMember + 1, memberDTO.getmC01(), chuho.getmC01(), chuho.getmC05()), Constants.TYPE_CONFIRM);
                }
                break;
            case Constants.QUESTION_C23:
                if (memberDTO.getmC23N() != null && memberDTO.getmC23N() == 2018 && memberDTO.getmC23T() != null && memberDTO.getmC23T() > 7) {
                    return new WarningDTO(getString(R.string.txt_time_mariage, posMember + 1, memberDTO.getmC01(), memberDTO.getmC23T()), Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC23N() != null && memberDTO.getmC4N() != null && memberDTO.getmC23N() < memberDTO.getmC4N()) {
                    return new WarningDTO(getString(R.string.txt_marriage_before_born, posMember + 1, memberDTO.getmC01(), memberDTO.getmC23N(), memberDTO.getmC4N()), Constants.TYPE_NOTI);
                }
                if (memberDTO.getmC23N() != null && memberDTO.getmC4N() != null && (memberDTO.getmC23N() - memberDTO.getmC4N()) < 7) {
                    return new WarningDTO(getString(R.string.txt_too_small_to_marriage, posMember + 1, memberDTO.getmC01(), memberDTO.getmC23N(), memberDTO.getmC4N(), (memberDTO.getmC23N() - memberDTO.getmC4N())), Constants.TYPE_NOTI);
                }
                if (7 <= memberDTO.getmC05() && memberDTO.getmC05() <= 12) {
                    return new WarningDTO(getString(R.string.txt_confirm_time_to_marriage, posMember + 1, memberDTO.getmC01(), memberDTO.getmC4N(), memberDTO.getmC23N()), Constants.TYPE_CONFIRM);
                }
                if (memberDTO.getmC4N() != null && memberDTO.getmC23N() != null && memberDTO.getmC4T() != null && memberDTO.getmC23T() != null
                        && (!memberDTO.getmC23T().equals(memberDTO.getmC4T()) || !memberDTO.getmC23N().equals(memberDTO.getmC4N()))) {
                    return new WarningDTO(getString(R.string.txt_diff_marriage, posMember + 1, memberDTO.getmC01(),
                            memberDTO.getmC23T(), memberDTO.getmC23N(), memberDTO.getmC4T(), memberDTO.getmC4N()), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C24:
                if (memberDTO.getmC24() < 7) {
                    return new WarningDTO(getString(R.string.txt_age_marriage_too_small, posMember + 1, memberDTO.getmC01(), memberDTO.getmC24()), Constants.TYPE_NOTI);
                }
                if (7 <= memberDTO.getmC24() && memberDTO.getmC24() <= 12) {
                    return new WarningDTO(getString(R.string.txt_confirm_age_marriage, posMember + 1, memberDTO.getmC01(), memberDTO.getmC24()), Constants.TYPE_CONFIRM);
                }
                if (memberDTO.getmC24() > memberDTO.getmC05()) {
                    return new WarningDTO(getString(R.string.txt_error_age_marriage_too_large, posMember + 1, memberDTO.getmC01(), memberDTO.getmC24(), memberDTO.getmC05()), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C35:
                return checkNumberChild(family);

            case Constants.QUESTION_C36:
                if (womanDTO.getC36A() > 10) {
                    return new WarningDTO(getString(R.string.txt_confirm_number_child_in_other_place, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_BOY, womanDTO.getC35A()), Constants.TYPE_CONFIRM);
                }
                if (womanDTO.getC36B() > 10) {
                    return new WarningDTO(getString(R.string.txt_confirm_number_child_in_other_place, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_GIRL, womanDTO.getC35A()), Constants.TYPE_CONFIRM);
                }
                break;

            case Constants.QUESTION_C37:
                if (womanDTO.getC37A() > 10) {
                    return new WarningDTO(getString(R.string.txt_confirm_number_child_die, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_BOY, womanDTO.getC35A()), Constants.TYPE_CONFIRM);
                }
                if (womanDTO.getC37B() > 10) {
                    return new WarningDTO(getString(R.string.txt_confirm_number_child_die, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_GIRL, womanDTO.getC35A()), Constants.TYPE_CONFIRM);
                }
                if(memberDTO.getmC02() == 2 && womanDTO.getC35A()!= countSon() && womanDTO.getC36A() ==0 && womanDTO.getC37A() ==0){
                    return new WarningDTO(getString(R.string.txt_error_son,  memberDTO.getmC01(),  womanDTO.getC35A(),countSon()), Constants.TYPE_NOTI);
                }
                if(memberDTO.getmC02() == 2 && womanDTO.getC35B()!= countDaughter() && womanDTO.getC36B() ==0 && womanDTO.getC37B() ==0){
                    return new WarningDTO(getString(R.string.txt_error_daughter,  memberDTO.getmC01(),  womanDTO.getC35B(),countDaughter()), Constants.TYPE_NOTI);
                }
                int sumChild = womanDTO.getC35A()+womanDTO.getC36A()+womanDTO.getC37A()+womanDTO.getC35B()+womanDTO.getC36B()+womanDTO.getC37B();
                if((memberDTO.getmC05()>= 10 && memberDTO.getmC05() <=20 && sumChild >=5)
                        ||(memberDTO.getmC05()>= 21 && memberDTO.getmC05() <=30 && sumChild >=10)
                        ||(memberDTO.getmC05()>= 31 && memberDTO.getmC05() <=49 && sumChild >=18)){
                    return new WarningDTO(getString(R.string.txt_warning_sum_child, posMember+1, memberDTO.getmC01(),  memberDTO.getmC05(),sumChild), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C38:
                //LỖI: Nữ chủ hộ %s có năm sinh lần gần nhất=%d nhưng đếm được %d người con đẻ của nữ chủ hộ có năm sinh sau năm sinh lần gần nhất
                //Cảnh báo: Phụ nữ STT %d %s có năm sinh lần gần nhất=%d và chỉ có con sống cùng nhưng đếm được %d người trong hộ có năm sinh = %d
                if(womanDTO.getC38N() == 2018 && womanDTO.getC38T() >7){
                    return new WarningDTO(getString(R.string.txt_invalid_month_birth,posMember+1, memberDTO.getmC01(),womanDTO.getC38T()), Constants.TYPE_NOTI);
                }
                if(memberDTO.getmC02() ==2 && (womanDTO.getC35A()+womanDTO.getC35B()+2 == family.getmTSKHAU()) && womanDTO.getC38N() >2018){
                    return new WarningDTO(getString(R.string.txt_invalid_year_birth,posMember+1, memberDTO.getmC01(),womanDTO.getC38N()), Constants.TYPE_NOTI);
                }
                int birthAge = memberDTO.getmC05() - Calendar.getInstance().get(Calendar.YEAR) - womanDTO.getC38N();
                if(birthAge > 49 || (7< birthAge && birthAge <12)){
                    return  new WarningDTO(getString(R.string.txt_warning_birth_age,posMember+1, womanDTO.getTenTV(),womanDTO.getC38T(),womanDTO.getC38N(),birthAge), Constants.TYPE_CONFIRM);
                }
                if(birthAge <7){
                    return  new WarningDTO(getString(R.string.txt_error_birth_age,posMember+1, womanDTO.getTenTV(),womanDTO.getC38T(),womanDTO.getC38N(),birthAge), Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C45:
                if(1< Integer.parseInt(deadDTO.getmC45T()) && Integer.parseInt(deadDTO.getmC45T()) >12 && Integer.parseInt(deadDTO.getmC45T())!=98){
                    return   new WarningDTO(getString(R.string.txt_invalid_month_die,posMember+1, deadDTO.getmC43(),Integer.parseInt(deadDTO.getmC45T())),Constants.TYPE_NOTI);
                }
                if(Integer.parseInt(deadDTO.getmC45N()) == 2018 && Integer.parseInt(deadDTO.getmC45T()) >7){
                    return   new WarningDTO(getString(R.string.txt_invalid_time_die,posMember+1, deadDTO.getmC43(),Integer.parseInt(deadDTO.getmC45T())),Constants.TYPE_NOTI);
                }
                break;

            case Constants.QUESTION_C46:
                //LỖI: Người chết thứ %d %s có Tháng sinh của người chết = %d không hợp lệ
                //LỖI: Người chết thứ %d %s sinh %d/2018 sau thời điểm điều tra 1/8/2018
                //LỖI: Người chết thứ %d %s sinh %d/%d chết %d/%d. Chết trước khi sinh!
                if(1< Integer.parseInt(deadDTO.getmC45T()) && Integer.parseInt(deadDTO.getmC45T()) >12 && Integer.parseInt(deadDTO.getmC45T())!=98){
                    return   new WarningDTO(getString(R.string.txt_invalid_month_birth_die,posMember+1, deadDTO.getmC43(),Integer.parseInt(deadDTO.getmC46T())),Constants.TYPE_NOTI);
                }
                if(Integer.parseInt(deadDTO.getmC46N()) == 2018 && Integer.parseInt(deadDTO.getmC46T()) >7){
                    return   new WarningDTO(getString(R.string.txt_invalid_time_die,posMember+1, deadDTO.getmC43(),Integer.parseInt(deadDTO.getmC46T())),Constants.TYPE_NOTI);
                }
                break;
            default:
                break;
        }
        return null;
    }

    private int countSon() {
        int count =0;
        for(MemberDTO member : Constants.mStaticObject.getMemberDTO()){
            if(member.getmC02() == 3 && member.getmC03() ==1){
                count ++;
            }
        }
        return count;
    }
    private int countDaughter() {
        int count =0;
        for(MemberDTO member : Constants.mStaticObject.getMemberDTO()){
            if(member.getmC02() == 3 && member.getmC03() ==2){
                count ++;
            }
        }
        return count;
    }

    private WarningDTO checkNumberChild(FamilyDetailDTO family) {
        if (womanDTO.getC35A() > 10) {
            return new WarningDTO(getString(R.string.txt_confirm_number_boy, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_BOY, womanDTO.getC35A()), Constants.TYPE_CONFIRM);
        }
        if (womanDTO.getC35A() > family.getmTSKHAU() - 1) {
            return new WarningDTO(getString(R.string.txt_number_boy_over_number_member2, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_BOY, womanDTO.getC35A(), family.getmTSKHAU() - 1), Constants.TYPE_NOTI);
        }
        if (memberDTO.getmC02() == 2 && womanDTO.getC35A() > family.getmTSKHAU() - 2) {
            return new WarningDTO(getString(R.string.txt_number_boy_over_number_member, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_BOY, womanDTO.getC35A(), family.getmTSKHAU() - 2), Constants.TYPE_NOTI);
        }
        if (womanDTO.getC35B() > 10) {
            return new WarningDTO(getString(R.string.txt_confirm_number_boy, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_GIRL, womanDTO.getC35B()), Constants.TYPE_CONFIRM);
        }
        if (womanDTO.getC35B() > family.getmTSKHAU() - 1) {
            return new WarningDTO(getString(R.string.txt_number_boy_over_number_member2, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_GIRL, womanDTO.getC35B(), family.getmTSKHAU() - 1), Constants.TYPE_NOTI);
        }
        if (memberDTO.getmC02() == 2 && womanDTO.getC35B() > family.getmTSKHAU() - 2) {
            return new WarningDTO(getString(R.string.txt_number_boy_over_number_member, posMember + 1, memberDTO.getmC01(), Constants.NUMBER_GIRL, womanDTO.getC35B(), family.getmTSKHAU() - 2), Constants.TYPE_NOTI);
        }
        if (memberDTO.getmC02() == 2 && (womanDTO.getC35B() + womanDTO.getC35A()) > family.getmTSKHAU() - 2) {
            return new WarningDTO(getString(R.string.txt_invalid_number_child, posMember + 1, memberDTO.getmC01(), womanDTO.getC35A() + womanDTO.getC35B(), family.getmTSKHAU() - 2), Constants.TYPE_NOTI);
        }
        if ((womanDTO.getC35B() + womanDTO.getC35A()) > family.getmTSKHAU() - 1) {
            return new WarningDTO(getString(R.string.txt_invalid_number_child2, posMember + 1, memberDTO.getmC01(), womanDTO.getC35A() + womanDTO.getC35B(), family.getmTSKHAU() - 1), Constants.TYPE_NOTI);
        }
        return null;
    }

    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
//        if (actionId == EditorInfo.IME_ACTION_NEXT) {
//            if(editText.getTag().toString().equals(Constants.MONTH)){
//
//            }
//
//            return true;
//        }
        return false;
    }

    private WomanDTO updateWoman(String option, String answer) {
        WomanDTO womanDTO = Constants.mStaticObject.getWomanDTO().get(posMember);
        switch (questionDTO.getId()) {
            case Constants.QUESTION_C38:
                if (option.equals(Constants.MONTH)) {
                    womanDTO.setC38T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    womanDTO.setC38N(Integer.parseInt(answer));
                }
                break;
            case Constants.QUESTION_C35:
            case Constants.QUESTION_C36:
            case Constants.QUESTION_C39:
            case Constants.QUESTION_C37:
            case Constants.QUESTION_C40:
                String id = questionDTO.getId();
                if (option.equalsIgnoreCase(Constants.NUMBER_BOY)) {
                    id = id + "A";
                } else if (option.equalsIgnoreCase(Constants.NUMBER_GIRL)) {
                    id = id + "B";
                }
                womanDTO.set(id, Integer.parseInt(answer));
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
                } else {
                    memberDTO.setmC4N(9998);
                    memberDTO.setmC4T(98);
                }
                if (isWoman(memberDTO)) {
                    Constants.mStaticObject.getWomanDTO().add(new WomanDTO(memberDTO.getmIDTV(),
                            memberDTO.getmC01()));
                }
                break;
            case Constants.QUESTION_C23:
                if (option.equals(Constants.MONTH)) {
                    memberDTO.setmC23T(Integer.parseInt(answer));
                } else if (option.equals(Constants.YEAR)) {
                    memberDTO.setmC23N(Integer.parseInt(answer));
                }
                if (isWoman(memberDTO)) {
                    Constants.mStaticObject.getWomanDTO().add(new WomanDTO(memberDTO.getmIDTV(),
                            memberDTO.getmC01()));
                }
                break;
            case Constants.QUESTION_C05:
                int age = Integer.parseInt(answer);
                memberDTO.setmC05(age);
                if (memberDTO.getmC03() == 2 && age >= 10 && age <= 49) {
                    Constants.mStaticObject.getWomanDTO().add(new WomanDTO(memberDTO.getmIDTV(),
                            memberDTO.getmC01()));
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

    @Override
    public void next() {
        Utils.hideKeyboard(activity, lnContent);
        if (validateQuaetion(questionDTO, null) == null) {
            Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
            if (currentIndex < getListQuestion().size() - 1) {
                currentIndex++;
                Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true, getListQuestion(), activity.mFragmentManager, getPosMember());
            }
        } else {
            WarningDTO warning = validateQuaetion(questionDTO, null);
            if (warning.getType() == Constants.TYPE_CONFIRM) {
                DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string.txt_yes, R.string.txt_no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Constants.mStaticObject.getMemberDTO().set(posMember, memberDTO);
                                if (currentIndex < getListQuestion().size() - 1) {
                                    currentIndex++;
                                    Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true, getListQuestion(), activity.mFragmentManager, getPosMember());
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
        if (currentIndex > 0) {
            currentIndex--;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), false, getListQuestion(), activity.mFragmentManager, getPosMember());
        }
    }

}
