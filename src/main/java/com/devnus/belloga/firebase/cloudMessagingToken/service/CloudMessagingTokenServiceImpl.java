package com.devnus.belloga.firebase.cloudMessagingToken.service;

import com.devnus.belloga.firebase.cloudMessagingToken.domain.CloudMessagingToken;
import com.devnus.belloga.firebase.cloudMessagingToken.repository.CloudMessagingTokenRepository;
import com.devnus.belloga.firebase.common.aop.annotation.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CloudMessagingTokenServiceImpl implements CloudMessagingTokenService {
    private final CloudMessagingTokenRepository cloudMessagingTokenRepository;

    /**
     * 사용자의 토큰을 디비에 저장하거나 업데이트한다.
     * @param userId
     * @param role
     * @param fcmToken
     */
    @Override
    @Transactional
    public void updateUserToken(String userId, UserRole role, String fcmToken) {
        CloudMessagingToken token = cloudMessagingTokenRepository.findByUserId(userId)
                .orElseGet(()->null);

        if(token == null) {
            token = cloudMessagingTokenRepository.save(CloudMessagingToken.builder()
                            .userId(userId)
                            .userRole(role)
                            .token(fcmToken)
                    .build());
        } else {
            token.updateUserToken(fcmToken);
        }
    }
}
