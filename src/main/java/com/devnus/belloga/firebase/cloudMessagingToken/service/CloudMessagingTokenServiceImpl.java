package com.devnus.belloga.firebase.cloudMessagingToken.service;

import com.devnus.belloga.firebase.cloudMessagingToken.domain.*;
import com.devnus.belloga.firebase.cloudMessagingToken.repository.CloudMessagingTokenRepository;
import com.devnus.belloga.firebase.cloudMessagingToken.util.FCMUtil;
import com.devnus.belloga.firebase.common.aop.annotation.UserRole;
import com.devnus.belloga.firebase.common.exception.error.NotFoundUserException;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CloudMessagingTokenServiceImpl implements CloudMessagingTokenService {
    private static final Logger logger = LoggerFactory.getLogger(CloudMessagingTokenServiceImpl.class);
    private final FCMUtil fcmUtil;
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
        CloudMessagingToken token = cloudMessagingTokenRepository.findByToken(fcmToken)
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

    @Override
    public void sendToFCMBySubscribeTopics(List<SubscribeType> subscribeTypes, String title, String body, String clickLink, ApnsPushType apnsPushType, ApnsPriority apnsPriority, AndroidPriority androidPriority) {
        logger.info("push notification event consume: " + title + " " + body);
        try {
            fcmUtil.sendFCMToTopics(subscribeTypes,
                    title,
                    body,
                    clickLink,
                    apnsPushType,
                    apnsPriority,
                    androidPriority);
        }catch (FirebaseMessagingException firebaseMessagingException) {
            logger.error("error " + firebaseMessagingException.toString());
        }
    }

    /**
     * 디비에서 유저아이디로 토큰을 꺼내 해당 사용자에게 메시지를 전송한다.
     * @param userId
     * @param title
     * @param body
     * @param clickLink
     * @param apnsPushType
     * @param apnsPriority
     * @param androidPriority
     */
    @Override
    @Transactional(readOnly = true)
    public void sendToFCMByToken(String userId, String title, String body, String clickLink, ApnsPushType apnsPushType, ApnsPriority apnsPriority, AndroidPriority androidPriority) {
        CloudMessagingToken cloudMessagingToken = cloudMessagingTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundUserException());

        logger.info("push notification event consume: " + title + " " + body + " to " + cloudMessagingToken.getToken());
        try {
            fcmUtil.sendToOneToken(cloudMessagingToken.getToken(),
                    title,
                    body,
                    clickLink,
                    apnsPushType,
                    apnsPriority,
                    androidPriority);
        }catch (FirebaseMessagingException firebaseMessagingException) {
            logger.error("error " + firebaseMessagingException.toString());
        }
    }
}
