package com.uyenpham.censusapplication.utils;

import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.HouseDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;

public class StaticObject {
    private FamilyDTO familyDTO;
    private FamilyDetailDTO familyDetailDTO;
    private WomanDTO womanDTO;
    private MemberDTO memberDTO;
    private PeopleDTO peopleDTO;
    private HouseDTO houseDTO;
    private DeadDTO deadDTO;

    public StaticObject() {
        familyDetailDTO = new FamilyDetailDTO();
        familyDTO = new FamilyDTO();
        womanDTO = new WomanDTO();
        memberDTO = new MemberDTO();
        peopleDTO = new PeopleDTO();
        houseDTO = new HouseDTO();
        deadDTO = new DeadDTO();
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

    public WomanDTO getWomanDTO() {
        return womanDTO;
    }

    public void setWomanDTO(WomanDTO womanDTO) {
        this.womanDTO = womanDTO;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
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

    public DeadDTO getDeadDTO() {
        return deadDTO;
    }

    public void setDeadDTO(DeadDTO deadDTO) {
        this.deadDTO = deadDTO;
    }
}
