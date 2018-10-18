package com.uyenpham.censusapplication.models.locality;

public class ProvinceDTO {
    private String code;
    private String name;

    public ProvinceDTO(String id, String name) {
        this.code = id;
        this.name = name;
    }

    public ProvinceDTO() {
    }

    public String getId() {
        return code;
    }

    public void setId(String id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
