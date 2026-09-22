package com.uniguide.controller;

import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.service.CampusLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for campus locations, points of interest, and QR navigation.
 */
@RestController
@RequestMapping("/api/campus-locations")
@RequiredArgsConstructor
public class CampusLocationController {

    private final CampusLocationService campusLocationService;

    /**
     * Retrieves campus locations with optional filtering by category, search keyword, code, or QR key.
     *
     * @param category  optional location category (e.g. ACADEMIC, RESIDENTIAL, AMENITY)
     * @param search    optional search keyword for location name
     * @param code      optional location code
     * @param qrCodeKey optional QR code navigation key
     * @return list of campus location response DTOs
     */
    @GetMapping
    public ResponseEntity<List<CampusLocationResponse>> getCampusLocations(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String qrCodeKey) {

        if (code != null && !code.isBlank()) {
            return ResponseEntity.ok(List.of(campusLocationService.getLocationByCode(code.trim())));
        }
        if (qrCodeKey != null && !qrCodeKey.isBlank()) {
            return ResponseEntity.ok(List.of(campusLocationService.getLocationByQrCodeKey(qrCodeKey.trim())));
        }
        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(campusLocationService.getLocationsByCategory(category.trim()));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(campusLocationService.searchLocations(search.trim()));
        }
        return ResponseEntity.ok(campusLocationService.getAllLocations());
    }

    /**
     * Retrieves a single campus location by its identifier.
     *
     * @param id the primary key identifier of the campus location
     * @return campus location response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<CampusLocationResponse> getCampusLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(campusLocationService.getLocationById(id));
    }

    /**
     * Retrieves a campus location by its unique QR code key.
     *
     * @param qrCodeKey the QR code identifier string
     * @return campus location response DTO
     */
    @GetMapping("/qr/{qrCodeKey}")
    public ResponseEntity<CampusLocationResponse> getCampusLocationByQrCode(@PathVariable String qrCodeKey) {
        return ResponseEntity.ok(campusLocationService.getLocationByQrCodeKey(qrCodeKey.trim()));
    }
}
