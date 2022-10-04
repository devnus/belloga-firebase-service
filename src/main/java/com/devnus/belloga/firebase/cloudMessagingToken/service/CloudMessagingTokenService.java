package com.devnus.belloga.firebase.cloudMessagingToken.service;

import com.devnus.belloga.firebase.common.aop.annotation.UserRole;

public interface CloudMessagingTokenService {
    void updateUserToken(String userId, UserRole role, String fcmToken);
}
