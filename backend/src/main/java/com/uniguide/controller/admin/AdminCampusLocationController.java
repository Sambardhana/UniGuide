package com.uniguide.controller.admin;

import com.uniguide.dto.CampusLocationRequest;
import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.service.CampusLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Administrative controller providing full CRUD management for campus buildings and geolocations.
 */
@RestController
@RequestMapping("/api/admin/campus-locations")
@RequiredArgsConstructor
public class AdminCampusLocationController {

    private final CampusLocationService campusLocationService;

    @GetMapping
    public ResponseEntity<List<CampusLocationResponse>> getAllLocations() {
        return ResponseEntity.ok(campusLocationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampusLocationResponse> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(campusLocationService.getLocationById(id));
    }

    @PostMapping
    public ResponseEntity<CampusLocationResponse> createLocation(@Valid @RequestBody CampusLocationRequest request) {
        CampusLocationResponse created = campusLocationService.createLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampusLocationResponse> updateLocation(
            @PathVariable Long id,
            @Valid @RequestBody CampusLocationRequest request) {
        CampusLocationResponse updated = campusLocationService.updateLocation(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        campusLocationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
