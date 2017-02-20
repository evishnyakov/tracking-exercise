package com.test.waypoint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.core.AbstractEntity;
import com.test.driver.Driver;
import lombok.*;

import javax.persistence.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ToString(exclude = {"driver"})
@EqualsAndHashCode(exclude = {"driver"})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Waypoint extends AbstractEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private String location;

    private String address;

    private String recipient;

    @Enumerated(EnumType.STRING)
    private WaypointType type;

    private Waypoint(Long id) {
        super(id);
    }

    public Waypoint clone(Long id, Driver driver) {
        Waypoint waypoint = new Waypoint(id);
        waypoint.location = location;
        waypoint.address = address;
        waypoint.recipient = recipient;
        waypoint.type = type;
        waypoint.driver = driver;
        return waypoint;
    }
}
