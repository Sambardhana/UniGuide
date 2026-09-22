package com.uniguide.service;

import com.uniguide.dto.CampusLocationRequest;
import com.uniguide.dto.CampusLocationResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.exception.DuplicateResourceException;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.CampusLocationMapper;
import com.uniguide.repository.CampusLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing campus points of interest and QR navigation locations.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampusLocationService {

    private final CampusLocationRepository campusLocationRepository;
    private final CampusLocationMapper campusLocationMapper;

    public List<CampusLocationResponse> getAllLocations() {
        return campusLocationRepository.findAll().stream()
                .map(campusLocationMapper::toResponse)
                .toList();
    }

    public CampusLocationResponse getLocationById(Long id) {
        CampusLocation location = campusLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", id));
        return campusLocationMapper.toResponse(location);
    }

    public CampusLocationResponse getLocationByCode(String code) {
        CampusLocation location = campusLocationRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "code", code));
        return campusLocationMapper.toResponse(location);
    }

    public CampusLocationResponse getLocationByQrCodeKey(String qrCodeKey) {
        CampusLocation location = campusLocationRepository.findByQrCodeKey(qrCodeKey)
                .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "qrCodeKey", qrCodeKey));
        return campusLocationMapper.toResponse(location);
    }

    public List<CampusLocationResponse> getLocationsByCategory(String category) {
        return campusLocationRepository.findByCategoryIgnoreCase(category).stream()
                .map(campusLocationMapper::toResponse)
                .toList();
    }

    public List<CampusLocationResponse> searchLocations(String keyword) {
        return campusLocationRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(campusLocationMapper::toResponse)
                .toList();
    }

    @Transactional
    public CampusLocationResponse createLocation(CampusLocationRequest request) {
        if (request.getCode() != null && campusLocationRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("CampusLocation", "code", request.getCode());
        }
        if (request.getQrCodeKey() != null && campusLocationRepository.existsByQrCodeKey(request.getQrCodeKey())) {
            throw new DuplicateResourceException("CampusLocation", "qrCodeKey", request.getQrCodeKey());
        }

        CampusLocation location = campusLocationMapper.toEntity(request);
        CampusLocation savedLocation = campusLocationRepository.save(location);
        return campusLocationMapper.toResponse(savedLocation);
    }

    @Transactional
    public CampusLocationResponse updateLocation(Long id, CampusLocationRequest request) {
        CampusLocation existingLocation = campusLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", id));

        if (request.getCode() != null && !request.getCode().equalsIgnoreCase(existingLocation.getCode())) {
            if (campusLocationRepository.existsByCode(request.getCode())) {
                throw new DuplicateResourceException("CampusLocation", "code", request.getCode());
            }
        }
        if (request.getQrCodeKey() != null && !request.getQrCodeKey().equalsIgnoreCase(existingLocation.getQrCodeKey())) {
            if (campusLocationRepository.existsByQrCodeKey(request.getQrCodeKey())) {
                throw new DuplicateResourceException("CampusLocation", "qrCodeKey", request.getQrCodeKey());
            }
        }

        existingLocation.setName(request.getName());
        existingLocation.setCode(request.getCode());
        existingLocation.setCategory(request.getCategory());
        existingLocation.setDescription(request.getDescription());
        existingLocation.setLatitude(request.getLatitude());
        existingLocation.setLongitude(request.getLongitude());
        existingLocation.setFloorCount(request.getFloorCount());
        existingLocation.setQrCodeKey(request.getQrCodeKey());

        CampusLocation updated = campusLocationRepository.save(existingLocation);
        return campusLocationMapper.toResponse(updated);
    }

    @Transactional
    public void deleteLocation(Long id) {
        if (!campusLocationRepository.existsById(id)) {
            throw new ResourceNotFoundException("CampusLocation", "id", id);
        }
        campusLocationRepository.deleteById(id);
    }
}
