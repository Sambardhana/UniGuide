package com.uniguide.mapper;

import com.uniguide.dto.NoticeRequest;
import com.uniguide.dto.NoticeResponse;
import com.uniguide.entity.Department;
import com.uniguide.entity.Notice;
import com.uniguide.entity.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Notice} entity and its DTOs.
 */
@Component
public class NoticeMapper {

    public Notice toEntity(NoticeRequest request, User author, Department department) {
        if (request == null) {
            return null;
        }
        return Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .attachmentUrl(request.getAttachmentUrl())
                .isPinned(request.getIsPinned())
                .publishedAt(request.getPublishedAt())
                .author(author)
                .department(department)
                .build();
    }

    public NoticeResponse toResponse(Notice entity) {
        if (entity == null) {
            return null;
        }
        return NoticeResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .category(entity.getCategory())
                .attachmentUrl(entity.getAttachmentUrl())
                .isPinned(entity.getIsPinned())
                .publishedAt(entity.getPublishedAt())
                .authorId(entity.getAuthor() != null ? entity.getAuthor().getId() : null)
                .authorName(entity.getAuthor() != null ? entity.getAuthor().getFullName() : null)
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
