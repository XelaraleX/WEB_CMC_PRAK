package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "airports")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @Lob
    @Column(name = "airport_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String airportName;

    @Lob
    @Column(name = "width", nullable = false)
    private Double width;

    @Lob
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Lob
    @Column(name = "timezone", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String timezone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airports)) return false;
        Airports airports = (Airports) o;
        return getId().equals(airports.getId()) && getAirportName().equals(airports.getAirportName()) && getWidth().equals(airports.getWidth()) && getLongitude().equals(airports.getLongitude()) && getTimezone().equals(airports.getTimezone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAirportName(), getWidth(), getLongitude(), getTimezone());
    }
}
