package ru.msu.cmc.webprak.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
    @ToString.Exclude
    private Flights flightId;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;
}
