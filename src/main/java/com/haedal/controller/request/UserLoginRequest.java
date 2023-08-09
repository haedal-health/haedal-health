package com.haedal.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자를 protected된 상태로 생성한다.
public class UserLoginRequest {
    String name;
    String password;
}

