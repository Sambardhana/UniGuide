package com.uniguide.mapper;

import com.uniguide.dto.ContactRequest;
import com.uniguide.dto.ContactResponse;
import com.uniguide.entity.Contact;
import com.uniguide.entity.Department;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between {@link Contact} entity and its DTOs.
 */
@Component
public class ContactMapper {

    public Contact toEntity(ContactRequest request, Department department) {
        if (request == null) {
            return null;
        }
        return Contact.builder()
                .name(request.getName())
                .designation(request.getDesignation())
                .category(request.getCategory())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .officeLocation(request.getOfficeLocation())
                .department(department)
                .build();
    }

    public ContactResponse toResponse(Contact entity) {
        if (entity == null) {
            return null;
        }
        return ContactResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .designation(entity.getDesignation())
                .category(entity.getCategory())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .officeLocation(entity.getOfficeLocation())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getName() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
