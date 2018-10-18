package com.uyenpham.censusapplication.models.locality;

public class DistrictDTO {
    private String provinceCode;
    private String districtCode;
    private String name;

    public DistrictDTO() {
    }

    public DistrictDTO(String provinceCode, String districtCode, String name) {
        this.provinceCode = provinceCode;
        this.districtCode = districtCode;
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
