package com.uniguide.mapper;

import com.uniguide.dto.CourseRequest;
import com.uniguide.dto.CourseResponse;
import com.uniguide.entity.Course;
import com.uniguide.entity.Department;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Course} entity and its DTOs.
 */
@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request, Department department) {
        if (request == null) {
            return null;
        }
        return Course.builder()
                .code(request.getCode())
                .title(request.getTitle())
                .description(request.getDescription())
                .credits(request.getCredits())
                .semester(request.getSemester())
                .department(department)
                .build();
    }

    public CourseResponse toResponse(Course entity) {
        if (entity == null) {
            return null;
        }
        return CourseResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .credits(entity.getCredits())
                .semester(entity.getSemester())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
