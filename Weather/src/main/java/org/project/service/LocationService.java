package org.project.service;

import java.util.List;
import java.util.UUID;

import org.project.model.LocationModel;
import org.project.repository.LocationRepository;
import org.project.exceptions.LocationException;
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
    public long getCountLocations(int userId) {
        // TODO: Добавить метод countByUserId в LocationRepository для оптимизации
        // Вместо загрузки всех локаций и подсчета размера списка
        return locationRepository.countByUserId(userId);
    }
    public boolean locationExists(int userId, LocationModel location) {
        return locationRepository.findByUserId(userId)
                .stream()
                .anyMatch(loc -> loc.getName().equalsIgnoreCase(location.getName()) && loc.getLatitude() == location.getLatitude() && loc.getLongitude() == location.getLongitude());
    }

    public LocationModel addLocation(LocationModel location) throws LocationException {
        if (locationExists(location.getUserId(), location)) {
            throw new LocationException("Локация уже существует");
        }
        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId, int userId) throws LocationException {
        LocationModel location = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationException("Локация не найдена"));
        
        if (location.getUserId() != userId) {
            throw new LocationException("Нет прав для удаления этой локации");
        }
        
        locationRepository.deleteById(locationId);
    }
}
