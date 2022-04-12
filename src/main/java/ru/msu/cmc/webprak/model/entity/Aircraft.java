package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @Lob
    @Column(name = "model_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String modelName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return getId().equals(aircraft.getId()) && Objects.equals(getModelName(), aircraft.getModelName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModelName());
    }
}
