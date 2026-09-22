package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Request payload for creating or updating a {@link com.uniguide.entity.Notice}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeRequest {

    @NotBlank(message = "Notice title is required")
    @Size(min = 2, max = 200, message = "Notice title must be between 2 and 200 characters")
    private String title;

    @NotBlank(message = "Notice content is required")
    @Size(min = 5, message = "Notice content must be at least 5 characters")
    private String content;

    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @Size(max = 500, message = "Attachment URL cannot exceed 500 characters")
    private String attachmentUrl;

    private Boolean isPinned;

    private LocalDateTime publishedAt;

    @Positive(message = "Author ID must be a positive number")
    private Long authorId;

    @Positive(message = "Department ID must be a positive number")
    private Long departmentId;
}
