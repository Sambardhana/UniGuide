package com.uniguide.service;

import com.uniguide.dto.HostelRequest;
import com.uniguide.dto.HostelResponse;
import com.uniguide.entity.CampusLocation;
import com.uniguide.entity.Hostel;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.HostelMapper;
import com.uniguide.repository.CampusLocationRepository;
import com.uniguide.repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing residential student hostels and housing info.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HostelService {

    private final HostelRepository hostelRepository;
    private final CampusLocationRepository campusLocationRepository;
    private final HostelMapper hostelMapper;

    public List<HostelResponse> getAllHostels() {
        return hostelRepository.findAll().stream()
                .map(hostelMapper::toResponse)
                .toList();
    }

    public HostelResponse getHostelById(Long id) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hostel", "id", id));
        return hostelMapper.toResponse(hostel);
    }

    public List<HostelResponse> getHostelsByType(String type) {
        return hostelRepository.findByTypeIgnoreCase(type).stream()
                .map(hostelMapper::toResponse)
                .toList();
    }

    public List<HostelResponse> getHostelsByLocation(Long locationId) {
        return hostelRepository.findByLocationId(locationId).stream()
                .map(hostelMapper::toResponse)
                .toList();
    }

    @Transactional
    public HostelResponse createHostel(HostelRequest request) {
        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        Hostel hostel = hostelMapper.toEntity(request, location);
        Hostel savedHostel = hostelRepository.save(hostel);
        return hostelMapper.toResponse(savedHostel);
    }

    @Transactional
    public HostelResponse updateHostel(Long id, HostelRequest request) {
        Hostel existing = hostelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hostel", "id", id));

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        existing.setName(request.getName());
        existing.setType(request.getType());
        existing.setCapacity(request.getCapacity());
        existing.setWardenName(request.getWardenName());
        existing.setWardenContact(request.getWardenContact());
        existing.setDescription(request.getDescription());
        existing.setLocation(location);

        Hostel updated = hostelRepository.save(existing);
        return hostelMapper.toResponse(updated);
    }

    @Transactional
    public void deleteHostel(Long id) {
        if (!hostelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hostel", "id", id);
        }
        hostelRepository.deleteById(id);
    }
}
