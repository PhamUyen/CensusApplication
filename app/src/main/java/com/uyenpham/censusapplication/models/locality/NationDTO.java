package com.uyenpham.censusapplication.models.locality;

public class NationDTO {
    private String code;
    private String name;

    public NationDTO(String id, String name) {
        this.code = id;
        this.name = name;
    }

    public NationDTO() {
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
