package com.uniguide.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Test-scoped REST controller providing endpoints mapped to /api/teacher/** and /api/admin/**
 * to verify role-based security filter chain rules without modifying production business logic.
 */
@RestController
public class TestProtectedControllers {

    @GetMapping("/api/teacher/dashboard")
    public ResponseEntity<Map<String, String>> teacherDashboard() {
        return ResponseEntity.ok(Map.of("status", "ok", "message", "Teacher Dashboard Access Granted"));
    }

    @GetMapping("/api/admin/dashboard")
    public ResponseEntity<Map<String, String>> adminDashboard() {
        return ResponseEntity.ok(Map.of("status", "ok", "message", "Admin Dashboard Access Granted"));
    }
}
