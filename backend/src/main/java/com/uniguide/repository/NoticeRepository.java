package com.uniguide.repository;

import com.uniguide.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Notice} entity persistence operations.
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByIsPinnedTrueOrderByPublishedAtDesc();

    List<Notice> findAllByOrderByPublishedAtDesc();

    List<Notice> findByCategoryIgnoreCaseOrderByPublishedAtDesc(String category);

    List<Notice> findByDepartmentIdOrderByPublishedAtDesc(Long departmentId);

    List<Notice> findByDepartmentIdIsNullOrderByPublishedAtDesc();
}
