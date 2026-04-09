package com.RESTurantManager.demo.controller;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.RESTurantManager.demo.db.requests.DeviationRequest;
import com.RESTurantManager.demo.model.Deviation;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.DeviationService;
import com.RESTurantManager.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = DeviationController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class DeviationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeviationService deviationService;

    @MockitoBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("createDeviation should return created deviation response")
    void createDeviationTest() throws Exception {
        DeviationRequest request = new DeviationRequest();
        request.setTitle("For høy temperatur");
        request.setDescription("Kjøleskapet var på 10 grader");
        request.setRegisteredBy(1);
        request.setDate(new Date());

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Ola");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(post("/deviation/createDeviation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("For høy temperatur"))
                .andExpect(jsonPath("$.description").value("Kjøleskapet var på 10 grader"))
                .andExpect(jsonPath("$.registeredBy").value(1));

        verify(employeeService).getEmployeeById(1);
        verify(deviationService).createDeviation(any(Deviation.class));
    }

    @Test
    @DisplayName("deleteDeviation should return 200")
    void deleteDeviationTest() throws Exception {
        mockMvc.perform(delete("/deviation/deleteDeviation/1"))
                .andExpect(status().isOk());

        verify(deviationService).deleteDeviationById(1);
    }

    @Test
    @DisplayName("getDeviation should return deviation response")
    void getDeviationTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Deviation deviation = new Deviation();
        deviation.setDeviationId(1);
        deviation.setName("For høy temperatur");
        deviation.setDescription("Kjøleskapet var på 10 grader");
        deviation.setRegisteredBy(employee);
        deviation.setDateRegistered(new Date());

        when(deviationService.getDeviationById(1)).thenReturn(deviation);

        mockMvc.perform(get("/deviation/getDeviation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviationId").value(1))
                .andExpect(jsonPath("$.title").value("For høy temperatur"))
                .andExpect(jsonPath("$.description").value("Kjøleskapet var på 10 grader"))
                .andExpect(jsonPath("$.registeredBy").value(1));

        verify(deviationService).getDeviationById(1);
    }

    @Test
    @DisplayName("getDeviationsByEmployeeId should return list of deviations")
    void getDeviationsByEmployeeIdTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);

        Deviation deviation = new Deviation();
        deviation.setDeviationId(1);
        deviation.setName("For høy temperatur");
        deviation.setDescription("Kjøleskapet var på 10 grader");
        deviation.setRegisteredBy(employee);
        deviation.setDateRegistered(new Date());

        when(deviationService.getDeviationsByEmployeeId(1)).thenReturn(new Deviation[] { deviation });

        mockMvc.perform(get("/deviation/getDeviationByEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviationId").value(1))
                .andExpect(jsonPath("$[0].title").value("For høy temperatur"))
                .andExpect(jsonPath("$[0].registeredBy").value(1));

        verify(deviationService).getDeviationsByEmployeeId(1);
    }

    @Test
    @DisplayName("getDeviationsByResturantId should return combined list of deviations")
    void getDeviationsByResturantIdTest() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);

        Deviation deviation1 = new Deviation();
        deviation1.setDeviationId(1);
        deviation1.setName("Avvik 1");
        deviation1.setDescription("Beskrivelse 1");
        deviation1.setRegisteredBy(employee1);
        deviation1.setDateRegistered(new Date());

        Deviation deviation2 = new Deviation();
        deviation2.setDeviationId(2);
        deviation2.setName("Avvik 2");
        deviation2.setDescription("Beskrivelse 2");
        deviation2.setRegisteredBy(employee2);
        deviation2.setDateRegistered(new Date());

        when(employeeService.getEmployeesByResturantId(1)).thenReturn(new Employee[] { employee1, employee2 });
        when(deviationService.getDeviationsByEmployeeId(1)).thenReturn(new Deviation[] { deviation1 });
        when(deviationService.getDeviationsByEmployeeId(2)).thenReturn(new Deviation[] { deviation2 });

        mockMvc.perform(get("/deviation/getDeviationByResturant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].deviationId").value(1))
                .andExpect(jsonPath("$[1].deviationId").value(2));

        verify(employeeService).getEmployeesByResturantId(1);
        verify(deviationService).getDeviationsByEmployeeId(1);
        verify(deviationService).getDeviationsByEmployeeId(2);
    }

    @Test
    @DisplayName("updateDeviation should return updated deviation response")
    void updateDeviationTest() throws Exception {
        DeviationRequest request = new DeviationRequest();
        request.setTitle("Oppdatert avvik");
        request.setDescription("Oppdatert beskrivelse");
        request.setRegisteredBy(1);
        request.setDate(new Date());

        Employee employee = new Employee();
        employee.setEmployeeId(1);

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(post("/deviation/updateDeviation/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviationId").value(1))
                .andExpect(jsonPath("$.title").value("Oppdatert avvik"))
                .andExpect(jsonPath("$.registeredBy").value(1));

        verify(employeeService).getEmployeeById(1);
        verify(deviationService).updateDeviation(any(Deviation.class));
    }
}