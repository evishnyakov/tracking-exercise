package com.test.tour.web;

import com.test.tour.Tour;
import com.test.tour.TourService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("drivers/{driverId}/tours/")
public class TourController {

    private final @NonNull TourService tourService;

    @GetMapping
    public List<Tour> getTours(@PathVariable("driverId") Long driverId) {
        return tourService.getTours(driverId);
    }

    @PostMapping
    public Tour saveTour(@PathVariable("driverId") Long driverId, @RequestBody Tour tour) {
        return tourService.saveTour(driverId, null, tour);
    }

    @PutMapping("{tourId}")
    public Tour updateTour(@PathVariable("driverId") Long driverId, @PathVariable("tourId") Long tourId, @RequestBody Tour tour) {
        return tourService.saveTour(driverId, tourId, tour);
    }

    @DeleteMapping("{tourId}")
    public void deleteTour(@PathVariable("driverId") Long driverId, @PathVariable("tourId") Long tourId) {
        tourService.removeTour(driverId, tourId);
    }

}
