package com.test.tour;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.core.AbstractEntity;
import com.test.driver.Driver;
import com.test.tourwaypoint.TourWaypoint;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString(exclude = {"driver"})
@EqualsAndHashCode(exclude = {"driver"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tour extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private String title;

    @OrderColumn
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "tour")
    private List<TourWaypoint> tourWaypoints = new ArrayList<>();

    private Tour(Long id) {
        super(id);
    }

    public Tour clone(Long id, Driver driver) {
        Tour tour = new Tour(id);
        tour.title = title;
        tour.driver = driver;
        return tour;
    }

}
