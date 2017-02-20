package com.test.tourwaypoint;

import com.test.driver.DriverRepository;
import com.test.tour.TourRepository;
import com.test.waypoint.Waypoint;
import com.test.waypoint.WaypointRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TourWaypointService {

    private final @NonNull TourRepository tours;
    private final @NonNull DriverRepository drivers;
    private final @NonNull TourWaypointRepository tourWaypoints;
    private final @NonNull WaypointRepository waypoints;

    public void removeTourWaypoint(Long tourWaypointId) {
        tourWaypoints.delete(tourWaypointId);
    }

    public TourWaypoint saveTourWaypoint(Long driverId, Long waypointId, Long tourId) {
        Waypoint waypoint = waypoints.findOne(waypointId);
        TourWaypoint tourWaypoint = new TourWaypoint(
                drivers.findOne(driverId),
                tours.findOne(tourId),
                waypoint.getLocation(),
                waypoint.getAddress(),
                waypoint.getRecipient(),
                waypoint.getType()
        );
        return tourWaypoints.save(tourWaypoint);
    }

    public List<TourWaypoint> getWaypoint(Long tourId) {
        return tourWaypoints.getTourWaypoints(tourId);
    }

}
