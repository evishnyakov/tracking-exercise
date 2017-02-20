package com.test.tourwaypoint.web;

import com.test.tourwaypoint.TourWaypoint;
import com.test.tourwaypoint.TourWaypointService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("drivers/{driverId}/tours/{tourId}/waypoints/")
public class TourWaypointController {

    private final @NonNull TourWaypointService tourWaypointService;

    @GetMapping
    public List<TourWaypoint> getTours(@PathVariable("tourId") Long tourId) {
        return tourWaypointService.getWaypoint(tourId);
    }

    @DeleteMapping("{tourWaypointId}")
    public void deleteTour(@PathVariable("tourWaypointId") Long tourWaypointId) {
        tourWaypointService.removeTourWaypoint(tourWaypointId);
    }

    @PostMapping("assign/{waypointId}")
    public TourWaypoint makeTourWaypoint(@PathVariable("driverId") Long driverId, @PathVariable("waypointId") Long waypointId, @PathVariable("tourId") Long tourId) {
        return tourWaypointService.saveTourWaypoint(driverId, waypointId, tourId);
    }

}
