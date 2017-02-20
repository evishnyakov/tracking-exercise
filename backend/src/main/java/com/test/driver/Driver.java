package com.test.driver;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.core.AbstractEntity;
import com.test.tour.Tour;
import com.test.waypoint.Waypoint;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString(exclude = {"waypoints", "tours"})
@EqualsAndHashCode(exclude = {"waypoints", "tours"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "driver")
    private List<Waypoint> waypoints = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "driver")
    private List<Tour> tours = new ArrayList<>();
}
