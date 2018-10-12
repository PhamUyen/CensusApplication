package com.uyenpham.censusapplication.ui.fragments;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.survey.AnswerDTO;
import com.uyenpham.censusapplication.models.survey.OptionDTO;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.models.survey.WarningDTO;
import com.uyenpham.censusapplication.ui.adapters.RadioButtonAdapter;
import com.uyenpham.censusapplication.ui.interfaces.INextQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IPreviousQuestion;
import com.uyenpham.censusapplication.ui.interfaces.IRadioButtonClick;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.DialogUtils;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;

import static com.uyenpham.censusapplication.ui.activities.SurveyActivity.currentIndex;

public class MultiSelectionFragment extends BaseTypeFragment implements IRadioButtonClick,
        INextQuestion, IPreviousQuestion {
    @Bind(R.id.tv_question)
    TextView tvQuestion;

    @Bind(R.id.list_checkbox)
    RecyclerView listCheckbox;

    private QuestionDTO questionDTO;
    ArrayList<OptionDTO> listOption;
    private RadioButtonAdapter adapter;
    private int posMember;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multi_selection;
    }

    @Override
    protected void createView(View view) {
        listCheckbox.removeAllViews();

        posMember = getPosMember();
        questionDTO = getQuestionDTO();
        loadQuestion(questionDTO);
    }


    private CheckBox getCheckbox(Integer id, String text, String tag, String value) {
        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setId(id);
        checkBox.setText(text);
        checkBox.setTag(tag);
        checkBox.setTextAppearance(getActivity(), R.style.RadioButton_Full);
        int margin = mActivity.getResources().getDimensionPixelOffset(R.dimen.margin_small_x);
        checkBox.setLayoutParams(getLayoutParams(margin, margin, margin, margin));
        checkBox.setButtonDrawable(R.drawable.bg_radio_button);
        checkBox.setPadding(margin, 0, 0, 0);
        if (value != null && value.equals(tag)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        return checkBox;
    }


    @Override
    public boolean loadQuestion(QuestionDTO question) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listCheckbox.setLayoutManager(linearLayoutManager);
        tvQuestion.setText(question.getQuestion());
        listOption = question.getOptions();
        adapter = new RadioButtonAdapter(listOption);
        listCheckbox.setAdapter(adapter);
        adapter.setListener(this);
        return false;
    }

    @Override
    public WarningDTO validateQuaetion(QuestionDTO question, AnswerDTO answer) {
        if(question.getId().equalsIgnoreCase(Constants.QUESTION_Q65)){
            if((houseDTO.getC65A() == null) && (houseDTO.getC65B() == null) && (houseDTO.getC65C() ==null)
                    && (houseDTO.getC65D() == null) && (houseDTO.getC65E() == null) && (houseDTO.getC65F() == null)
                    && (houseDTO.getC65G() == null) &&(houseDTO.getC65H() == null) && (houseDTO.getC65I() == null)
                    && houseDTO.getC65J() == null && houseDTO.getC65K() == null){
                return new WarningDTO(getString(R.string.txt_no_equipment), Constants.TYPE_CONFIRM);
            }
            if(houseDTO.getC65A().equalsIgnoreCase("A") && houseDTO.getC65E().equalsIgnoreCase("E")
                    && houseDTO.getC65F().equalsIgnoreCase("F") && houseDTO.getC65H().equalsIgnoreCase("H")
                    && (houseDTO.getC61() ==3 || houseDTO.getC61() ==4)
                    && (houseDTO.getC62() == 3 || houseDTO.getC62() ==4)
                    && (houseDTO.getC64() ==4)){
                return new WarningDTO(getString(R.string.txt_warning_equipment,houseDTO.getC61(),houseDTO.getC62()), Constants.TYPE_CONFIRM);
            }
        }
        return null;
    }

    @Override
    public void onRadioClick(int pos, boolean isChecked) {
        OptionDTO option = listOption.get(pos);
        option.setSelected(!isChecked);
        adapter.notifyDataSetChanged();
        if(questionDTO.getId().equalsIgnoreCase(Constants.QUESTION_Q9)){

        }else if(questionDTO.getId().equalsIgnoreCase(Constants.QUESTION_Q65)){
            switch (pos) {
                case 0:
                    houseDTO.setC65A(isChecked ? "A" : null);
                    familyDetailDTO.setmC65A(isChecked ? "A" : null);
                    break;
                case 1:
                    houseDTO.setC65B(isChecked ? "B" : null);
                    familyDetailDTO.setmC65B(isChecked ? "B" : null);
                    break;
                case 2:
                    houseDTO.setC65C(isChecked ? "C" : null);
                    familyDetailDTO.setmC65C(isChecked ? "C" : null);
                    break;
                case 3:
                    houseDTO.setC65D(isChecked ? "D" : null);
                    familyDetailDTO.setmC65D(isChecked? "D" : null);
                    break;
                case 4:
                    houseDTO.setC65E(isChecked ? "E" : null);
                    familyDetailDTO.setmC65D(isChecked? "E" : null);
                    break;
                case 5:
                    houseDTO.setC65F(isChecked ? "F" : null);
                    familyDetailDTO.setmC65D(isChecked? "F" : null);
                    break;
                case 6:
                    houseDTO.setC65G(isChecked ? "G" : null);
                    familyDetailDTO.setmC65D(isChecked? "G" : null);
                    break;
                case 7:
                    houseDTO.setC65H(isChecked ? "H" : null);
                    familyDetailDTO.setmC65D(isChecked? "H" : null);
                    break;
                case 8:
                    houseDTO.setC65I(isChecked ? "I" : null);
                    familyDetailDTO.setmC65D(isChecked? "I" : null);
                    break;
                case 9:
                    houseDTO.setC65J(isChecked ? "K" : null);
                    familyDetailDTO.setmC65D(isChecked? "K" : null);
                    break;
                case 10:
                    houseDTO.setC65K(isChecked ? "L" : null);
                    familyDetailDTO.setmC65D(isChecked? "L" : null);
                    break;
                case 11:
                    houseDTO.setC65L(isChecked ? "M" : null);
                    familyDetailDTO.setmC65D(isChecked? "M" : null);
                    break;
                default:
                    break;

            }
        }

    }

    @Override
    public void next() {
        switch (questionDTO.getId()) {
            case Constants.QUESTION_Q9:
                    if (!Constants.mStaticObject.getPeopleDetailDTO().isEmpty()) {
                        activity.survey = Constants.SURVEY_MEMBER;
                        activity.isMember = true;
                        activity.setListPeople(0);
                        activity.getNavigationBar().setTitle("1 - " + Constants.mStaticObject
                                .getPeopleDetailDTO().get(0).getQ1());
                    } else {
                        nextFragment();
                    }
                break;
            case Constants.QUESTION_Q65:
                if(validateQuaetion(questionDTO,null) == null){
                    Constants.mStaticObject.setHouseDTO(houseDTO);
                        Constants.mStaticObject.updateDB();
                        activity.finish();
                }else {
                    WarningDTO warning = validateQuaetion(questionDTO, null);
                    if(warning.getType() == Constants.TYPE_CONFIRM){
                        DialogUtils.showErrorAlert2Option(activity, warning.getMessage(), R.string.txt_yes, R.string.txt_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Constants.mStaticObject.setHouseDTO(houseDTO);
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
                    }else {
                        DialogUtils.showErrorAlert(activity, warning.getMessage());
                    }
                }
                break;
            default:
                break;
        }
    }

    private void nextFragment() {
        currentIndex++;
        if (currentIndex < getListQuestion().size()) {
            saveAnswerToSurvey(questionDTO,posMember);
            Utils.replcaeFragmentByType(getListQuestion().get(currentIndex), true,
                    getListQuestion(), activity.mFragmentManager, getPosMember());
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
