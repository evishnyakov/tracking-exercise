package com.test.waypoint;

import com.test.driver.DriverRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class WaypointService {

    private final @NonNull WaypointRepository waypoints;
    private final @NonNull DriverRepository drivers;

    public List<Waypoint> getWaypoints(Long driverId) {
        return waypoints.getWaypoints(driverId);
    }

    public Waypoint getWaypoint(Long driverId, Long waypointId) {
        return waypoints.findOne(waypointId);
    }

    public Waypoint saveWaypoint(Long driverId, Long waypointId, Waypoint waypoint) {
        return waypoints.save(waypoint.clone(waypointId, drivers.findOne(driverId)));
    }

    public void deleteWaypoint(Long driverId, Long waypointId) {
        waypoints.delete(waypointId);
    }

}
