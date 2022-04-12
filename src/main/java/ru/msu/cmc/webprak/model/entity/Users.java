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
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(getId(), users.getId()) && Objects.equals(userStatus, users.userStatus) && Objects.equals(fullName, users.fullName) && Objects.equals(address, users.address) && Objects.equals(email, users.email) && Objects.equals(phoneNumber, users.phoneNumber) && Objects.equals(userLogin, users.userLogin) && Objects.equals(userPassword, users.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), userStatus, fullName, address, email, phoneNumber, userLogin, userPassword);
    }
}
