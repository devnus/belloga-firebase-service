package com.devnus.belloga.firebase.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HttpServletRequest 에서 사용자 식별 ID를 꺼내올 때 사용하는 어노테이션이다.
 * 컨트롤러의 파라미터에 @GetAccountIdentification(role) 형태로 사용한다.
 * @author suhongkim
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface GetAccountIdentification {
    UserRole role();
}


