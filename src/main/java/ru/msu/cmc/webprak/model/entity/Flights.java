package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import ru.msu.cmc.webprak.model.entity.Aircraft;
import ru.msu.cmc.webprak.model.entity.Airlines;
import ru.msu.cmc.webprak.model.entity.Airports;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id", nullable = false)
    @ToString.Exclude
    private Airlines airlineId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id_dep", nullable = false)
    @ToString.Exclude
    private Airports airportIdDep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id_arr", nullable = false)
    @ToString.Exclude
    private Airports airportIdArr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aircraft_id", nullable = false)
    @ToString.Exclude
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
}
