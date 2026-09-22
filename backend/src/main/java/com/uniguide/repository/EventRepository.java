package com.uniguide.repository;

import com.uniguide.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for {@link Event} entity persistence operations.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStartDateAfterOrderByStartDateAsc(LocalDateTime now);

    List<Event> findByStartDateBetweenOrderByStartDateAsc(LocalDateTime start, LocalDateTime end);

    List<Event> findByLocationId(Long locationId);

    List<Event> findAllByOrderByStartDateAsc();
}
