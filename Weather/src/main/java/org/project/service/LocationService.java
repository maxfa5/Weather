package org.project.service;

import java.util.List;
import java.util.UUID;

import org.project.model.LocationModel;
import org.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public String getLocation(UUID sessionId) {
        return sessionId.toString();
    }

    public List<LocationModel> getLocations(int userId) {
        return locationRepository.findByUserId(userId);
    }
    public int getCountLocations(int userId) {
        return locationRepository.findByUserId(userId).size();
    }
    public boolean locationExists(int userId, LocationModel location) {
        return locationRepository.findByUserId(userId)
                .stream()
                .anyMatch(loc -> loc.getName().equalsIgnoreCase(location.getName()) && loc.getLatitude() == location.getLatitude() && loc.getLongitude() == location.getLongitude());
    }

    public LocationModel addLocation(LocationModel location) {
        if (locationExists(location.getUserId(), location)) {
            throw new RuntimeException("Локация уже существует");
        }
        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId, int userId) {
        LocationModel location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Локация не найдена"));
        
        if (location.getUserId() != userId) {
            throw new RuntimeException("Нет прав для удаления этой локации");
        }
        
        locationRepository.deleteById(locationId);
    }
}
