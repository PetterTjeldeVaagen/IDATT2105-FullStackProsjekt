package com.RESTurantManager.demo.db.requests;

public class ResturantRequest {
    private String name;
    private int resturantId;
    private int employeeId;
    private String joinCode;

    public ResturantRequest() { }

    public ResturantRequest(String name, int employeeId, String joinCode) {
        this.name = name;
        this.employeeId = employeeId;
        this.joinCode = joinCode;
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
