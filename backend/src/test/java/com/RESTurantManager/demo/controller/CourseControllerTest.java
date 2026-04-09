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

import com.RESTurantManager.demo.db.requests.CourseRequest;
import com.RESTurantManager.demo.model.Course;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = CourseController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("createCourse should return 200")
    void createCourseTest() throws Exception {
        CourseRequest request = new CourseRequest();
        request.setName("Brannvernkurs");
        request.setDescription("Grunnleggende brannvern");
        request.setEmployeeId(1);
        request.setCompletionDate(new Date());
        request.setExpirationDate(new Date());
        request.setDocumentation(null);

        mockMvc.perform(post("/course/createCourse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(courseService).createCourse(any(Course.class));
    }

    @Test
    @DisplayName("deleteCourse should return 200")
    void deleteCourseTest() throws Exception {
        mockMvc.perform(delete("/course/deleteCourse/1"))
                .andExpect(status().isOk());

        verify(courseService).deleteCourseById(1);
    }

    @Test
    @DisplayName("getCourse should return course response")
    void getCourseTest() throws Exception {
        Course course = new Course();
        course.setCourseId(1);
        course.setName("Brannvernkurs");
        course.setDescription("Grunnleggende brannvern");
        course.setEmployeeId(1);
        course.setDateCompleted(new Date());
        course.setDateExpires(new Date());

        when(courseService.getCourseById(1)).thenReturn(course);

        mockMvc.perform(get("/course/getCourse/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.name").value("Brannvernkurs"))
                .andExpect(jsonPath("$.description").value("Grunnleggende brannvern"))
                .andExpect(jsonPath("$.employeeId").value(1));

        verify(courseService).getCourseById(1);
    }

    @Test
    @DisplayName("getCoursesByEmployeeId should return list of courses")
    void getCoursesByEmployeeIdTest() throws Exception {
        Course course1 = new Course();
        course1.setCourseId(1);
        course1.setName("Brannvernkurs");
        course1.setDescription("Grunnleggende brannvern");
        course1.setEmployeeId(1);

        Course course2 = new Course();
        course2.setCourseId(2);
        course2.setName("Hygienekurs");
        course2.setDescription("Mattrygghet og hygiene");
        course2.setEmployeeId(1);

        when(courseService.getCoursesByEmployeeId(1)).thenReturn(new Course[] { course1, course2 });

        mockMvc.perform(get("/course/getCoursesByEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].name").value("Brannvernkurs"))
                .andExpect(jsonPath("$[1].courseId").value(2))
                .andExpect(jsonPath("$[1].name").value("Hygienekurs"));

        verify(courseService).getCoursesByEmployeeId(1);
    }

    @Test
    @DisplayName("getCoursesByResturantId should return list of courses")
    void getCoursesByResturantIdTest() throws Exception {
        Course course = new Course();
        course.setCourseId(1);
        course.setName("Brannvernkurs");
        course.setDescription("Grunnleggende brannvern");
        course.setEmployeeId(1);

        when(courseService.getCoursesByResturantId(1)).thenReturn(new Course[] { course });

        mockMvc.perform(get("/course/getCoursesByResturant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].name").value("Brannvernkurs"));

        verify(courseService).getCoursesByResturantId(1);
    }

    @Test
    @DisplayName("updateCourse should return 200")
    void updateCourseTest() throws Exception {
        CourseRequest request = new CourseRequest();
        request.setName("Oppdatert kurs");
        request.setDescription("Oppdatert beskrivelse");
        request.setEmployeeId(1);
        request.setCompletionDate(new Date());
        request.setExpirationDate(new Date());
        request.setDocumentation(null);

        mockMvc.perform(post("/course/updateCourse/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(courseService).updateCourse(any(Course.class));
    }
}