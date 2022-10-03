package com.devnus.belloga.firebase.pushNotification.dto;

import com.devnus.belloga.firebase.pushNotification.domain.AndroidPriority;
import com.devnus.belloga.firebase.pushNotification.domain.ApnsPriority;
import com.devnus.belloga.firebase.pushNotification.domain.ApnsPushType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventPushNotification {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String title;
        private String body;
        private String clickLink;

        private ApnsPushType apnsPushType;
        private ApnsPriority apnsPriority;
        private AndroidPriority androidPriority;
    }
}
