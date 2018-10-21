package com.uyenpham.censusapplication.models;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;
import com.uyenpham.censusapplication.models.survey.FakeQuestionsResponse;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
import com.uyenpham.censusapplication.utils.Constants;
import com.uyenpham.censusapplication.utils.Logger;
import com.uyenpham.censusapplication.utils.Utils;

import java.util.ArrayList;

public class DrawerDataFactory {
//    public static List<GroupDrawer> makeGenres() {
//        return Arrays.asList(makeInfoGroup(),
//                makeJazzGenre(),
//                makeClassicGenre(),
//                makeSalsaGenre(),
//                makeBluegrassGenre());
//    }


    public static GroupDrawer makeInfoGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_info), makeListQuestion("question_people.json"));
    }

    public static GroupDrawer makePeopleGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_people), makeListQuestion("people_2.json"));
    }


    public static GroupDrawer makeWomanGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_woman), genListWoman());
    }
    public static ArrayList<QuestionDTO> genListWoman(){
        ArrayList<QuestionDTO> list = new ArrayList<>();
        for(WomanDTO womanDTO : Constants.mStaticObject.getWomanDTO()){
            QuestionDTO questionDTO = new QuestionDTO(womanDTO.getTenTV());
            list.add(questionDTO);
        }
        return list;
    }
    public static GroupDrawer makeMemberGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_member),genListMember());
    }
    public static ArrayList<QuestionDTO> genListMember(){
        ArrayList<QuestionDTO> list = new ArrayList<>();
        for(PeopleDetailDTO peopleDetailDTO : Constants.mStaticObject.getPeopleDetailDTO()){
            QuestionDTO questionDTO = new QuestionDTO(peopleDetailDTO.getQ1A());
            list.add(questionDTO);
        }
        return list;
    }
    public static ArrayList<QuestionDTO> makeListQuestion(String file) {
        String json = Utils.readFromAsset(App.getInstance(), file);
        FakeQuestionsResponse fakeQuestionsResponse = null;
        try {
            fakeQuestionsResponse = new Gson().fromJson(json, FakeQuestionsResponse.class);
        }catch (JsonSyntaxException w){
            Logger.e(w);
        }
        return fakeQuestionsResponse == null  ? ( new ArrayList<QuestionDTO>()):fakeQuestionsResponse.getQuestions();
    }

}
