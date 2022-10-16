package com.devnus.belloga.firebase.common.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value(value = "${app.topic.cloud-messaging-token.fcm-push-notification-to-subscribe}")
    private String FCM_PUSH_NOTIFICATION_TO_SUBSCRIBE;

    @Value(value = "${app.topic.cloud-messaging-token.fcm-push-notification-to-token}")
    private String FCM_PUSH_NOTIFICATION_TO_TOKEN;

    /**
     * 푸쉬 알림을 보내고자할 경우 사용할 토픽 생성(구독자 전송)
     * 같은 이름의 토픽이 등록되어 있다면 동작하지 않음
     * partition 개수, replica 개수는 논의 후 수정
     */
    @Bean
    public NewTopic createFCMPushNotificationToSubscribeTopic() {
        return TopicBuilder.name(FCM_PUSH_NOTIFICATION_TO_SUBSCRIBE)
                .partitions(1)
                .replicas(1)
                .build();
    }
    /**
     * 푸쉬 알림을 보내고자할 경우 사용할 토픽 생성(토큰으로 보내기)
     * 같은 이름의 토픽이 등록되어 있다면 동작하지 않음
     * partition 개수, replica 개수는 논의 후 수정
     */
    @Bean
    public NewTopic createFCMPushNotificationToTokenTopic() {
        return TopicBuilder.name(FCM_PUSH_NOTIFICATION_TO_TOKEN)
                .partitions(1)
                .replicas(1)
                .build();
    }
}



