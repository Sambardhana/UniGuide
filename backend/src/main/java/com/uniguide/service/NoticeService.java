package com.uniguide.service;

import com.uniguide.dto.NoticeRequest;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.entity.Department;
import com.uniguide.entity.Notice;
import com.uniguide.entity.User;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.NoticeMapper;
import com.uniguide.repository.DepartmentRepository;
import com.uniguide.repository.NoticeRepository;
import com.uniguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing academic circulars, hostel updates, and university announcements.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final NoticeMapper noticeMapper;

    public List<NoticeResponse> getAllNotices() {
        return noticeRepository.findAllByOrderByPublishedAtDesc().stream()
                .map(noticeMapper::toResponse)
                .toList();
    }

    public List<NoticeResponse> getPinnedNotices() {
        return noticeRepository.findByIsPinnedTrueOrderByPublishedAtDesc().stream()
                .map(noticeMapper::toResponse)
                .toList();
    }

    public NoticeResponse getNoticeById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice", "id", id));
        return noticeMapper.toResponse(notice);
    }

    public List<NoticeResponse> getNoticesByCategory(String category) {
        return noticeRepository.findByCategoryIgnoreCaseOrderByPublishedAtDesc(category).stream()
                .map(noticeMapper::toResponse)
                .toList();
    }

    public List<NoticeResponse> getNoticesByDepartment(Long departmentId) {
        return noticeRepository.findByDepartmentIdOrderByPublishedAtDesc(departmentId).stream()
                .map(noticeMapper::toResponse)
                .toList();
    }

    public List<NoticeResponse> getGeneralNotices() {
        return noticeRepository.findByDepartmentIdIsNullOrderByPublishedAtDesc().stream()
                .map(noticeMapper::toResponse)
                .toList();
    }

    @Transactional
    public NoticeResponse createNotice(NoticeRequest request) {
        User author = null;
        if (request.getAuthorId() != null) {
            author = userRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getAuthorId()));
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        Notice notice = noticeMapper.toEntity(request, author, department);
        Notice savedNotice = noticeRepository.save(notice);
        return noticeMapper.toResponse(savedNotice);
    }

    @Transactional
    public NoticeResponse updateNotice(Long id, NoticeRequest request) {
        Notice existing = noticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice", "id", id));

        User author = null;
        if (request.getAuthorId() != null) {
            author = userRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getAuthorId()));
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        existing.setCategory(request.getCategory());
        existing.setAttachmentUrl(request.getAttachmentUrl());
        existing.setIsPinned(request.getIsPinned());
        if (request.getPublishedAt() != null) {
            existing.setPublishedAt(request.getPublishedAt());
        }
        existing.setAuthor(author);
        existing.setDepartment(department);

        Notice updated = noticeRepository.save(existing);
        return noticeMapper.toResponse(updated);
    }

    @Transactional
    public void deleteNotice(Long id) {
        if (!noticeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notice", "id", id);
        }
        noticeRepository.deleteById(id);
    }
}
