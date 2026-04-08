package com.RESTurantManager.demo.db.responses;

public class ResturantResponse {
    private String name;
    private int resturantId;
    private String joinCode;
    
    public ResturantResponse() { }

    public ResturantResponse(String name) {
        this.name = name;
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

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public String getJoinCode() {
        return joinCode;
    }
}
