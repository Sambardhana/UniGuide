package com.uniguide.controller;

import com.uniguide.dto.ContactResponse;
import com.uniguide.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for emergency helplines, department contacts, and staff directory.
 */
@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    /**
     * Retrieves contacts with optional filtering by category, department ID, or search keyword.
     *
     * @param category     optional contact category (e.g. EMERGENCY, HEALTH, SECURITY, ADMINISTRATIVE)
     * @param departmentId optional department ID filter
     * @param search       optional search keyword on contact name
     * @return list of contact response DTOs
     */
    @GetMapping
    public ResponseEntity<List<ContactResponse>> getContacts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String search) {

        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(contactService.getContactsByCategory(category.trim()));
        }
        if (departmentId != null) {
            return ResponseEntity.ok(contactService.getContactsByDepartment(departmentId));
        }
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(contactService.searchContacts(search.trim()));
        }
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    /**
     * Retrieves a single contact by its identifier.
     *
     * @param id the primary key identifier of the contact
     * @return contact response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    /**
     * Convenience endpoint to retrieve all university emergency and crisis contacts.
     *
     * @return list of emergency contact response DTOs
     */
    @GetMapping("/emergency")
    public ResponseEntity<List<ContactResponse>> getEmergencyContacts() {
        return ResponseEntity.ok(contactService.getContactsByCategory("EMERGENCY"));
    }

    /**
     * Retrieves contacts belonging to a specific category.
     *
     * @param category contact category (e.g. EMERGENCY, HEALTH, SECURITY)
     * @return list of contact response DTOs
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ContactResponse>> getContactsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(contactService.getContactsByCategory(category.trim()));
    }
}
