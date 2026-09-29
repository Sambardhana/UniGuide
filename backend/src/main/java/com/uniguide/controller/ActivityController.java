package com.uniguide.controller;

import com.uniguide.dto.ActivityResponse;
import com.uniguide.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for extracurricular activities, clubs, and sports.
 */
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    /**
     * Retrieves activities with optional filtering by category, location, or search keyword.
     *
     * @param category   optional activity category (e.g. CLUBS, SPORTS, CULTURAL, TECHNICAL)
     * @param locationId optional campus location ID filter
     * @param search     optional text search on activity name
     * @return list of activity response DTOs
     */
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getActivities(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) String search) {

        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(activityService.getActivitiesByCategory(category.trim()));
        }
        if (locationId != null) {
            return ResponseEntity.ok(activityService.getActivitiesByLocation(locationId));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(activityService.searchActivities(search.trim()));
        }
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    /**
     * Retrieves a single activity by its identifier.
     *
     * @param id the primary key identifier of the activity
     * @return activity response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }

    /**
     * Retrieves activities by category.
     *
     * @param category the category of activities (e.g., CLUBS, SPORTS, CULTURAL)
     * @return list of activity response DTOs matching the category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ActivityResponse>> getActivitiesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(activityService.getActivitiesByCategory(category.trim()));
    }
}
