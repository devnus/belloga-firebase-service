package com.devnus.belloga.firebase.cloudMessagingToken.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class RequestCloudMessagingToken {

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateToken {
        @NotEmpty(message = "fcm 토큰이 비어있음")
        private String token;
    }
}
