package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "user_status", nullable = false)
    private String userStatus;

    @Lob
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "email", nullable = false)
    private String email;

    @Lob
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Lob
    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @Lob
    @Column(name = "user_password", nullable = false)
    private String userPassword;
}
