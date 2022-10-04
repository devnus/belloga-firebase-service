package com.devnus.belloga.firebase.cloudMessagingToken.repository;

import com.devnus.belloga.firebase.cloudMessagingToken.domain.CloudMessagingToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CloudMessagingTokenRepository extends JpaRepository<CloudMessagingToken, Long> {
    Optional<CloudMessagingToken> findByUserId(String userId);
    Optional<CloudMessagingToken> findByToken(String token);
}
