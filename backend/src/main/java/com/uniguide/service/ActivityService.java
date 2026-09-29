package com.uniguide.service;

import com.uniguide.dto.ActivityRequest;
import com.uniguide.dto.ActivityResponse;
import com.uniguide.entity.Activity;
import com.uniguide.entity.CampusLocation;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.ActivityMapper;
import com.uniguide.repository.ActivityRepository;
import com.uniguide.repository.CampusLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing extracurricular clubs, sports, and student activities.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CampusLocationRepository campusLocationRepository;
    private final ActivityMapper activityMapper;

    public List<ActivityResponse> getAllActivities() {
        return activityRepository.findAll().stream()
                .map(activityMapper::toResponse)
                .toList();
    }

    public ActivityResponse getActivityById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "id", id));
        return activityMapper.toResponse(activity);
    }

    public List<ActivityResponse> getActivitiesByCategory(String category) {
        return activityRepository.findByCategoryIgnoreCase(category).stream()
                .map(activityMapper::toResponse)
                .toList();
    }

    public List<ActivityResponse> getActivitiesByLocation(Long locationId) {
        return activityRepository.findByLocationId(locationId).stream()
                .map(activityMapper::toResponse)
                .toList();
    }

    public List<ActivityResponse> searchActivities(String keyword) {
        return activityRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(activityMapper::toResponse)
                .toList();
    }

    @Transactional
    public ActivityResponse createActivity(ActivityRequest request) {
        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        Activity activity = activityMapper.toEntity(request, location);
        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toResponse(savedActivity);
    }

    @Transactional
    public ActivityResponse updateActivity(Long id, ActivityRequest request) {
        Activity existing = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "id", id));

        CampusLocation location = null;
        if (request.getLocationId() != null) {
            location = campusLocationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new ResourceNotFoundException("CampusLocation", "id", request.getLocationId()));
        }

        existing.setName(request.getName());
        existing.setCategory(request.getCategory());
        existing.setDescription(request.getDescription());
        existing.setCoordinatorName(request.getCoordinatorName());
        existing.setCoordinatorContact(request.getCoordinatorContact());
        existing.setLocation(location);

        Activity updated = activityRepository.save(existing);
        return activityMapper.toResponse(updated);
    }

    @Transactional
    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Activity", "id", id);
        }
        activityRepository.deleteById(id);
    }
}
