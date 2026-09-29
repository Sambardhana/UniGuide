package com.uniguide.repository;

import com.uniguide.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Contact} entity persistence operations.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByCategoryIgnoreCase(String category);

    List<Contact> findByDepartmentId(Long departmentId);

    List<Contact> findByNameContainingIgnoreCase(String keyword);
}
