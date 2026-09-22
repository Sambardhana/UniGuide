package com.uniguide.service;

import com.uniguide.dto.CourseRequest;
import com.uniguide.dto.CourseResponse;
import com.uniguide.entity.Course;
import com.uniguide.entity.Department;
import com.uniguide.exception.DuplicateResourceException;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.CourseMapper;
import com.uniguide.repository.CourseRepository;
import com.uniguide.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing academic curriculum courses and subjects.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseMapper courseMapper;

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return courseMapper.toResponse(course);
    }

    public CourseResponse getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "code", code));
        return courseMapper.toResponse(course);
    }

    public List<CourseResponse> getCoursesByDepartment(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public List<CourseResponse> getCoursesBySemester(Integer semester) {
        return courseRepository.findBySemester(semester).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public List<CourseResponse> getCoursesByDepartmentAndSemester(Long departmentId, Integer semester) {
        return courseRepository.findByDepartmentIdAndSemester(departmentId, semester).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    public List<CourseResponse> searchCourses(String keyword) {
        return courseRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        if (courseRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Course", "code", request.getCode());
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));

        Course course = courseMapper.toEntity(request, department);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toResponse(savedCourse);
    }

    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        if (!request.getCode().equalsIgnoreCase(existing.getCode())) {
            if (courseRepository.existsByCode(request.getCode())) {
                throw new DuplicateResourceException("Course", "code", request.getCode());
            }
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));

        existing.setCode(request.getCode());
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setCredits(request.getCredits());
        existing.setSemester(request.getSemester());
        existing.setDepartment(department);

        Course updated = courseRepository.save(existing);
        return courseMapper.toResponse(updated);
    }

    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course", "id", id);
        }
        courseRepository.deleteById(id);
    }
}
