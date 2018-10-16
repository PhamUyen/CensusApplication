package com.uyenpham.censusapplication.models.locality;

public class SpinnerDTO {
    private String id;
    private String name;

    public SpinnerDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SpinnerDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
