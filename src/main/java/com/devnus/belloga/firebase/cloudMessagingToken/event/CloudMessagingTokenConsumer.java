package com.devnus.belloga.firebase.cloudMessagingToken.event;

import com.devnus.belloga.firebase.cloudMessagingToken.dto.EventCloudMessagingToken;
import com.devnus.belloga.firebase.cloudMessagingToken.service.CloudMessagingTokenService;
import com.devnus.belloga.firebase.cloudMessagingToken.util.FCMUtil;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CloudMessagingTokenConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CloudMessagingTokenConsumer.class);
    private final FCMUtil fcmUtil;
    private final CloudMessagingTokenService cloudMessagingTokenService;

    /**
     * 푸쉬 알림 요청 이벤트가 들어오면 fcm 을 이용해 푸쉬 알림(구독)
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "fcm-push-notification-to-subscribe", groupId = "fcm-push-notification-subscribe-1", containerFactory = "eventCloudMessagingTokenBySubscribeListener")
    protected void consumeFCMPushNotificationEventBySubscribe(EventCloudMessagingToken.MessageBySubscribe event) throws IOException {
        cloudMessagingTokenService.sendToFCMBySubscribeTopics(event.getSubscribeTypes(),
                event.getTitle(),
                event.getBody(),
                event.getClickLink(),
                event.getApnsPushType(),
                event.getApnsPriority(),
                event.getAndroidPriority());
    }

    /**
     * 푸쉬 알림 요청 이벤트가 들어오면 fcm 을 이용해 푸쉬 알림(토큰)
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "fcm-push-notification-to-token", groupId = "fcm-push-notification-token-1", containerFactory = "eventCloudMessagingTokenByTokenListener")
    protected void consumeFCMPushNotificationEventByToken(EventCloudMessagingToken.MessageByToken event) throws IOException {
        cloudMessagingTokenService.sendToFCMByToken(event.getUserId(),
                event.getTitle(),
                event.getBody(),
                event.getClickLink(),
                event.getApnsPushType(),
                event.getApnsPriority(),
                event.getAndroidPriority());
    }

}
