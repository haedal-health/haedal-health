package com.haedal.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PassCreateRequest {

    String name;
    Integer count;
    Integer price;
    LocalDateTime startedDay;
    LocalDateTime endedDay;

}
