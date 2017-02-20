package com.test.waypoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {

    @Query("SELECT w FROM Waypoint w WHERE w.driver.id = ?1")
    List<Waypoint> getWaypoints(Long driverId);

}
