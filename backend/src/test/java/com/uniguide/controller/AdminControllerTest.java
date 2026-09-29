package com.uniguide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.uniguide.controller.admin.AdminActivityController;
import com.uniguide.controller.admin.AdminCampusLocationController;
import com.uniguide.controller.admin.AdminContactController;
import com.uniguide.controller.admin.AdminCourseController;
import com.uniguide.controller.admin.AdminDepartmentController;
import com.uniguide.controller.admin.AdminEventController;
import com.uniguide.controller.admin.AdminFacilityController;
import com.uniguide.controller.admin.AdminHostelController;
import com.uniguide.controller.admin.AdminNoticeController;
import com.uniguide.dto.ActivityRequest;
import com.uniguide.dto.ActivityResponse;
import com.uniguide.dto.CampusLocationRequest;
import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.dto.ContactRequest;
import com.uniguide.dto.ContactResponse;
import com.uniguide.dto.CourseRequest;
import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentRequest;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.EventRequest;
import com.uniguide.dto.EventResponse;
import com.uniguide.dto.FacilityRequest;
import com.uniguide.dto.FacilityResponse;
import com.uniguide.dto.HostelRequest;
import com.uniguide.dto.HostelResponse;
import com.uniguide.dto.NoticeRequest;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.exception.GlobalExceptionHandler;
import com.uniguide.service.ActivityService;
import com.uniguide.service.CampusLocationService;
import com.uniguide.service.ContactService;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.EventService;
import com.uniguide.service.FacilityService;
import com.uniguide.service.HostelService;
import com.uniguide.service.NoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    private ObjectMapper objectMapper;

    @Mock private DepartmentService departmentService;
    @Mock private CourseService courseService;
    @Mock private HostelService hostelService;
    @Mock private FacilityService facilityService;
    @Mock private ActivityService activityService;
    @Mock private EventService eventService;
    @Mock private NoticeService noticeService;
    @Mock private ContactService contactService;
    @Mock private CampusLocationService campusLocationService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    // ==========================================
    // 1. Department CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Department CRUD operations")
    void testDepartmentCrud() throws Exception {
        AdminDepartmentController controller = new AdminDepartmentController(departmentService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        DepartmentResponse resp = DepartmentResponse.builder()
                .id(1L).name("Computer Science").code("CSE").build();
        DepartmentRequest req = DepartmentRequest.builder()
                .name("Computer Science").code("CSE").build();

        when(departmentService.getAllDepartments()).thenReturn(List.of(resp));
        when(departmentService.getDepartmentById(1L)).thenReturn(resp);
        when(departmentService.createDepartment(any(DepartmentRequest.class))).thenReturn(resp);
        when(departmentService.updateDepartment(eq(1L), any(DepartmentRequest.class))).thenReturn(resp);
        doNothing().when(departmentService).deleteDepartment(1L);

        // GET all
        mockMvc.perform(get("/api/admin/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].code", is("CSE")));

        // GET by ID
        mockMvc.perform(get("/api/admin/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        // POST (201 Created)
        mockMvc.perform(post("/api/admin/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));

        // PUT (200 OK)
        mockMvc.perform(put("/api/admin/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        // DELETE (204 No Content)
        mockMvc.perform(delete("/api/admin/departments/1"))
                .andExpect(status().isNoContent());

        verify(departmentService).deleteDepartment(1L);
    }

    // ==========================================
    // 2. Course CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Course CRUD operations")
    void testCourseCrud() throws Exception {
        AdminCourseController controller = new AdminCourseController(courseService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        CourseResponse resp = CourseResponse.builder().id(10L).code("CS101").title("Algorithms").build();
        CourseRequest req = CourseRequest.builder().code("CS101").title("Algorithms").credits(4).departmentId(1L).build();

        when(courseService.getAllCourses()).thenReturn(List.of(resp));
        when(courseService.getCourseById(10L)).thenReturn(resp);
        when(courseService.createCourse(any(CourseRequest.class))).thenReturn(resp);
        when(courseService.updateCourse(eq(10L), any(CourseRequest.class))).thenReturn(resp);
        doNothing().when(courseService).deleteCourse(10L);

        mockMvc.perform(get("/api/admin/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(get("/api/admin/courses/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("CS101")));

        mockMvc.perform(post("/api/admin/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(10)));

        mockMvc.perform(put("/api/admin/courses/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/admin/courses/10"))
                .andExpect(status().isNoContent());

        verify(courseService).deleteCourse(10L);
    }

    // ==========================================
    // 3. Hostel CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Hostel CRUD operations")
    void testHostelCrud() throws Exception {
        AdminHostelController controller = new AdminHostelController(hostelService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        HostelResponse resp = HostelResponse.builder().id(20L).name("Gargi Hall").build();
        HostelRequest req = HostelRequest.builder().name("Gargi Hall").type("FEMALE").capacity(200).build();

        when(hostelService.getAllHostels()).thenReturn(List.of(resp));
        when(hostelService.createHostel(any(HostelRequest.class))).thenReturn(resp);
        doNothing().when(hostelService).deleteHostel(20L);

        mockMvc.perform(get("/api/admin/hostels")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/hostels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/hostels/20")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 4. Facility CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Facility CRUD operations")
    void testFacilityCrud() throws Exception {
        AdminFacilityController controller = new AdminFacilityController(facilityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        FacilityResponse resp = FacilityResponse.builder().id(30L).name("Central Library").build();
        FacilityRequest req = FacilityRequest.builder().name("Central Library").type("LIBRARY").build();

        when(facilityService.getAllFacilities()).thenReturn(List.of(resp));
        when(facilityService.createFacility(any(FacilityRequest.class))).thenReturn(resp);
        doNothing().when(facilityService).deleteFacility(30L);

        mockMvc.perform(get("/api/admin/facilities")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/facilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/facilities/30")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 5. Activity CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Activity CRUD operations")
    void testActivityCrud() throws Exception {
        AdminActivityController controller = new AdminActivityController(activityService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        ActivityResponse resp = ActivityResponse.builder().id(40L).name("Robotics Club").build();
        ActivityRequest req = ActivityRequest.builder().name("Robotics Club").category("TECHNICAL").build();

        when(activityService.getAllActivities()).thenReturn(List.of(resp));
        when(activityService.createActivity(any(ActivityRequest.class))).thenReturn(resp);
        doNothing().when(activityService).deleteActivity(40L);

        mockMvc.perform(get("/api/admin/activities")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/activities/40")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 6. Event CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Event CRUD operations")
    void testEventCrud() throws Exception {
        AdminEventController controller = new AdminEventController(eventService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(2);
        EventResponse resp = EventResponse.builder().id(50L).title("Tech Fest").build();
        EventRequest req = EventRequest.builder().title("Tech Fest").startDate(start).endDate(end).build();

        when(eventService.getAllEvents()).thenReturn(List.of(resp));
        when(eventService.createEvent(any(EventRequest.class))).thenReturn(resp);
        doNothing().when(eventService).deleteEvent(50L);

        mockMvc.perform(get("/api/admin/events")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/events/50")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 7. Notice CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Notice CRUD operations")
    void testNoticeCrud() throws Exception {
        AdminNoticeController controller = new AdminNoticeController(noticeService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        NoticeResponse resp = NoticeResponse.builder().id(60L).title("Campus Holiday").build();
        NoticeRequest req = NoticeRequest.builder().title("Campus Holiday").content("Campus will be closed tomorrow.").build();

        when(noticeService.getAllNotices()).thenReturn(List.of(resp));
        when(noticeService.createNotice(any(NoticeRequest.class))).thenReturn(resp);
        doNothing().when(noticeService).deleteNotice(60L);

        mockMvc.perform(get("/api/admin/notices")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/notices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/notices/60")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 8. Contact CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Contact CRUD operations")
    void testContactCrud() throws Exception {
        AdminContactController controller = new AdminContactController(contactService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        ContactResponse resp = ContactResponse.builder().id(70L).name("Campus Security").phoneNumber("+1-555-9111").build();
        ContactRequest req = ContactRequest.builder().name("Campus Security").phoneNumber("+1-555-9111").build();

        when(contactService.getAllContacts()).thenReturn(List.of(resp));
        when(contactService.createContact(any(ContactRequest.class))).thenReturn(resp);
        doNothing().when(contactService).deleteContact(70L);

        mockMvc.perform(get("/api/admin/contacts")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/contacts/70")).andExpect(status().isNoContent());
    }

    // ==========================================
    // 9. Campus Location CRUD
    // ==========================================
    @Test
    @DisplayName("Admin Campus Location CRUD operations")
    void testCampusLocationCrud() throws Exception {
        AdminCampusLocationController controller = new AdminCampusLocationController(campusLocationService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        CampusLocationResponse resp = CampusLocationResponse.builder().id(80L).name("Main Auditorium").build();
        CampusLocationRequest req = CampusLocationRequest.builder().name("Main Auditorium").build();

        when(campusLocationService.getAllLocations()).thenReturn(List.of(resp));
        when(campusLocationService.createLocation(any(CampusLocationRequest.class))).thenReturn(resp);
        doNothing().when(campusLocationService).deleteLocation(80L);

        mockMvc.perform(get("/api/admin/campus-locations")).andExpect(status().isOk());
        mockMvc.perform(post("/api/admin/campus-locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/admin/campus-locations/80")).andExpect(status().isNoContent());
    }
}
