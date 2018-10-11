package com.uyenpham.censusapplication.ui.fragments;

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
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class NumberInputFragment extends BaseTypeFragment implements EditText
        .OnEditorActionListener,
        INextQuestion, IPreviousQuestion{
    @Bind(R.id.tv_question)
    TextView tvQuestion;
    @Bind(R.id.lnContent)
    LinearLayout lnContent;


    private QuestionDTO questionDTO;
    private int posMember;
    private MemberDTO memberDTO;
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
                        .get(question.getId())),listOption.indexOf(option));
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
        if(option.equals(Constants.MONTH)){
            editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
        }else if(option.equals(Constants.YEAR)){
            editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        }
        if(posOption == listOption.size() -1){
            editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }else {
            editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
        if(questionDTO.getId().equals(Constants.QUESTION_C05)){
            if(memberDTO.getmC4N() != 9998){
                int age = Calendar.getInstance().get(Calendar.YEAR) - memberDTO.getmC4N();
                editText.setText(String.valueOf(age));
                editText.setEnabled(false);
            }else {
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

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }


    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        String idTV = Constants.mStaticObject.getIdHo() + Constants.mStaticObject
                .getPeopleDetailDTO().get(0).getSTT();
        MemberDTO chuho = MemberDAO.getInstance().findById(idTV);
        switch (question.getId()) {
            case Constants.QUESTION_C04:
                if (memberDTO.getmC4T() <0 ||(memberDTO.getmC4T() >12 && memberDTO.getmC4T() != 98) ||
                        (memberDTO.getmC4N() == Calendar.getInstance().get(Calendar.YEAR) && memberDTO.getmC4T() > Calendar.getInstance().get(Calendar.MONTH)
                        )) {
                    return new WarningDTO(getString(R.string.txt_invalid_month,posMember+1,memberDTO.getmC01(),memberDTO.getmC4T()),Constants.TYPE_NOTI);
                }
            case Constants.QUESTION_C05:
                if(posMember == 0 && memberDTO.getmC05() <6){
                    return new WarningDTO(getString(R.string.txt_age_too_small,memberDTO.getmC01(), memberDTO.getmC05()),Constants.TYPE_CONFIRM);
                }
                if(posMember != 0 && memberDTO.getmC02() ==1 && Math.abs(chuho.getmC05()-memberDTO.getmC05()) >= 20){
                    return new WarningDTO(getString(R.string.txt_age_couple
                            ,posMember+1,memberDTO.getmC01(),memberDTO.getmC05(),chuho.getmC01(),chuho.getmC05(),Math.abs(chuho.getmC05() - memberDTO.getmC05())),Constants.TYPE_CONFIRM);
                }
                if(posMember != 0 && memberDTO.getmC02() ==4 && chuho.getmC05() >= memberDTO.getmC05()){
                    return new WarningDTO(getString(R.string.txt_age_parent
                            ,posMember+1,memberDTO.getmC01(),memberDTO.getmC05(),chuho.getmC01(),chuho.getmC05()),Constants.TYPE_NOTI);
                }
                if(posMember != 0 && memberDTO.getmC02() == 3&& chuho.getmC05() <= 30){
                    return new WarningDTO(getString(R.string.txt_age_grandparent
                            ,posMember+1,memberDTO.getmC01(),chuho.getmC01(),chuho.getmC05()),Constants.TYPE_CONFIRM);
                }
            case Constants.QUESTION_C19:

            default:
                return null;
        }
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
                if (option.equals(Constants.NUMBER_BOY)) {
                    id = id + "A";
                } else if (option.equals(Constants.NUMBER_GIRL)) {
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
        Utils.hideKeyboard(activity,lnContent);
          if(validateQuaetion(questionDTO, null)  == null){
              Constants.mStaticObject.getMemberDTO().set(posMember,memberDTO);
              if (currentIndex < getListQuestion().size()-1) {
                  currentIndex++;
                  Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,getListQuestion(),activity.mFragmentManager,getPosMember());
              }
          }else {
              //show dialog error
          }
    }

    @Override
    public void previuos() {
        Utils.hideKeyboard(activity,lnContent);
        if (currentIndex > 0) {
            currentIndex--;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), false,getListQuestion(),activity.mFragmentManager,getPosMember());
        }
    }

}
