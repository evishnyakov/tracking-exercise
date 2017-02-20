package com.test.config;

import com.test.driver.Driver;
import com.test.driver.DriverRepository;
import com.test.tour.Tour;
import com.test.waypoint.Waypoint;
import com.test.waypoint.WaypointRepository;
import com.test.waypoint.WaypointType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@RequiredArgsConstructor
@Configuration
public class InitConfig {

    private final @NotNull DriverRepository drivers;
    private final @NotNull WaypointRepository waypoints;

    @Bean
    public InitializingBean init() {
        return () -> {
            Driver driver = drivers.save(new Driver("John", "Doe", "+123456789", "john@mail.com", new ArrayList<Waypoint>(), new ArrayList<Tour>()));

            waypoints.save(new Waypoint(driver, "loc1", "addr1", "rec1", WaypointType.DROPOFF));
            waypoints.save(new Waypoint(driver, "loc2", "addr2", "rec2", WaypointType.PICKUP));
        };
    }


}
