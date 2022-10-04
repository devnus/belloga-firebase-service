package com.devnus.belloga.firebase.cloudMessagingToken.domain;

import com.devnus.belloga.firebase.common.aop.annotation.UserRole;
import com.devnus.belloga.firebase.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cloud_messaging_token")
@Getter
@NoArgsConstructor
public class CloudMessagingToken extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "token")
    private String token;

    @Builder
    public CloudMessagingToken(String userId, UserRole userRole, String token) {
        this.userId = userId;
        this.userRole = userRole;
        this.token = token;
    }

    public void updateUserToken(String newToken) {
        this.token = newToken;
    }
}
