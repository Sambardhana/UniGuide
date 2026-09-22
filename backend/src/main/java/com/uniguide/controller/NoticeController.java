package com.uniguide.controller;

import com.uniguide.dto.NoticeResponse;
import com.uniguide.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller exposing student-facing read endpoints for university circulars, notices, and announcements.
 */
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * Retrieves university notices with optional filtering by category, department, pinned status, or general scope.
     *
     * @param category     optional category filter (e.g. ACADEMIC, EXAMINATION, HOSTEL, GENERAL)
     * @param departmentId optional department ID filter
     * @param pinned       optional flag to return only pinned notices
     * @param general      optional flag to return only general (non-departmental) notices
     * @return list of notice response DTOs
     */
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> getNotices(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean pinned,
            @RequestParam(required = false) Boolean general) {

        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(noticeService.getNoticesByCategory(category.trim()));
        }
        if (departmentId != null) {
            return ResponseEntity.ok(noticeService.getNoticesByDepartment(departmentId));
        }
        if (Boolean.TRUE.equals(pinned)) {
            return ResponseEntity.ok(noticeService.getPinnedNotices());
        }
        if (Boolean.TRUE.equals(general)) {
            return ResponseEntity.ok(noticeService.getGeneralNotices());
        }
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    /**
     * Retrieves a single notice by its identifier.
     *
     * @param id the primary key identifier of the notice
     * @return notice response DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> getNoticeById(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    /**
     * Convenience endpoint to retrieve pinned university notices.
     *
     * @return list of pinned notice response DTOs
     */
    @GetMapping("/pinned")
    public ResponseEntity<List<NoticeResponse>> getPinnedNotices() {
        return ResponseEntity.ok(noticeService.getPinnedNotices());
    }

    /**
     * Retrieves notices belonging to a specific category.
     *
     * @param category notice category (e.g., ACADEMIC, EXAMINATION, HOSTEL)
     * @return list of notice response DTOs
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<NoticeResponse>> getNoticesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(noticeService.getNoticesByCategory(category.trim()));
    }
}
