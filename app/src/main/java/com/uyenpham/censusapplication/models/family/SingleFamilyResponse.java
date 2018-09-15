package com.uyenpham.censusapplication.models.family;

import com.google.gson.annotations.SerializedName;

public class SingleFamilyResponse {
    @SerializedName("code")
    private String code;
    @SerializedName("HO")
    private FamilyDTO family;

    public SingleFamilyResponse(String code, FamilyDTO family) {
        this.code = code;
        this.family = family;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FamilyDTO getFamily() {
        return family;
    }

    public void setFamily(FamilyDTO family) {
        this.family = family;
    }
}
