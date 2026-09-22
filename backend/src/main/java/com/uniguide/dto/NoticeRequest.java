package com.uniguide.dto;

import jakarta.validation.constraints.NotBlank;
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
    private String title;

    @NotBlank(message = "Notice content is required")
    private String content;

    private String category;

    private String attachmentUrl;

    private Boolean isPinned;

    private LocalDateTime publishedAt;

    private Long authorId;

    private Long departmentId;
}
