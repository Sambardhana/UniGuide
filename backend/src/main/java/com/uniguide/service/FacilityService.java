package com.uniguide.service;

import com.uniguide.dto.FacilityRequest;
import com.uniguide.dto.FacilityResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Facility;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.FacilityMapper;
import com.uniguide.repository.CampusLocationRepository;
import com.uniguide.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing campus amenities, libraries, cafeterias, and health centers.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final CampusLocationRepository campusLocationRepository;
    private final FacilityMapper facilityMapper;

    public List<FacilityResponse> getAllFacilities() {
        return facilityRepository.findAll().stream()
                .map(facilityMapper::toResponse)
                .toList();
    }

    public FacilityResponse getFacilityById(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", "id", id));
        return facilityMapper.toResponse(facility);
    }

    public List<FacilityResponse> getFacilitiesByType(String type) {
        return facilityRepository.findByTypeIgnoreCase(type).stream()
                .map(facilityMapper::toResponse)
                .toList();
    }

    public List<FacilityResponse> getFacilitiesByLocation(Long locationId) {
        return facilityRepository.findByLocationId(locationId).stream()
                .map(facilityMapper::toResponse)
                .toList();
    }

    public List<FacilityResponse> searchFacilities(String keyword) {
        return facilityRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(facilityMapper::toResponse)
                .toList();
    }

    @Transactional
    public FacilityResponse createFacility(FacilityRequest request) {
        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        Facility facility = facilityMapper.toEntity(request, location);
        Facility savedFacility = facilityRepository.save(facility);
        return facilityMapper.toResponse(savedFacility);
    }

    @Transactional
    public FacilityResponse updateFacility(Long id, FacilityRequest request) {
        Facility existing = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility", "id", id));

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        existing.setName(request.getName());
        existing.setType(request.getType());
        existing.setDescription(request.getDescription());
        existing.setOpeningHours(request.getOpeningHours());
        existing.setContactNumber(request.getContactNumber());
        existing.setLocation(location);

        Facility updated = facilityRepository.save(existing);
        return facilityMapper.toResponse(updated);
    }

    @Transactional
    public void deleteFacility(Long id) {
        if (!facilityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Facility", "id", id);
        }
        facilityRepository.deleteById(id);
    }
}
