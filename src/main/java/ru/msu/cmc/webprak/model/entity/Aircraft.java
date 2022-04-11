package ru.msu.cmc.webprak.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "model_name", nullable = false)
    private String modelName;
}
