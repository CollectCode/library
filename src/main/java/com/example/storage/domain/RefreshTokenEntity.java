package com.example.storage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RefreshToken")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rtId;

    @Column(name = "rt_token", columnDefinition = "VARCHAR(255)", nullable = false)
    private String rtToken;

    @OneToOne
    private UsersEntity user;

    public void updateRefreshToken(String newRtToken) {
        this.rtToken = newRtToken;
    }

    public void updateUser(UsersEntity newUser) {
        this.user = newUser;
    }
}
