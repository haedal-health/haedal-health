package com.haedal.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자를 protected된 상태로 생성한다.
public class BookingResetRequest {
    Long passId;
    Long userId;
}
