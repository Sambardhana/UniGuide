package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.dto.NoticeRequest;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.dto.UserResponse;
import com.uniguide.entity.Role;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import com.uniguide.service.NoticeService;
import com.uniguide.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * REST controller managing teacher-facing endpoints including profile,
 * associated department, class courses/schedule, notices, and departmental students.
 */
@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final UserService userService;
    private final DepartmentService departmentService;
    private final CourseService courseService;
    private final NoticeService noticeService;

    /**
     * Retrieves the profile of the currently authenticated teacher.
     *
     * @param principal authenticated security principal
     * @return teacher user response DTO
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getTeacherProfile(Principal principal) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }

    /**
     * Retrieves the department associated with the authenticated teacher.
     *
     * @param principal authenticated security principal
     * @return department response DTO
     */
    @GetMapping("/department")
    public ResponseEntity<DepartmentResponse> getTeacherDepartment(Principal principal) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        if (user.getDepartmentId() == null) {
            throw new ResourceNotFoundException("Department", "teacherEmail", principal.getName());
        }
        DepartmentResponse department = departmentService.getDepartmentById(user.getDepartmentId());
        return ResponseEntity.ok(department);
    }

    /**
     * Retrieves courses taught or offered within the teacher's department.
     *
     * @param principal authenticated security principal
     * @param semester optional semester filter
     * @return list of course response DTOs
     */
    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getTeacherCourses(
            Principal principal,
            @RequestParam(required = false) Integer semester) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        if (user.getDepartmentId() == null) {
            return ResponseEntity.ok(List.of());
        }
        if (semester != null) {
            return ResponseEntity.ok(courseService.getCoursesByDepartmentAndSemester(user.getDepartmentId(), semester));
        }
        return ResponseEntity.ok(courseService.getCoursesByDepartment(user.getDepartmentId()));
    }

    /**
     * Retrieves class and curriculum schedule for the teacher's department.
     *
     * @param principal authenticated security principal
     * @param semester optional semester filter
     * @return list of course response DTOs representing class offerings
     */
    @GetMapping("/schedule")
    public ResponseEntity<List<CourseResponse>> getTeacherSchedule(
            Principal principal,
            @RequestParam(required = false) Integer semester) {
        return getTeacherCourses(principal, semester);
    }

    /**
     * Retrieves departmental circulars and notices for the teacher's department.
     *
     * @param principal authenticated security principal
     * @return list of notice response DTOs
     */
    @GetMapping("/notices")
    public ResponseEntity<List<NoticeResponse>> getTeacherNotices(Principal principal) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        if (user.getDepartmentId() == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(noticeService.getNoticesByDepartment(user.getDepartmentId()));
    }

    /**
     * Allows an authenticated teacher to publish a departmental or academic notice.
     *
     * @param principal authenticated security principal
     * @param request notice creation request payload
     * @return created notice response DTO
     */
    @PostMapping("/notices")
    public ResponseEntity<NoticeResponse> createTeacherNotice(
            Principal principal,
            @Valid @RequestBody NoticeRequest request) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        if (request.getAuthorId() == null) {
            request.setAuthorId(user.getId());
        }
        if (request.getDepartmentId() == null && user.getDepartmentId() != null) {
            request.setDepartmentId(user.getDepartmentId());
        }
        NoticeResponse created = noticeService.createNotice(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Retrieves students enrolled within the teacher's department.
     *
     * @param principal authenticated security principal
     * @return list of student user response DTOs
     */
    @GetMapping("/students")
    public ResponseEntity<List<UserResponse>> getDepartmentStudents(Principal principal) {
        UserResponse user = userService.getUserByEmail(principal.getName());
        if (user.getDepartmentId() == null) {
            return ResponseEntity.ok(List.of());
        }
        List<UserResponse> departmentUsers = userService.getUsersByDepartment(user.getDepartmentId());
        List<UserResponse> students = departmentUsers.stream()
                .filter(u -> u.getRole() == Role.STUDENT)
                .toList();
        return ResponseEntity.ok(students);
    }
}
