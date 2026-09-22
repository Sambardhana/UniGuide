package com.uniguide.repository;

import com.uniguide.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Department} entity persistence operations.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByCode(String code);

    boolean existsByCode(String code);

    List<Department> findByLocationId(Long locationId);

    List<Department> findByNameContainingIgnoreCase(String keyword);
}
