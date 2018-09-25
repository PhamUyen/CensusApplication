package com.uyenpham.censusapplication.models;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.survey.FakeQuestionsResponse;
import com.uyenpham.censusapplication.models.survey.QuestionDTO;
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
        return new GroupDrawer(App.getInstance().getString(R.string.txt_info), makeListInfo
                ());
    }

    public static ArrayList<QuestionDTO> makeListInfo() {
        String json = Utils.readFromAsset(App.getInstance(), "question_people.json");
        FakeQuestionsResponse fakeQuestionsResponse = null;
        try {
            fakeQuestionsResponse = new Gson().fromJson(json, FakeQuestionsResponse.class);
        }catch (JsonSyntaxException w){
            Logger.e(w);
        }
        return fakeQuestionsResponse == null  ? ( new ArrayList<QuestionDTO>()):fakeQuestionsResponse.getQuestions();
    }

    public static GroupDrawer makePeopleGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_people), makeListPeople());
    }

    //
    public static ArrayList<QuestionDTO> makeListPeople() {
        String json = Utils.readFromAsset(App.getInstance(), "people_question.json");
        FakeQuestionsResponse fakeQuestionsResponse = null;
        try {
             fakeQuestionsResponse = new Gson().fromJson(json, FakeQuestionsResponse.class);
        }catch (JsonSyntaxException w){
            Logger.e(w);
        }
        return fakeQuestionsResponse == null  ? ( new ArrayList<QuestionDTO>()):fakeQuestionsResponse.getQuestions();
    }

    public static GroupDrawer makeWomanGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_woman), makeListWoman());
    }

    public static ArrayList<QuestionDTO> makeListWoman() {
        String json = Utils.readFromAsset(App.getInstance(), "woman_question.json");
        FakeQuestionsResponse fakeQuestionsResponse = null;
        try {
            fakeQuestionsResponse = new Gson().fromJson(json, FakeQuestionsResponse.class);
        }catch (JsonSyntaxException w){
            Logger.e(w);
        }
        return fakeQuestionsResponse == null  ? ( new ArrayList<QuestionDTO>()):fakeQuestionsResponse.getQuestions();
    }

    public static GroupDrawer makeMemberGroup() {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_member), makeListMember());
    }
    //
//
    public static ArrayList<QuestionDTO> makeListMember() {
        String json = Utils.readFromAsset(App.getInstance(), "member_question.json");
        FakeQuestionsResponse fakeQuestionsResponse = null;
        try {
            fakeQuestionsResponse = new Gson().fromJson(json, FakeQuestionsResponse.class);
        }catch (JsonSyntaxException w){
            Logger.e(w);
        }
        return fakeQuestionsResponse == null  ? ( new ArrayList<QuestionDTO>()):fakeQuestionsResponse.getQuestions();
    }

//    public static GroupDrawer makeSalsaGenre() {
//        return new GroupDrawer("Salsa", makeSalsaArtists());
//    }
//
//
//
//    public static List<ChildDrawer> makeSalsaArtists() {
//        ChildDrawer hectorLavoe = new ChildDrawer("Hector Lavoe", "");
//        ChildDrawer celiaCruz = new ChildDrawer("Celia Cruz", "");
//        ChildDrawer willieColon = new ChildDrawer("Willie Colon", "");
//        ChildDrawer marcAnthony = new ChildDrawer("Marc Anthony", "");
//
//        return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
//    }
//
//    public static GroupDrawer makeBluegrassGenre() {
//        return new GroupDrawer("Bluegrass", makeBluegrassArtists());
//    }
//
//
//    public static List<ChildDrawer> makeBluegrassArtists() {
//        ChildDrawer billMonroe = new ChildDrawer("Bill Monroe", "");
//        ChildDrawer earlScruggs = new ChildDrawer("Earl Scruggs", "");
//        ChildDrawer osborneBrothers = new ChildDrawer("Osborne Brothers", "");
//        ChildDrawer johnHartford = new ChildDrawer("John Hartford", "");
//
//        return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
//    }

}
