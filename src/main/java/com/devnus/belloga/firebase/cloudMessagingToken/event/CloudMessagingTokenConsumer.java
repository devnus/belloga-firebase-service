package com.devnus.belloga.firebase.cloudMessagingToken.event;

import com.devnus.belloga.firebase.cloudMessagingToken.dto.EventCloudMessagingToken;
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

    /**
     * 푸쉬 알림 요청 이벤트가 들어오면 fcm 을 이용해 푸쉬 알림
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "fcm-push-notification", groupId = "fcm-push-notification-1", containerFactory = "eventCloudMessagingTokenListener")
    protected void consumeFCMPushNotificationEvent(EventCloudMessagingToken.Message event) throws IOException {
        logger.info("push notification event consume: " + event.getTitle() + " " + event.getBody());
        try {
            fcmUtil.sendFCMToTopics(event.getSubscribeTypes(),
                    event.getTitle(),
                    event.getBody(),
                    event.getClickLink(),
                    event.getApnsPushType(),
                    event.getApnsPriority(),
                    event.getAndroidPriority());
        }catch (FirebaseMessagingException firebaseMessagingException) {
            logger.error("error " + firebaseMessagingException.toString());
        }
    }

}
