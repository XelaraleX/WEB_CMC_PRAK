package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bonusprogram")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BonusProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airlines airlineId;

    @Lob
    @Column(name = "bonus_card", nullable = false)
    private String bonusCard;

    @Lob
    @Column(name = "cnt_km", nullable = false)
    private Integer cntKm;

    @Lob
    @Column(name = "cnt_use_km", nullable = false)
    private Integer cntUseKm;
}
