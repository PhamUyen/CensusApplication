package com.uyenpham.censusapplication.utils;

import com.uyenpham.censusapplication.db.DeadDAO;
import com.uyenpham.censusapplication.db.FamilyDAO;
import com.uyenpham.censusapplication.db.FamilyDetailDAO;
import com.uyenpham.censusapplication.db.MemberDAO;
import com.uyenpham.censusapplication.db.PeopleDAO;
import com.uyenpham.censusapplication.db.PeopleDetailDAO;
import com.uyenpham.censusapplication.db.WomanDAO;
import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;

import java.util.ArrayList;
import java.util.List;

public class StaticObject {
    private FamilyDTO familyDTO;
    private FamilyDetailDTO familyDetailDTO;
    private  List<WomanDTO> womanDTOs;
    private List<MemberDTO> memberDTOs;
    private PeopleDTO peopleDTO;
    private HouseDTO houseDTO;
    private  List<DeadDTO>  deadDTOs;
    private List<PeopleDetailDTO> peopleDetailDTOs;
    private String idHo;

    private static StaticObject instance;

    public static StaticObject getInstance(){
        if(instance==null)
            instance=new StaticObject();

        return instance;
    }

    public void reset(){
        instance = new StaticObject() ;
    }

    public StaticObject() {
        familyDetailDTO = new FamilyDetailDTO();
        familyDTO = new FamilyDTO();
        womanDTOs = new ArrayList<>();
        memberDTOs = new ArrayList<>();
        peopleDTO = new PeopleDTO();
        houseDTO = new HouseDTO();
        deadDTOs = new ArrayList<>();
        peopleDetailDTOs = new ArrayList<>();
    }

    public String getIdHo() {
        return idHo;
    }

    public void setIdHo(String idHo) {
        this.idHo = idHo;
    }

    public List<PeopleDetailDTO>  getPeopleDetailDTO() {
        return peopleDetailDTOs;
    }

    public void setPeopleDetailDTO(List<PeopleDetailDTO>  peopleDetailDTO) {
        this.peopleDetailDTOs = peopleDetailDTO;
    }

    public FamilyDTO getFamilyDTO() {
        return familyDTO;
    }

    public void setFamilyDTO(FamilyDTO familyDTO) {
        this.familyDTO = familyDTO;
    }

    public FamilyDetailDTO getFamilyDetailDTO() {
        return familyDetailDTO;
    }

    public void setFamilyDetailDTO(FamilyDetailDTO familyDetailDTO) {
        this.familyDetailDTO = familyDetailDTO;
    }

    public  List<WomanDTO> getWomanDTO() {
        return womanDTOs;
    }

    public void setWomanDTO( List<WomanDTO> womanDTO) {
        this.womanDTOs = womanDTO;
    }

    public List<MemberDTO> getMemberDTO() {
        return memberDTOs;
    }

    public void setMemberDTO(List<MemberDTO> memberDTO) {
        this.memberDTOs = memberDTO;
    }

    public PeopleDTO getPeopleDTO() {
        return peopleDTO;
    }

    public void setPeopleDTO(PeopleDTO peopleDTO) {
        this.peopleDTO = peopleDTO;
    }

    public HouseDTO getHouseDTO() {
        return houseDTO;
    }

    public void setHouseDTO(HouseDTO houseDTO) {
        this.houseDTO = houseDTO;
    }

    public List<DeadDTO> getDeadDTO() {
        return deadDTOs;
    }

    public void setDeadDTO(List<DeadDTO> deadDTO) {
        this.deadDTOs = deadDTO;
    }
    public void updateDB(){
        PeopleDAO.getInstance().insert(peopleDTO);
        FamilyDAO.getInstance().insert(familyDTO);
        FamilyDetailDAO.getInstance().insert(familyDetailDTO);
        for(WomanDTO womanDTO : womanDTOs){
            WomanDAO.getInstance().insert(womanDTO);
        }
        for(PeopleDetailDTO peopleDetailDTO : peopleDetailDTOs){
            PeopleDetailDAO.getInstance().insert(peopleDetailDTO);
        }
        for(MemberDTO memberDTO : memberDTOs){
            MemberDAO.getInstance().insert(memberDTO);
        }
        for(DeadDTO deadDTO : deadDTOs){
            DeadDAO.getInstance().insert(deadDTO);
        }
    }
}
