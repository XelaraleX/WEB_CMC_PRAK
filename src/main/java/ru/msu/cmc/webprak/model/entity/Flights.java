package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import ru.msu.cmc.webprak.model.entity.Aircraft;
import ru.msu.cmc.webprak.model.entity.Airlines;
import ru.msu.cmc.webprak.model.entity.Airports;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "flights")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airlines airlineId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "airport_id_dep", nullable = false)
    private Airports airportIdDep;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "airport_id_arr", nullable = false)
    private Airports airportIdArr;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraftId;

    @Lob
    @Column(name = "time_dep", nullable = false)
    private LocalDateTime timeDep;

    @Lob
    @Column(name = "time_arr", nullable = false)
    private LocalDateTime timeArr;

    @Lob
    @Column(name = "flight_cost", nullable = false)
    private Integer flightCost;

    @Lob
    @Column(name = "cnt_seats", nullable = false)
    private Integer cntSeats;

    @Lob
    @Column(name = "cnt_available_seats", nullable = false)
    private Integer cntAvailableSeats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flights flights = (Flights) o;
        return Objects.equals(getId(), flights.getId()) && Objects.equals(airlineId, flights.airlineId) && Objects.equals(airportIdDep, flights.airportIdDep) && Objects.equals(airportIdArr, flights.airportIdArr) && Objects.equals(aircraftId, flights.aircraftId) && Objects.equals(timeDep, flights.timeDep) && Objects.equals(timeArr, flights.timeArr) && Objects.equals(flightCost, flights.flightCost) && Objects.equals(cntSeats, flights.cntSeats) && Objects.equals(cntAvailableSeats, flights.cntAvailableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), airlineId, airportIdDep, airportIdArr, aircraftId, timeDep, timeArr, flightCost, cntSeats, cntAvailableSeats);
    }
}
