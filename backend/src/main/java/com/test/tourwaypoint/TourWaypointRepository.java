package com.test.tourwaypoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourWaypointRepository extends JpaRepository<TourWaypoint, Long> {

    @Query("SELECT t FROM TourWaypoint t WHERE t.tour.id = ?1")
    List<TourWaypoint> getTourWaypoints(Long tourId);


}
