package com.RESTurantManager.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.db.responses.ResturantResponse;

@ExtendWith(MockitoExtension.class)
public class ResturantServiceTest {
    @Mock
    private ResturantRepository resturantRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private ResturantService resturantService;

    Resturant resturant;

    Employee employee;

    Employee manager;

    @BeforeEach
    public void setUp() {
        resturant = new Resturant();
        resturant.setResturantId(1);
        resturant.setName("Test Resturant");
        resturant.setJoinCode("joinCode");

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Test Employee");
        employee.setEmail("test@mail.com");
        employee.setPassword("password");

        manager = new Employee();
        manager.setEmployeeId(2);
        manager.setName("Test Manager");
        manager.setEmail("manager@mail.com");
        manager.setPassword("password");
    }

    @Test
    public void testGetEmployeesByResturantId() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(manager);
        when(resturantRepository.getEmployeesByResturantId(1)).thenReturn(employees);

        ArrayList<Employee> result = resturantService.getEmployeesByResturantId(1);

        assertEquals(2, result.size());
        assertTrue(result.contains(employee));
        assertTrue(result.contains(manager));
        verify(resturantRepository).getEmployeesByResturantId(1);
    }

    @Test
    public void testGetManagersByResturantId() {
        ArrayList<Employee> managers = new ArrayList<>();
        managers.add(manager);
        when(resturantRepository.getManagersByResturantId(1)).thenReturn(managers);

        ArrayList<Employee> result = resturantService.getManagersByResturantId(1);

        assertEquals(1, result.size());
        assertTrue(result.contains(manager));
        verify(resturantRepository).getManagersByResturantId(1);
    }

    @Test
    public void testAddEmployeeToResturant() {
        resturantService.addEmployeeToResturant(1, employee);
        verify(resturantRepository).addEmployeeToResturant(1, employee);
    }

    @Test
    public void testRemoveEmployeeFromResturant() {
        resturantService.removeEmployeeFromResturant(1, 1);
        verify(resturantRepository).removeEmployeeFromResturant(1, 1);
    }

    @Test
    public void testAddManagerToResturant() {
        resturantService.addManagerToResturant(1, manager);
        verify(resturantRepository).addManagerToResturant(1, manager);
    }

    @Test 
    public void testRemoveManagerFromResturant() {
        resturantService.removeManagerFromResturant(1, 2);
        verify(resturantRepository).removeManagerFromResturant(1, 2);
    }

    @Test
    public void testGetResturantByEmployeeId() {
        when(resturantRepository.getResturantByEmployeeId(1)).thenReturn(resturant);
        ResturantResponse result = resturantService.getResturantByEmployeeId(1);
        assertEquals(resturant.getName(), result.getName());
        assertEquals(resturant.getResturantId(), result.getResturantId());
        assertEquals(resturant.getJoinCode(), result.getJoinCode());
    }

    @Test
    public void testCreateResturant() {
        when(resturantRepository.createResturant("New Resturant", 2, "joinCode")).thenReturn(1);
        when(employeeService.getEmployeeById(2)).thenReturn(manager);

        resturantService.createResturant("New Resturant", 2, "joinCode");

        verify(resturantRepository).createResturant("New Resturant", 2, "joinCode");
        verify(employeeService).getEmployeeById(2);
        verify(resturantRepository).addManagerToResturant(1, manager);
    }

    @Test
    public void testGetResturantById() {
        when(resturantRepository.getResturantById(1)).thenReturn(resturant);
        ResturantResponse result = resturantService.getResturantById(1);
        assertEquals(resturant.getName(), result.getName());
        assertEquals(resturant.getResturantId(), result.getResturantId());
        assertEquals(resturant.getJoinCode(), result.getJoinCode());
        verify(resturantRepository).getResturantById(1);
    }

    @Test
    public void testJoinResturant() {
        when(resturantRepository.getResturantIdByJoinCode("joinCode")).thenReturn(1);
        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        resturantService.joinResturant("joinCode", 1);

        verify(resturantRepository).getResturantIdByJoinCode("joinCode");
        verify(employeeService).getEmployeeById(1);
        verify(resturantRepository).addEmployeeToResturant(1, employee);
    }
}
