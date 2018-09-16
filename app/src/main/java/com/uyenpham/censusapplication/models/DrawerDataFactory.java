package com.uyenpham.censusapplication.models;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
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


    public static GroupDrawer makeInfoGroup(FamilyDTO familyDTO) {
        return new GroupDrawer(App.getInstance().getString(R.string.txt_info), makeListInfo
                (familyDTO));
    }

    public static ArrayList<QuestionDTO> makeListInfo(FamilyDTO familyDTO) {
        ArrayList<QuestionDTO> list = new ArrayList<>();
        list.add(new QuestionDTO("Q00", "Q00", "Tỉnh/Thành phố", 1, null, "Tỉnh/Thành phố",
                familyDTO.getMATINH()));
        list.add(new QuestionDTO("Q01", "Q01", "huyện/quận/thị xã/thành phố thuộc tỉnh", 1, null,
                "huyện/quận/thị xã/thành phố thuộc tỉnh:", familyDTO.getMAHUYEN()));
        list.add(new QuestionDTO("Q02", "Q02", "xã/phường/thị trấn", 1, null, "xã/phường/thị " +
                "trấn", familyDTO.getMAXA()));
        list.add(new QuestionDTO("Q03", "Q03", "thôn/xóm/ấp/bản/tổ dân phố", 1, null,
                "thôn/xóm/ấp/bản/tổ dân phố", familyDTO.getMATHON()));
        list.add(new QuestionDTO("Q04", "Q04", "Địa bàn đIều tra số", 1, null, "Địa bàn đIều tra " +
                "số", familyDTO.getMADIABAN()));
        list.add(new QuestionDTO("Q05", "Q05", "Tên địa bàn đIều tra", 1, null, "Tên địa bàn đIều" +
                " tra", "Chưa xác định"));
        list.add(new QuestionDTO("Q06", "Q06", "THÀNH THỊ/NÔNG THÔN", 1, null, "THÀNH THỊ/NÔNG " +
                "THÔN", "1"));
        list.add(new QuestionDTO("Q07", "Q07", "Hộ số", 1, null, "Hộ số", familyDTO.getHOSO()));
        list.add(new QuestionDTO("Q08", "Q08", "Họ và tên chủ hộ", 1, null, "Họ và tên chủ hộ",
                familyDTO.getTENCHUHO()));
        list.add(new QuestionDTO("Q09", "Q09", "Địa chỉ của hộ", 1, null, "Địa chỉ của hộ",
                familyDTO.getDIACHI()));
        list.add(new QuestionDTO("Q010", "Q010", "Điện thoạI cố định/di động", 1, null, "Điện " +
                "thoạI cố định/di động", familyDTO.getDIENTHOAI()));
        return list;
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
        return new GroupDrawer(App.getInstance().getString(R.string.txt_member), makeListWoman());
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
