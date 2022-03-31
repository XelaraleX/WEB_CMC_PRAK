package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "airports")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "airport_name", nullable = false)
    private String airportName;

    @Lob
    @Column(name = "width", nullable = false)
    private Float width;

    @Lob
    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Lob
    @Column(name = "timezone", nullable = false)
    private String timezone;
}
