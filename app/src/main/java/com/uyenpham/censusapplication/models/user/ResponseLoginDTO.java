
package com.uyenpham.censusapplication.models.user;

import com.google.gson.annotations.SerializedName;

public class ResponseLoginDTO {

    @SerializedName("code")
    private String mCode;
    @SerializedName("profile")
    private ProfileDTO mProfile;
    @SerializedName("token")
    private String mToken;

    public ResponseLoginDTO() {
    }

    public ResponseLoginDTO(String mCode, ProfileDTO mProfile, String mToken) {
        this.mCode = mCode;
        this.mProfile = mProfile;
        this.mToken = mToken;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public ProfileDTO getProfile() {
        return mProfile;
    }

    public void setProfile(ProfileDTO profile) {
        mProfile = profile;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
