package com.haedal.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BookingUpdateRequest {

    LocalDateTime startTime;
    LocalDateTime endedTime;
    String teacher;

}
