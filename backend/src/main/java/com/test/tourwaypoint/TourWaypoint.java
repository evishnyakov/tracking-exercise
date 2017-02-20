package com.test.tourwaypoint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.core.AbstractEntity;
import com.test.driver.Driver;
import com.test.tour.Tour;
import com.test.waypoint.WaypointType;
import lombok.*;

import javax.persistence.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString(exclude = {"driver"})
@EqualsAndHashCode(exclude = {"driver"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TourWaypoint extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private String location;

    private String address;

    private String recipient;

    @Enumerated(EnumType.STRING)
    private WaypointType type;

}
