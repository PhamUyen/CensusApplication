package com.uyenpham.censusapplication.models.drawer;

import android.os.Parcel;
import android.os.Parcelable;

public class ChildDrawer implements Parcelable {
    private String name;
    private String detail;

    public ChildDrawer(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    protected ChildDrawer(Parcel in) {
        name = in.readString();
        detail = in.readString();
    }

    public static final Creator<ChildDrawer> CREATOR = new Creator<ChildDrawer>() {
        @Override
        public ChildDrawer createFromParcel(Parcel in) {
            return new ChildDrawer(in);
        }

        @Override
        public ChildDrawer[] newArray(int size) {
            return new ChildDrawer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(detail);
    }
}
