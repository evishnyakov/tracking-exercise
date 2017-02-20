package com.test.tour;

import com.test.driver.DriverRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TourService {

    private final @NonNull TourRepository tours;
    private final @NonNull DriverRepository drivers;

    public List<Tour> getTours(Long driverId) {
        return tours.getTours(driverId);
    }

    public void removeTour(Long driverId, Long tourId) {
        tours.delete(tourId);
    }

    public Tour saveTour(Long driverId, Long tourId, Tour tour) {
        return tours.save(tour.clone(tourId, drivers.findOne(driverId)));
    }

}
