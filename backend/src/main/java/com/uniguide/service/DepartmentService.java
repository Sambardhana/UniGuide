package com.uniguide.service;

import com.uniguide.dto.DepartmentRequest;
import com.uniguide.dto.DepartmentResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Department;
import com.uniguide.exception.DuplicateResourceException;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.DepartmentMapper;
import com.uniguide.repository.CampusLocationRepository;
import com.uniguide.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing academic departments and their relationships.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CampusLocationRepository campusLocationRepository;
    private final DepartmentMapper departmentMapper;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return departmentMapper.toResponse(department);
    }

    public DepartmentResponse getDepartmentByCode(String code) {
        Department department = departmentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "code", code));
        return departmentMapper.toResponse(department);
    }

    public List<DepartmentResponse> getDepartmentsByLocation(Long locationId) {
        return departmentRepository.findByLocationId(locationId).stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    public List<DepartmentResponse> searchDepartments(String keyword) {
        return departmentRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Department", "code", request.getCode());
        }

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        Department department = departmentMapper.toEntity(request, location);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMapper.toResponse(savedDepartment);
    }

    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        if (!request.getCode().equalsIgnoreCase(existing.getCode())) {
            if (departmentRepository.existsByCode(request.getCode())) {
                throw new DuplicateResourceException("Department", "code", request.getCode());
            }
        }

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        existing.setName(request.getName());
        existing.setCode(request.getCode());
        existing.setDescription(request.getDescription());
        existing.setContactEmail(request.getContactEmail());
        existing.setContactPhone(request.getContactPhone());
        existing.setLocation(location);

        Department updated = departmentRepository.save(existing);
        return departmentMapper.toResponse(updated);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department", "id", id);
        }
        departmentRepository.deleteById(id);
    }
}
