package ru.msu.cmc.webprak.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",
            columnDefinition = "serial",
            insertable = false,
            updatable = false)
    private Integer id;

    @Lob
    @Column(name = "user_status", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String userStatus;

    @Lob
    @Column(name = "full_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String fullName;

    @Lob
    @Column(name = "address")
    @Type(type = "org.hibernate.type.TextType")
    private String address;

    @Lob
    @Column(name = "email", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String email;

    @Lob
    @Column(name = "phone_number", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String phoneNumber;

    @Lob
    @Column(name = "user_login", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String userLogin;

    @Lob
    @Column(name = "user_password", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String userPassword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) && getUserStatus().equals(users.getUserStatus()) && getFullName().equals(users.getFullName()) && getAddress().equals(users.getAddress()) && getEmail().equals(users.getEmail()) && getPhoneNumber().equals(users.getPhoneNumber()) && getUserLogin().equals(users.getUserLogin()) && getUserPassword().equals(users.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserStatus(), getFullName(), getAddress(), getEmail(), getPhoneNumber(), getUserLogin(), getUserPassword());
    }
}
