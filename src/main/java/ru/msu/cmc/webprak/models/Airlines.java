package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Airlines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "airline_name", nullable = false)
    private String airlineName;

    @Lob
    @Column(name = "airline_email")
    private String airlineEmail;

    @Lob
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
