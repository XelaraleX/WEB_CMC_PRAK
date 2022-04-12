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
        if (o == null || getClass() != o.getClass()) return false;
        Airports airports = (Airports) o;
        return Objects.equals(getId(), airports.getId()) && Objects.equals(airportName, airports.airportName) && Objects.equals(width, airports.width) && Objects.equals(longitude, airports.longitude) && Objects.equals(timezone, airports.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), airportName, width, longitude, timezone);
    }
}
