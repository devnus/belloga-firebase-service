package com.devnus.belloga.firebase.cloudMessagingToken.service;

import com.devnus.belloga.firebase.cloudMessagingToken.domain.AndroidPriority;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.ApnsPriority;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.ApnsPushType;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.SubscribeType;
import com.devnus.belloga.firebase.common.aop.annotation.UserRole;

import java.util.List;

public interface CloudMessagingTokenService {
    void updateUserToken(String userId, UserRole role, String fcmToken);
    void sendToFCMBySubscribeTopics(List<SubscribeType> subscribeTypes, String title, String body, String clickLink, ApnsPushType apnsPushType, ApnsPriority apnsPriority, AndroidPriority androidPriority);
    void sendToFCMByToken(String userId, String title, String body, String clickLink, ApnsPushType apnsPushType, ApnsPriority apnsPriority, AndroidPriority androidPriority);
}
