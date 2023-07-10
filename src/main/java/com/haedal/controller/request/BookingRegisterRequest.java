package com.haedal.controller.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자를 protected된 상태로 생성한다.
public class BookingRegisterRequest {
    Long passId;
    Long userId;
}
