package com.RESTurantManager.demo.db.responses;

/**
 * Response class for restaurant-related operations. Contains fields for restaurant details such as name, restaurant ID and join code.
 */
public class ResturantResponse {
    private String name;
    private int resturantId;
    private String joinCode;
    
    /**
     * Default constructor for ResturantResponse.
     */
    public ResturantResponse() { }

    /**
     * Parameterized constructor for ResturantResponse.
     * @param name the name of the restaurant
     */
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
