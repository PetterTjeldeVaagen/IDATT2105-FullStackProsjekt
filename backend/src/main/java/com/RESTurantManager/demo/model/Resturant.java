package com.RESTurantManager.demo.model;

import java.util.ArrayList;

/**
 * Model class representing a restaurant. Contains fields for restaurant details such as name, restaurant ID, join code, employees, managers, checklist tasks, categories, and registered deviations.
 */
public class Resturant {
    private String name;
    private int resturantId;
    private String joinCode;
    private ArrayList<Employee> employees;
    private ArrayList<Employee> managers;
    private ArrayList<Task> checklist;
    private ArrayList<String> categories;
    private ArrayList<Deviation> registeredDeviations;

    /**
     * Default constructor for Resturant.
     */
    public Resturant() { }
    
    /**
     * Parameterized constructor for Resturant.
     * @param name the name of the restaurant
     * @param resturantId the unique ID of the restaurant
     * @param manager the initial manager of the restaurant
     * @param joinCode the code used for employees to join the restaurant
     */
    public Resturant(String name, int resturantId, Employee manager, String joinCode) {
        this.name = name;
        this.resturantId = resturantId;
        this.joinCode = joinCode;
        this.employees = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.checklist = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.registeredDeviations = new ArrayList<>();
        this.managers.add(manager);
        this.employees.add(manager);
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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Employee> managers) {
        this.managers = managers;
    }

    public ArrayList<Task> getChecklist() {
        return checklist;
    }

    public void setChecklist(ArrayList<Task> checklist) {
        this.checklist = checklist;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<Deviation> getRegisteredDeviations() {
        return registeredDeviations;
    }

    public void setRegisteredDeviations(ArrayList<Deviation> registeredDeviations) {
        this.registeredDeviations = registeredDeviations;
    }

    /**
     * Adds an employee to the restaurant's employee list.
     * @param employee the employee to be added to the restaurant
     */
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    /**
     * Adds a manager to the restaurant's manager list.
     * @param manager the manager to be added to the restaurant
     */
    public void addManager(Employee manager) {
        this.managers.add(manager);
    }

    /**
     * Adds a task to the restaurant's checklist.
     * @param task the task to be added to the restaurant's checklist
     */
    public void addTask(Task task) {
        this.checklist.add(task);
    }

    /**
     * Adds a category to the restaurant's category list.
     * @param category the category to be added to the restaurant's category list
     */
    public void addCategory(String category) {
        this.categories.add(category);
    }

    /**
     * Adds a deviation to the restaurant's registered deviations list.
     * @param deviation the deviation to be added to the restaurant's registered deviations list
     */
    public void addDeviation(Deviation deviation) {
        this.registeredDeviations.add(deviation);
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
