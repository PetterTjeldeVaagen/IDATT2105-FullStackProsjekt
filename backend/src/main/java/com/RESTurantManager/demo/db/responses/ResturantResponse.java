package com.RESTurantManager.demo.db.responses;

public class ResturantResponse {
    private String name;
    private int resturantId;

    public ResturantResponse() { }

    public ResturantResponse(String name, int resturantId) {
        this.name = name;
        this.resturantId = resturantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

}
