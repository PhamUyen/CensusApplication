package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;
import com.uyenpham.censusapplication.models.ListInfoResponse;

import java.util.List;

public class FamilyResponse extends ListInfoResponse {
    @SerializedName("collection")
    private List<FamilyDTO> listLocality;

    public FamilyResponse(List<FamilyDTO> listLocality) {
        this.listLocality = listLocality;
    }

    public List<FamilyDTO> getListLocality() {
        return listLocality;
    }

    public void setListLocality(List<FamilyDTO> listLocality) {
        this.listLocality = listLocality;
    }
}
