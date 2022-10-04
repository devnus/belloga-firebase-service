package com.devnus.belloga.firebase.cloudMessagingToken.domain;

import lombok.Getter;

@Getter
public enum SubscribeType {

    ALL("전체(기본 구독 토픽)");

    private String korean;
    SubscribeType(String korean) {
        this.korean = korean;
    }
}
