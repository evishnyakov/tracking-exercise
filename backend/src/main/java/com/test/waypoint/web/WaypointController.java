package com.test.waypoint.web;

import com.test.waypoint.Waypoint;
import com.test.waypoint.WaypointService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("drivers/{driverId}/waypoints/")
public class WaypointController {

    private final @NonNull WaypointService waypointService;

    @GetMapping
    public List<Waypoint> getWaypoints(@PathVariable("driverId") Long driverId) {
        return waypointService.getWaypoints(driverId);
    }

    @GetMapping("{waypointId}")
    public Waypoint getWaypoint(@PathVariable("driverId") Long driverId, @PathVariable("waypointId") Long waypointId) {
        return waypointService.getWaypoint(driverId, waypointId);
    }

    @PostMapping
    public ResponseEntity<Waypoint> saveWaypoint(@PathVariable("driverId") Long driverId, @RequestBody Waypoint waypoint) {
        return ResponseEntity.ok(waypointService.saveWaypoint(driverId, null, waypoint));
    }

    @PutMapping("{waypointId}")
    public ResponseEntity<Waypoint> updateWaypoint(@PathVariable("driverId") Long driverId, @PathVariable("waypointId") Long waypointId, @RequestBody Waypoint waypoint) {
        return ResponseEntity.ok(waypointService.saveWaypoint(driverId, waypointId, waypoint));
    }

    @DeleteMapping("{waypointId}")
    public ResponseEntity<?> deleteWaypoint(@PathVariable("driverId") Long driverId, @PathVariable("waypointId") Long waypointId) {
        waypointService.deleteWaypoint(driverId, waypointId);
        return ResponseEntity.ok().build();
    }



}
