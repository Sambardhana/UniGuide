package com.uniguide.controller;

import com.uniguide.dto.CourseResponse;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.service.CourseService;
import com.uniguide.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for academic departments.
 */
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final CourseService courseService;

    /**
     * Retrieves all academic departments with optional filtering.
     *
     * @param search     optional text filter on department name
     * @param locationId optional campus location ID filter
     * @param code       optional unique department code filter
     * @return list of department response DTOs
     */
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) String code) {

        if (code != null && !code.isBlank()) {
            return ResponseEntity.ok(List.of(departmentService.getDepartmentByCode(code.trim())));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(departmentService.searchDepartments(search.trim()));
        }
        if (locationId != null) {
            return ResponseEntity.ok(departmentService.getDepartmentsByLocation(locationId));
        }
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    /**
     * Retrieves a single academic department by its identifier.
     *
     * @param id the primary key identifier of the department
     * @return department response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    /**
     * Retrieves all courses offered by a specific department.
     *
     * @param id the primary key identifier of the department
     * @return list of course response DTOs
     */
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponse>> getCoursesByDepartment(@PathVariable Long id) {
        departmentService.getDepartmentById(id);
        return ResponseEntity.ok(courseService.getCoursesByDepartment(id));
    }
}
