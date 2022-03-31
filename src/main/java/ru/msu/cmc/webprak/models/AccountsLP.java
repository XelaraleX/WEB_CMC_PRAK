package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accountslp")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor

public class AccountsLP {
    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private Users id;

    @Lob
    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @Lob
    @Column(name = "user_password", nullable = false)
    private String userPassword;
}
