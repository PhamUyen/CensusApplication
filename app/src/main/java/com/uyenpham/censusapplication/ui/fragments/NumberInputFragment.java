package com.uyenpham.censusapplication.ui.fragments;

import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
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
        INextQuestion, IPreviousQuestion {
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

    private LinearLayout genlayoutInputNumber(String option, String answer, int posOption) {
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        int padding = mActivity.getResources().getDimensionPixelOffset(R.dimen.padding_small);

        LinearLayout linearLayout = new LinearLayout(mActivity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(getLayoutParams(0, margin, 0, margin / 2));

        TextView textView = new TextView(mActivity);
        textView.setLayoutParams(getLayoutParamsWeghtWidth(0, 0, margin, 0, 1.5f));
        textView.setText(option);
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        linearLayout.addView(textView);

        EditText editText = new EditText(mActivity);
        editText.setLayoutParams(getLayoutParamsWeghtWidth(margin, 0, 0, 0, 2f));
        textView.setTextAppearance(getActivity(), R.style.TextView_Option);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setBackgroundResource(R.drawable.bg_edit_text);
        editText.setTag(option);
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
        editText.setPadding(padding, padding, padding, padding);
        linearLayout.addView(editText);

        return linearLayout;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }


    @Override
    public boolean validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        switch (question.getId()) {
            case Constants.QUESTION_C04:
                if (memberDTO.getmC4N() == null || memberDTO.getmC4T() == null
                ||((memberDTO.getmC4N() == Calendar.getInstance().get(Calendar.YEAR))
                        && memberDTO.getmC4T() > Calendar.getInstance().get(Calendar.MONTH))
                        ) {
                    return false;
                } else {
                    return true;
                }
            case Constants.QUESTION_C05:
                if(memberDTO != null && memberDTO.getmC02() ==1 && memberDTO.getmC05() <6){
                    return false;
                }else {
                    return true;
                }
            default:
                return true;
        }
    }

    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
            String action = editText.getTag().toString();
            switch (questionDTO.getSurvey()) {
                case Constants.SURVEY_MEMBER:
                    Constants.mStaticObject.getMemberDTO().set(posMember, updateMember(action,
                            editText.getText().toString()));
                    break;
                case Constants.SURVEY_WOMAN:
                    Constants.mStaticObject.getWomanDTO().set(posMember, updateWoman(action,
                            editText.getText().toString()));
                    break;
                default:
                    break;
            }

            return true;
        }
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
          if(validateQuaetion(questionDTO, null)){
              Constants.mStaticObject.getMemberDTO().set(posMember,memberDTO);
              if (currentIndex < getListQuestion().size()-1) {
                  currentIndex++;
                  Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,getListQuestion(),activity.mFragmentManager,getPosMember());
              }
          }
    }

    @Override
    public void previuos() {
        if (currentIndex > 0) {
            currentIndex--;
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), false,getListQuestion(),activity.mFragmentManager,getPosMember());
        }
    }
}
