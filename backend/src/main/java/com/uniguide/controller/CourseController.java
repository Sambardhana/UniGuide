package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for courses and curriculum.
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    /**
     * Retrieves courses with optional filtering by department, semester, code, or keyword.
     *
     * @param departmentId optional department ID filter
     * @param semester     optional semester filter
     * @param code         optional unique course code filter
     * @param search       optional text search filter on title
     * @return list of course response DTOs
     */
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getCourses(
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
     * Retrieves a single course by its identifier.
     *
     * @param id the primary key identifier of the course
     * @return course response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
}
