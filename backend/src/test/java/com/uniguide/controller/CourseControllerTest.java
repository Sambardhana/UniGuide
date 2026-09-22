package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.CourseService;
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
class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private CourseResponse sampleCourse;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleCourse = CourseResponse.builder()
                .id(101L)
                .code("CS201")
                .title("Data Structures & Algorithms")
                .description("Core computer science curriculum course")
                .credits(4)
                .semester(3)
                .departmentId(1L)
                .departmentName("Computer Science & Engineering")
                .build();
    }

    @Test
    @DisplayName("GET /api/courses returns all courses")
    void getCourses_ReturnsAll() throws Exception {
        when(courseService.getAllCourses()).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(101)))
                .andExpect(jsonPath("$[0].code", is("CS201")))
                .andExpect(jsonPath("$[0].title", is("Data Structures & Algorithms")))
                .andExpect(jsonPath("$[0].credits", is(4)));

        verify(courseService).getAllCourses();
    }

    @Test
    @DisplayName("GET /api/courses?departmentId=1&semester=3 returns department & semester filtered courses")
    void getCourses_DepartmentAndSemesterFilter() throws Exception {
        when(courseService.getCoursesByDepartmentAndSemester(1L, 3)).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/courses")
                        .param("departmentId", "1")
                        .param("semester", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].departmentId", is(1)))
                .andExpect(jsonPath("$[0].semester", is(3)));

        verify(courseService).getCoursesByDepartmentAndSemester(1L, 3);
    }

    @Test
    @DisplayName("GET /api/courses?search=Data returns search filtered courses")
    void getCourses_SearchFilter() throws Exception {
        when(courseService.searchCourses("Data")).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/courses")
                        .param("search", "Data")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CS201")));

        verify(courseService).searchCourses("Data");
    }

    @Test
    @DisplayName("GET /api/courses/{id} returns course when found")
    void getCourseById_Success() throws Exception {
        when(courseService.getCourseById(101L)).thenReturn(sampleCourse);

        mockMvc.perform(get("/api/courses/101")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.code", is("CS201")))
                .andExpect(jsonPath("$.title", is("Data Structures & Algorithms")));

        verify(courseService).getCourseById(101L);
    }

    @Test
    @DisplayName("GET /api/courses/{id} returns 404 when not found")
    void getCourseById_NotFound() throws Exception {
        when(courseService.getCourseById(999L))
                .thenThrow(new ResourceNotFoundException("Course", "id", 999L));

        mockMvc.perform(get("/api/courses/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.message", is("Course not found with id: '999'")));

        verify(courseService).getCourseById(999L);
    }
}
