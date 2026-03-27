package com.RESTurantManager.demo.model;

import java.util.ArrayList;

public class resturant {
    private String name;
    private int resturantId;
    private ArrayList<Employee> employees;
    private ArrayList<Employee> managers;
    private ArrayList<Task> checklist;
    private ArrayList<String> categories;
    private ArrayList<Deviation> registeredDeviations;
    
    public resturant(String name, int resturantId, Employee manager) {
        this.name = name;
        this.resturantId = resturantId;
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

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void addManager(Employee manager) {
        this.managers.add(manager);
    }

    public void addTask(Task task) {
        this.checklist.add(task);
    }

    public void addCategory(String category) {
        this.categories.add(category);
    }

    public void addDeviation(Deviation deviation) {
        this.registeredDeviations.add(deviation);
    }
}
