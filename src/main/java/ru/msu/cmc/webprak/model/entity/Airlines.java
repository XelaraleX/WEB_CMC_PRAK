package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class Airlines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @Lob
    @Column(name = "airline_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String airlineName;

    @Lob
    @Column(name = "airline_email")
    @Type(type = "org.hibernate.type.TextType")
    private String airlineEmail;

    @Lob
    @Column(name = "phone_number", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airlines airlines = (Airlines) o;
        return Objects.equals(getId(), airlines.getId()) && Objects.equals(airlineName, airlines.airlineName) && Objects.equals(airlineEmail, airlines.airlineEmail) && Objects.equals(phoneNumber, airlines.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), airlineName, airlineEmail, phoneNumber);
    }
}
