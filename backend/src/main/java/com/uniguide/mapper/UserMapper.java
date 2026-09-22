package com.uniguide.mapper;

import com.uniguide.dto.UserRequest;
import com.uniguide.dto.UserResponse;
import com.uniguide.entity.Department;
import com.uniguide.entity.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link User} entity and its DTOs.
 * Ensures sensitive attributes such as passwords are never leaked into responses.
 */
@Component
public class UserMapper {

    public User toEntity(UserRequest request, Department department) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .studentId(request.getStudentId())
                .department(department)
                .build();
    }

    public UserResponse toResponse(User entity) {
        if (entity == null) {
            return null;
        }
        return UserResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .role(entity.getRole())
                .phoneNumber(entity.getPhoneNumber())
                .studentId(entity.getStudentId())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
