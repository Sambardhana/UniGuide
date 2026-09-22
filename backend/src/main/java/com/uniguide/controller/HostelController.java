package com.uniguide.controller;

import com.uniguide.dto.HostelResponse;
import com.uniguide.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for residential hostels and housing.
 */
@RestController
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
public class HostelController {

    private final HostelService hostelService;

    /**
     * Retrieves all hostels with optional filtering by type or location.
     *
     * @param type       optional hostel type (e.g. BOYS, GIRLS, COED)
     * @param locationId optional campus location ID
     * @return list of hostel response DTOs
     */
    @GetMapping
    public ResponseEntity<List<HostelResponse>> getHostels(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long locationId) {

        if (type != null && !type.isBlank()) {
            return ResponseEntity.ok(hostelService.getHostelsByType(type.trim()));
        }
        if (locationId != null) {
            return ResponseEntity.ok(hostelService.getHostelsByLocation(locationId));
        }
        return ResponseEntity.ok(hostelService.getAllHostels());
    }

    /**
     * Retrieves a single hostel by its identifier.
     *
     * @param id the primary key identifier of the hostel
     * @return hostel response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<HostelResponse> getHostelById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getHostelById(id));
    }
}
