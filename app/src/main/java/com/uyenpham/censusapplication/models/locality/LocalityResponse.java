package com.uyenpham.censusapplication.models.locality;

import com.google.gson.annotations.SerializedName;
import com.uyenpham.censusapplication.models.ListInfoResponse;

import java.util.List;

public class LocalityResponse extends ListInfoResponse {
    @SerializedName("collection")
    private List<LocalityDTO> listLocality;

    public LocalityResponse(List<LocalityDTO> listLocality) {
        this.listLocality = listLocality;
    }

    public LocalityResponse() {
    }

    public List<LocalityDTO> getListLocality() {
        return listLocality;
    }

    public void setListLocality(List<LocalityDTO> listLocality) {
        this.listLocality = listLocality;
    }
}
