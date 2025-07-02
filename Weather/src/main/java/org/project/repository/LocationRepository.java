package org.project.repository;

import java.util.List;

import org.project.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationModel, Integer> {
    List<LocationModel> findByUserId(int userId);
}
