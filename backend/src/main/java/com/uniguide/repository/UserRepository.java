package com.uniguide.repository;

import com.uniguide.entity.Role;
import com.uniguide.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link User} entity persistence operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByStudentId(String studentId);

    List<User> findByRole(Role role);

    List<User> findByDepartmentId(Long departmentId);
}
