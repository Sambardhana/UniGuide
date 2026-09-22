package com.uniguide.repository;

import com.uniguide.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Activity} entity persistence operations.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByCategoryIgnoreCase(String category);

    List<Activity> findByLocationId(Long locationId);

    List<Activity> findByNameContainingIgnoreCase(String keyword);
}
