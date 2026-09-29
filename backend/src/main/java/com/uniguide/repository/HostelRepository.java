package com.uniguide.repository;

import com.uniguide.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Hostel} entity persistence operations.
 */
@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

    List<Hostel> findByTypeIgnoreCase(String type);

    List<Hostel> findByLocationId(Long locationId);

    Optional<Hostel> findByNameIgnoreCase(String name);
}
