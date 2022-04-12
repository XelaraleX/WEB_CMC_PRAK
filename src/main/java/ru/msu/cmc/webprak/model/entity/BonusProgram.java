package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "bonusprogram")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class BonusProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bonus_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private Users userId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    @ToString.Exclude
    private Airlines airlineId;

    @Lob
    @Column(name = "bonus_card", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String bonusCard;

    @Column(name = "cnt_km", nullable = false)
    private Integer cntKm;

    @Column(name = "cnt_use_km", nullable = false)
    private Integer cntUseKm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BonusProgram)) return false;
        BonusProgram that = (BonusProgram) o;
        return getId().equals(that.getId()) && getUserId().equals(that.getUserId()) && getAirlineId().equals(that.getAirlineId()) && getBonusCard().equals(that.getBonusCard()) && getCntKm().equals(that.getCntKm()) && getCntUseKm().equals(that.getCntUseKm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getAirlineId(), getBonusCard(), getCntKm(), getCntUseKm());
    }
}
