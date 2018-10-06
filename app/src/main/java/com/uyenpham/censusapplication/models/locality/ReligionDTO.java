package com.uyenpham.censusapplication.models.locality;

public class ReligionDTO {
    private int id;
    private String name;

    public ReligionDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ReligionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
