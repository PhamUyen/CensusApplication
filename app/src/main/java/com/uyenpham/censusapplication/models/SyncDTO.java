package com.uyenpham.censusapplication.models;

import com.uyenpham.censusapplication.models.family.DeadDTO;
import com.uyenpham.censusapplication.models.family.FamilyDetailDTO;
import com.uyenpham.censusapplication.models.family.MemberDTO;
import com.uyenpham.censusapplication.models.family.PeopleDTO;
import com.uyenpham.censusapplication.models.family.PeopleDetailDTO;
import com.uyenpham.censusapplication.models.family.WomanDTO;

import java.util.ArrayList;

public class SyncDTO {
    String IDHO;
    FamilyDetailDTO THONGTINHO;
    ArrayList<PeopleDTO> THONGTINNHANKHAUHO;
    ArrayList<WomanDTO> THONGTINPHUNU;
    ArrayList<DeadDTO> THONGTINCHET;
    ArrayList<MemberDTO>THONGTINTHANHVIEN;
    ArrayList<PeopleDetailDTO>THONGTINNHANKHAU;

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

    public ArrayList<PeopleDTO> getTHONGTINNHANKHAUHO() {
        return THONGTINNHANKHAUHO;
    }

    public void setTHONGTINNHANKHAUHO(ArrayList<PeopleDTO> THONGTINNHANKHAUHO) {
        this.THONGTINNHANKHAUHO = THONGTINNHANKHAUHO;
    }

    public ArrayList<WomanDTO> getTHONGTINPHUNU() {
        return THONGTINPHUNU;
    }

    public void setTHONGTINPHUNU(ArrayList<WomanDTO> THONGTINPHUNU) {
        this.THONGTINPHUNU = THONGTINPHUNU;
    }

    public ArrayList<DeadDTO> getTHONGTINCHET() {
        return THONGTINCHET;
    }

    public void setTHONGTINCHET(ArrayList<DeadDTO> THONGTINCHET) {
        this.THONGTINCHET = THONGTINCHET;
    }

    public ArrayList<MemberDTO> getTHONGTINTHANHVIEN() {
        return THONGTINTHANHVIEN;
    }

    public void setTHONGTINTHANHVIEN(ArrayList<MemberDTO> THONGTINTHANHVIEN) {
        this.THONGTINTHANHVIEN = THONGTINTHANHVIEN;
    }

    public ArrayList<PeopleDetailDTO> getTHONGTINNHANKHAU() {
        return THONGTINNHANKHAU;
    }

    public void setTHONGTINNHANKHAU(ArrayList<PeopleDetailDTO> THONGTINNHANKHAU) {
        this.THONGTINNHANKHAU = THONGTINNHANKHAU;
    }
}
