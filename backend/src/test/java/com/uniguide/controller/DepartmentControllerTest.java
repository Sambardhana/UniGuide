package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private DepartmentController departmentController;

    private DepartmentResponse sampleDepartment;
    private CourseResponse sampleCourse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleDepartment = DepartmentResponse.builder()
                .id(1L)
                .name("Computer Science & Engineering")
                .code("CSE")
                .description("Department of Computer Science")
                .contactEmail("cse@university.edu")
                .contactPhone("+1-555-100-2000")
                .locationId(10L)
                .locationName("Academic Block 1")
                .build();

        sampleCourse = CourseResponse.builder()
                .id(101L)
                .code("CSE101")
                .title("Introduction to Programming")
                .description("Basic programming concepts in Java and C")
                .credits(3)
                .semester(1)
                .departmentId(1L)
                .departmentName("Computer Science & Engineering")
                .build();
    }

    @Test
    @DisplayName("GET /api/departments returns all departments")
    void getDepartments_ReturnsAll() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(List.of(sampleDepartment));

        mockMvc.perform(get("/api/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Computer Science & Engineering")))
                .andExpect(jsonPath("$[0].code", is("CSE")));

        verify(departmentService).getAllDepartments();
    }

    @Test
    @DisplayName("GET /api/departments?search=Computer returns filtered departments")
    void getDepartments_SearchFilter() throws Exception {
        when(departmentService.searchDepartments("Computer")).thenReturn(List.of(sampleDepartment));

        mockMvc.perform(get("/api/departments")
                        .param("search", "Computer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CSE")));

        verify(departmentService).searchDepartments("Computer");
    }

    @Test
    @DisplayName("GET /api/departments?locationId=10 returns departments at location")
    void getDepartments_LocationFilter() throws Exception {
        when(departmentService.getDepartmentsByLocation(10L)).thenReturn(List.of(sampleDepartment));

        mockMvc.perform(get("/api/departments")
                        .param("locationId", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].locationId", is(10)));

        verify(departmentService).getDepartmentsByLocation(10L);
    }

    @Test
    @DisplayName("GET /api/departments/{id} returns department when found")
    void getDepartmentById_Success() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(sampleDepartment);

        mockMvc.perform(get("/api/departments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.code", is("CSE")))
                .andExpect(jsonPath("$.name", is("Computer Science & Engineering")));

        verify(departmentService).getDepartmentById(1L);
    }

    @Test
    @DisplayName("GET /api/departments/{id} returns 404 when not found")
    void getDepartmentById_NotFound() throws Exception {
        when(departmentService.getDepartmentById(99L))
                .thenThrow(new ResourceNotFoundException("Department", "id", 99L));

        mockMvc.perform(get("/api/departments/99")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Department not found with id: '99'")));

        verify(departmentService).getDepartmentById(99L);
    }

    @Test
    @DisplayName("GET /api/departments/{id}/courses returns department courses")
    void getCoursesByDepartment_Success() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(sampleDepartment);
        when(courseService.getCoursesByDepartment(1L)).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/departments/1/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(101)))
                .andExpect(jsonPath("$[0].code", is("CSE101")))
                .andExpect(jsonPath("$[0].departmentId", is(1)));

        verify(departmentService).getDepartmentById(1L);
        verify(courseService).getCoursesByDepartment(1L);
    }

    @Test
    @DisplayName("GET /api/departments/{id}/courses returns 404 when department not found")
    void getCoursesByDepartment_DepartmentNotFound() throws Exception {
        when(departmentService.getDepartmentById(99L))
                .thenThrow(new ResourceNotFoundException("Department", "id", 99L));

        mockMvc.perform(get("/api/departments/99/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Department not found with id: '99'")));

        verify(departmentService).getDepartmentById(99L);
    }
}
