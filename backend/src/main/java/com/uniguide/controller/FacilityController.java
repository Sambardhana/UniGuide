package com.uniguide.controller;

import com.uniguide.dto.FacilityResponse;
import com.uniguide.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for campus facilities and amenities.
 */
@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    /**
     * Retrieves campus facilities with optional filtering by type, location, or search keyword.
     *
     * @param type       optional facility type (e.g. LIBRARY, CAFETERIA, SPORTS, HEALTH)
     * @param locationId optional campus location ID
     * @param search     optional text search query on facility name
     * @return list of facility response DTOs
     */
    @GetMapping
    public ResponseEntity<List<FacilityResponse>> getFacilities(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) String search) {

        if (type != null && !type.isBlank()) {
            return ResponseEntity.ok(facilityService.getFacilitiesByType(type.trim()));
        }
        if (locationId != null) {
            return ResponseEntity.ok(facilityService.getFacilitiesByLocation(locationId));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(facilityService.searchFacilities(search.trim()));
        }
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    /**
     * Retrieves a single campus facility by its identifier.
     *
     * @param id the primary key identifier of the facility
     * @return facility response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<FacilityResponse> getFacilityById(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.getFacilityById(id));
    }
}
