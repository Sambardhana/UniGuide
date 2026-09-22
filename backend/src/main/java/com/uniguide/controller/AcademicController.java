package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.EventResponse;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.EventService;
import com.uniguide.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller providing consolidated student-facing endpoints for academic information,
 * program curriculums, courses, academic calendars, examination notices, and circulars.
 */
@RestController
@RequestMapping("/api/academic")
@RequiredArgsConstructor
public class AcademicController {

    private final DepartmentService departmentService;
    private final CourseService courseService;
    private final NoticeService noticeService;
    private final EventService eventService;

    /**
     * Retrieves academic departments/programs with optional name search.
     *
     * @param search optional search keyword
     * @return list of department response DTOs
     */
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponse>> getAcademicDepartments(
            @RequestParam(required = false) String search) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(departmentService.searchDepartments(search.trim()));
        }
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    /**
     * Retrieves details of a specific academic department.
     *
     * @param id the department identifier
     * @return department response DTO
     */
    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentResponse> getAcademicDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    /**
     * Retrieves the curriculum/courses offered by a specific academic department.
     *
     * @param id the department identifier
     * @return list of course response DTOs
     */
    @GetMapping("/departments/{id}/courses")
    public ResponseEntity<List<CourseResponse>> getDepartmentCourses(@PathVariable Long id) {
        // Validate department existence (throws 404 if not found)
        departmentService.getDepartmentById(id);
        return ResponseEntity.ok(courseService.getCoursesByDepartment(id));
    }

    /**
     * Retrieves academic courses with optional filtering by department, semester, code, or keyword.
     *
     * @param departmentId optional department ID filter
     * @param semester     optional semester filter
     * @param code         optional unique course code filter
     * @param search       optional search keyword
     * @return list of course response DTOs
     */
    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getAcademicCourses(
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Integer semester,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String search) {

        if (code != null && !code.isBlank()) {
            return ResponseEntity.ok(List.of(courseService.getCourseByCode(code.trim())));
        }
        if (departmentId != null && semester != null) {
            return ResponseEntity.ok(courseService.getCoursesByDepartmentAndSemester(departmentId, semester));
        }
        if (departmentId != null) {
            return ResponseEntity.ok(courseService.getCoursesByDepartment(departmentId));
        }
        if (semester != null) {
            return ResponseEntity.ok(courseService.getCoursesBySemester(semester));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(courseService.searchCourses(search.trim()));
        }
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    /**
     * Retrieves detailed information for a single course, including description/syllabus metadata,
     * credits, and semester details.
     *
     * @param id the course identifier
     * @return course response DTO
     */
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getAcademicCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    /**
     * Retrieves the academic calendar and key scheduled academic events.
     *
     * @param upcomingOnly whether to only return upcoming events (default: false)
     * @return list of event response DTOs
     */
    @GetMapping("/calendar")
    public ResponseEntity<List<EventResponse>> getAcademicCalendar(
            @RequestParam(required = false, defaultValue = "false") boolean upcomingOnly) {
        if (upcomingOnly) {
            return ResponseEntity.ok(eventService.getUpcomingEvents());
        }
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * Retrieves examination information, schedules, and circulars.
     *
     * @return list of notice response DTOs related to examinations
     */
    @GetMapping("/examinations")
    public ResponseEntity<List<NoticeResponse>> getExaminationNotices() {
        return ResponseEntity.ok(noticeService.getNoticesByCategory("EXAMINATION"));
    }

    /**
     * Retrieves academic announcements, circulars, and notifications.
     *
     * @return list of notice response DTOs categorized as academic
     */
    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponse>> getAcademicNotices() {
        return ResponseEntity.ok(noticeService.getNoticesByCategory("ACADEMIC"));
    }
}
