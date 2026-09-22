package com.uniguide.controller.admin;

import com.uniguide.dto.HostelRequest;
import com.uniguide.dto.HostelResponse;
import com.uniguide.service.HostelService;
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
 * Administrative controller providing full CRUD management for university hostels.
 */
@RestController
@RequestMapping("/api/admin/hostels")
@RequiredArgsConstructor
public class AdminHostelController {

    private final HostelService hostelService;

    @GetMapping
    public ResponseEntity<List<HostelResponse>> getAllHostels() {
        return ResponseEntity.ok(hostelService.getAllHostels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostelResponse> getHostelById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getHostelById(id));
    }

    @PostMapping
    public ResponseEntity<HostelResponse> createHostel(@Valid @RequestBody HostelRequest request) {
        HostelResponse created = hostelService.createHostel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostelResponse> updateHostel(
            @PathVariable Long id,
            @Valid @RequestBody HostelRequest request) {
        HostelResponse updated = hostelService.updateHostel(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHostel(@PathVariable Long id) {
        hostelService.deleteHostel(id);
        return ResponseEntity.noContent().build();
    }
}
