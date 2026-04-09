package com.RESTurantManager.demo.db.requests;

/**
 * Request class for restaurant. Contains fields for restaurant details such as name, restaurant ID, employee ID and join code.
 */
public class ResturantRequest {
    private String name;
    private int resturantId;
    private int employeeId;
    private String joinCode;

    /**
     * Default constructor for ResturantRequest.
     */
    public ResturantRequest() { }

    /**
     * Parameterized constructor for ResturantRequest.
     * @param name the name of the restaurant
     * @param employeeId the ID of the employee associated with the restaurant
     * @param joinCode the join code for the restaurant
     */
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
