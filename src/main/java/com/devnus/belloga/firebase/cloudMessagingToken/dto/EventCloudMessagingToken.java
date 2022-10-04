package com.devnus.belloga.firebase.cloudMessagingToken.dto;

import com.devnus.belloga.firebase.cloudMessagingToken.domain.AndroidPriority;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.ApnsPriority;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.ApnsPushType;
import com.devnus.belloga.firebase.cloudMessagingToken.domain.SubscribeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class EventCloudMessagingToken {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private List<SubscribeType> subscribeTypes; //보내고자하는 구독 유형 리스트
        private String title;
        private String body;
        private String clickLink;

        private ApnsPushType apnsPushType;
        private ApnsPriority apnsPriority;
        private AndroidPriority androidPriority;
    }
}
