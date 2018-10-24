package com.uyenpham.censusapplication.models;

import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;

import java.util.ArrayList;
import java.util.List;

public class SyncDTO {
    String IDHO;
    FamilyDetailDTO THONGTINHO;
    List<PeopleDTO> THONGTINNHANKHAUHO;
    List<WomanDTO> THONGTINPHUNU;
    List<DeadDTO> THONGTINCHET;
    List<MemberDTO>THONGTINTHANHVIEN;
    List<PeopleDetailDTO>THONGTINNHANKHAU;

    public SyncDTO() {
    }

    public SyncDTO(String IDHO, FamilyDetailDTO THONGTINHO, ArrayList<PeopleDTO>
            THONGTINNHANKHAUHO, ArrayList<WomanDTO> THONGTINPHUNU, ArrayList<DeadDTO>
            THONGTINCHET, ArrayList<MemberDTO> THONGTINTHANHVIEN, ArrayList<PeopleDetailDTO>
            THONGTINNHANKHAU) {
        this.IDHO = IDHO;
        this.THONGTINHO = THONGTINHO;
        this.THONGTINNHANKHAUHO = THONGTINNHANKHAUHO;
        this.THONGTINPHUNU = THONGTINPHUNU;
        this.THONGTINCHET = THONGTINCHET;
        this.THONGTINTHANHVIEN = THONGTINTHANHVIEN;
        this.THONGTINNHANKHAU = THONGTINNHANKHAU;
    }

    public String getIDHO() {
        return IDHO;
    }

    public void setIDHO(String IDHO) {
        this.IDHO = IDHO;
    }

    public FamilyDetailDTO getTHONGTINHO() {
        return THONGTINHO;
    }

    public void setTHONGTINHO(FamilyDetailDTO THONGTINHO) {
        this.THONGTINHO = THONGTINHO;
    }

    public List<PeopleDTO> getTHONGTINNHANKHAUHO() {
        return THONGTINNHANKHAUHO;
    }

    public void setTHONGTINNHANKHAUHO(List<PeopleDTO> THONGTINNHANKHAUHO) {
        this.THONGTINNHANKHAUHO = THONGTINNHANKHAUHO;
    }

    public List<WomanDTO> getTHONGTINPHUNU() {
        return THONGTINPHUNU;
    }

    public void setTHONGTINPHUNU(List<WomanDTO> THONGTINPHUNU) {
        this.THONGTINPHUNU = THONGTINPHUNU;
    }

    public List<DeadDTO> getTHONGTINCHET() {
        return THONGTINCHET;
    }

    public void setTHONGTINCHET(List<DeadDTO> THONGTINCHET) {
        this.THONGTINCHET = THONGTINCHET;
    }

    public List<MemberDTO> getTHONGTINTHANHVIEN() {
        return THONGTINTHANHVIEN;
    }

    public void setTHONGTINTHANHVIEN(List<MemberDTO> THONGTINTHANHVIEN) {
        this.THONGTINTHANHVIEN = THONGTINTHANHVIEN;
    }

    public List<PeopleDetailDTO> getTHONGTINNHANKHAU() {
        return THONGTINNHANKHAU;
    }

    public void setTHONGTINNHANKHAU(List<PeopleDetailDTO> THONGTINNHANKHAU) {
        this.THONGTINNHANKHAU = THONGTINNHANKHAU;
    }
}
