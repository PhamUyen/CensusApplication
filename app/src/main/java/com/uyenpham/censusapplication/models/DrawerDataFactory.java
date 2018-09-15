package com.uyenpham.censusapplication.models;

import com.uyenpham.censusapplication.App;
import com.uyenpham.censusapplication.R;
import com.uyenpham.censusapplication.models.drawer.ChildDrawer;
import com.uyenpham.censusapplication.models.drawer.GroupDrawer;
import com.uyenpham.censusapplication.models.family.FamilyDTO;

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
        return new GroupDrawer(App.getInstance().getString(R.string.txt_info), makeListInfo(familyDTO));
    }

    public static ArrayList<ChildDrawer> makeListInfo(FamilyDTO familyDTO) {
        ArrayList<ChildDrawer> list = new ArrayList<>();
       list.add( new ChildDrawer("Tỉnh/Thành phố", familyDTO.getMATINH()));
        list.add( new ChildDrawer("HUYỆN/QUẬN/THỊ XÃ/THÀNH PHỐ THUỘC TỈNH:", familyDTO.getMAHUYEN()));
        list.add( new ChildDrawer("XÃ/PHƯỜNG/THỊ TRẤN", familyDTO.getMAXA()));
        list.add( new ChildDrawer("THÔN/XÓM/ẤP/BẢN/TỔ DÂN PHỐ", familyDTO.getMATHON()));
        list.add( new ChildDrawer("ĐỊA BÀN ĐIỀU TRA SỐ", familyDTO.getMADIABAN()));
        list.add(new ChildDrawer("TÊN ĐỊA BÀN ĐIỀU TRA","Chưa xác định"));
        list.add(new ChildDrawer("THÀNH THỊ/NÔNG THÔN","1"));
        list.add(new ChildDrawer("HỘ SỐ",familyDTO.getHOSO()));
        list.add(new ChildDrawer("HỌ VÀ TÊN CHỦ HỘ", familyDTO.getTENCHUHO()));
        list.add(new ChildDrawer("ĐỊA CHỈ CỦA HỘ", familyDTO.getDIACHI()));
        list.add(new ChildDrawer("ĐIỆN THOẠI CỐ ĐỊNH/DI ĐỘNG",familyDTO.getDIENTHOAI()));
        return list;
    }

//    public static GroupDrawer makeJazzGenre() {
//        return new GroupDrawer("Jazz", makeJazzArtists());
//    }
//
//    public static List<ChildDrawer> makeJazzArtists() {
//        ChildDrawer milesDavis = new ChildDrawer("Miles Davis", "");
//        ChildDrawer ellaFitzgerald = new ChildDrawer("Ella Fitzgerald", "");
//        ChildDrawer billieHoliday = new ChildDrawer("Billie Holiday", "");
//
//        return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
//    }
//
//    public static GroupDrawer makeClassicGenre() {
//        return new GroupDrawer("Classic", makeClassicArtists());
//    }
//
//
//    public static List<ChildDrawer> makeClassicArtists() {
//        ChildDrawer beethoven = new ChildDrawer("Ludwig van Beethoven", "");
//        ChildDrawer bach = new ChildDrawer("Johann Sebastian Bach", "");
//        ChildDrawer brahms = new ChildDrawer("Johannes Brahms", "");
//        ChildDrawer puccini = new ChildDrawer("Giacomo Puccini", "");
//
//        return Arrays.asList(beethoven, bach, brahms, puccini);
//    }
//
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
