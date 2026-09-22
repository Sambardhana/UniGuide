package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.EventResponse;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.EventService;
import com.uniguide.service.NoticeService;
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

import java.time.LocalDateTime;
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
class AcademicControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CourseService courseService;

    @Mock
    private NoticeService noticeService;

    @Mock
    private EventService eventService;

    @InjectMocks
    private AcademicController academicController;

    private DepartmentResponse sampleDepartment;
    private CourseResponse sampleCourse;
    private EventResponse sampleCalendarEvent;
    private NoticeResponse sampleExamNotice;
    private NoticeResponse sampleAcademicNotice;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(academicController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        sampleDepartment = DepartmentResponse.builder()
                .id(1L)
                .name("Computer Science & Engineering")
                .code("CSE")
                .description("Department of Computer Science")
                .contactEmail("cse@university.edu")
                .build();

        sampleCourse = CourseResponse.builder()
                .id(101L)
                .code("CSE201")
                .title("Data Structures and Algorithms")
                .description("Fundamental algorithms, trees, graphs, sorting, and complexity.")
                .credits(4)
                .semester(3)
                .departmentId(1L)
                .departmentName("Computer Science & Engineering")
                .build();

        sampleCalendarEvent = EventResponse.builder()
                .id(201L)
                .title("Mid-Semester Examinations")
                .description("University-wide mid semester exam block")
                .startDate(LocalDateTime.of(2026, 10, 15, 9, 0))
                .endDate(LocalDateTime.of(2026, 10, 22, 17, 0))
                .venue("Main Examination Hall")
                .organizer("Dean of Academics")
                .build();

        sampleExamNotice = NoticeResponse.builder()
                .id(301L)
                .title("Fall 2026 End-Term Examination Schedule")
                .content("The official exam timetable has been announced.")
                .category("EXAMINATION")
                .attachmentUrl("https://uniguide.edu/docs/exam-schedule-fall26.pdf")
                .isPinned(true)
                .publishedAt(LocalDateTime.now())
                .build();

        sampleAcademicNotice = NoticeResponse.builder()
                .id(302L)
                .title("Course Registration Deadline for Spring 2027")
                .content("Course registration opens next Monday.")
                .category("ACADEMIC")
                .attachmentUrl("https://uniguide.edu/docs/registration-guide.pdf")
                .isPinned(false)
                .publishedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("GET /api/academic/departments returns academic departments")
    void getAcademicDepartments_ReturnsAll() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(List.of(sampleDepartment));

        mockMvc.perform(get("/api/academic/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Computer Science & Engineering")));

        verify(departmentService).getAllDepartments();
    }

    @Test
    @DisplayName("GET /api/academic/departments?search=Computer returns filtered departments")
    void getAcademicDepartments_WithSearch() throws Exception {
        when(departmentService.searchDepartments("Computer")).thenReturn(List.of(sampleDepartment));

        mockMvc.perform(get("/api/academic/departments")
                        .param("search", "Computer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CSE")));

        verify(departmentService).searchDepartments("Computer");
    }

    @Test
    @DisplayName("GET /api/academic/departments/{id} returns department details")
    void getAcademicDepartmentById_Success() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(sampleDepartment);

        mockMvc.perform(get("/api/academic/departments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.code", is("CSE")));

        verify(departmentService).getDepartmentById(1L);
    }

    @Test
    @DisplayName("GET /api/academic/departments/{id}/courses returns curriculum courses")
    void getDepartmentCourses_Success() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(sampleDepartment);
        when(courseService.getCoursesByDepartment(1L)).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/academic/departments/1/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CSE201")))
                .andExpect(jsonPath("$[0].credits", is(4)));

        verify(departmentService).getDepartmentById(1L);
        verify(courseService).getCoursesByDepartment(1L);
    }

    @Test
    @DisplayName("GET /api/academic/departments/{id}/courses returns 404 when department not found")
    void getDepartmentCourses_NotFound() throws Exception {
        when(departmentService.getDepartmentById(99L))
                .thenThrow(new ResourceNotFoundException("Department", "id", 99L));

        mockMvc.perform(get("/api/academic/departments/99/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Department not found with id: '99'")));

        verify(departmentService).getDepartmentById(99L);
    }

    @Test
    @DisplayName("GET /api/academic/courses returns all courses")
    void getAcademicCourses_ReturnsAll() throws Exception {
        when(courseService.getAllCourses()).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/academic/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Data Structures and Algorithms")));

        verify(courseService).getAllCourses();
    }

    @Test
    @DisplayName("GET /api/academic/courses with department and semester filters")
    void getAcademicCourses_Filtered() throws Exception {
        when(courseService.getCoursesByDepartmentAndSemester(1L, 3)).thenReturn(List.of(sampleCourse));

        mockMvc.perform(get("/api/academic/courses")
                        .param("departmentId", "1")
                        .param("semester", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CSE201")));

        verify(courseService).getCoursesByDepartmentAndSemester(1L, 3);
    }

    @Test
    @DisplayName("GET /api/academic/courses/{id} returns course details")
    void getAcademicCourseById_Success() throws Exception {
        when(courseService.getCourseById(101L)).thenReturn(sampleCourse);

        mockMvc.perform(get("/api/academic/courses/101")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.credits", is(4)))
                .andExpect(jsonPath("$.description", is("Fundamental algorithms, trees, graphs, sorting, and complexity.")));

        verify(courseService).getCourseById(101L);
    }

    @Test
    @DisplayName("GET /api/academic/courses/{id} returns 404 when not found")
    void getAcademicCourseById_NotFound() throws Exception {
        when(courseService.getCourseById(999L))
                .thenThrow(new ResourceNotFoundException("Course", "id", 999L));

        mockMvc.perform(get("/api/academic/courses/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")));

        verify(courseService).getCourseById(999L);
    }

    @Test
    @DisplayName("GET /api/academic/calendar returns academic calendar events")
    void getAcademicCalendar_All() throws Exception {
        when(eventService.getAllEvents()).thenReturn(List.of(sampleCalendarEvent));

        mockMvc.perform(get("/api/academic/calendar")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Mid-Semester Examinations")));

        verify(eventService).getAllEvents();
    }

    @Test
    @DisplayName("GET /api/academic/calendar?upcomingOnly=true returns upcoming events")
    void getAcademicCalendar_UpcomingOnly() throws Exception {
        when(eventService.getUpcomingEvents()).thenReturn(List.of(sampleCalendarEvent));

        mockMvc.perform(get("/api/academic/calendar")
                        .param("upcomingOnly", "true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Mid-Semester Examinations")));

        verify(eventService).getUpcomingEvents();
    }

    @Test
    @DisplayName("GET /api/academic/examinations returns examination circulars and notices")
    void getExaminationNotices_Success() throws Exception {
        when(noticeService.getNoticesByCategory("EXAMINATION")).thenReturn(List.of(sampleExamNotice));

        mockMvc.perform(get("/api/academic/examinations")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("EXAMINATION")))
                .andExpect(jsonPath("$[0].attachmentUrl", is("https://uniguide.edu/docs/exam-schedule-fall26.pdf")));

        verify(noticeService).getNoticesByCategory("EXAMINATION");
    }

    @Test
    @DisplayName("GET /api/academic/notices returns academic circulars and notices")
    void getAcademicNotices_Success() throws Exception {
        when(noticeService.getNoticesByCategory("ACADEMIC")).thenReturn(List.of(sampleAcademicNotice));

        mockMvc.perform(get("/api/academic/notices")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].category", is("ACADEMIC")))
                .andExpect(jsonPath("$[0].attachmentUrl", is("https://uniguide.edu/docs/registration-guide.pdf")));

        verify(noticeService).getNoticesByCategory("ACADEMIC");
    }
}
