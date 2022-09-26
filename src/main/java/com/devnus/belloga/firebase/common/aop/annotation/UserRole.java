package com.devnus.belloga.firebase.common.aop.annotation;

import lombok.Getter;

/**
 * 헤더에서 key에 해당하는 value를 가져오기 위한 에넘 타입
 * @author suhongkim
 */
@Getter
public enum UserRole {
    ENTERPRISE("enterprise-id"),
    ADMIN("admin-id"),
    LABELER("labeler-id");

    private String key;

    UserRole(final String key) {
        this.key = key;
    }
}


