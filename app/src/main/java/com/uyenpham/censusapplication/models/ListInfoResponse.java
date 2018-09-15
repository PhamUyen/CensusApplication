
package com.uyenpham.censusapplication.models;

import com.google.gson.annotations.SerializedName;

public class ListInfoResponse {

    @SerializedName("code")
    private String mCode;
//    @SerializedName("collection")
//    private List<Collection> mCollection;
    @SerializedName("items")
    private Long mItems;
    @SerializedName("next")
    private Boolean mNext;
    @SerializedName("page")
    private Long mPage;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

//    public List<Collection> getCollection() {
//        return mCollection;
//    }

//    public void setCollection(List<Collection> collection) {
//        mCollection = collection;
//    }

    public Long getItems() {
        return mItems;
    }

    public void setItems(Long items) {
        mItems = items;
    }

    public Boolean getNext() {
        return mNext;
    }

    public void setNext(Boolean next) {
        mNext = next;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

}
