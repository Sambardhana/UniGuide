package com.uniguide.repository;

import com.uniguide.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Course} entity persistence operations.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    boolean existsByCode(String code);

    List<Course> findByDepartmentId(Long departmentId);

    List<Course> findBySemester(Integer semester);

    List<Course> findByDepartmentIdAndSemester(Long departmentId, Integer semester);

    List<Course> findByTitleContainingIgnoreCase(String keyword);
}
