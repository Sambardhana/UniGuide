package com.uniguide.repository;

import com.uniguide.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Facility} entity persistence operations.
 */
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    List<Facility> findByTypeIgnoreCase(String type);

    List<Facility> findByLocationId(Long locationId);

    List<Facility> findByNameContainingIgnoreCase(String keyword);
}
