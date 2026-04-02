package com.RESTurantManager.demo.db.requests;

public class ResturantRequest {
    private String name;
    private int resturantId;
    private int managerId;

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public ResturantRequest() { }

    public ResturantRequest(String name, int resturantId, int managerId) {
        this.name = name;
        this.resturantId = resturantId;
        this.managerId = managerId;
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
