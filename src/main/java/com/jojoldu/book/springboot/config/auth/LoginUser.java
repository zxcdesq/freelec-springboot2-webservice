package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 사용될 곳. PARAMETER: 메소드의 파라미터
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   // @interface 어노테이션 클래스로 지정
}
