package com.haedal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record PassDto(
        Long id,
        String name,
        Integer count,
        Integer price,
        LocalDateTime startedDay,
        LocalDateTime endedDay
) {

    public static PassDto of(Long id, String name, Integer count, Integer price, LocalDateTime startedDay, LocalDateTime endedDay) {
        return new PassDto(id, name, count, price, startedDay, endedDay);
    }

    public static PassDto from(Pass pass){
        return new PassDto(
                pass.getPassId(),
                pass.getName(),
                pass.getCount(),
                pass.getPrice(),
                pass.getStartedDay(),
                pass.getEndedDay()
        );
    }


    public Pass toEntity() {
        return Pass.builder()
                .passId(id)
                .name(name)
                .count(count)
                .price(price)
                .startedDay(startedDay)
                .endedDay(endedDay)
                .build();
    }
}