package com.haedal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haedal.model.entity.Pass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //직렬화 속성무시
public class PassDto{
        Long id;
        String name;
        Integer count;
        Integer price;
        LocalDateTime startedDay;
        LocalDateTime endedDay;

    public PassDto(String name, Integer count, Integer price, LocalDateTime startedDay, LocalDateTime endedDay) {
    }

    public static PassDto of(String name, Integer count, Integer price, LocalDateTime startedDay, LocalDateTime endedDay) {
        return new PassDto(name, count, price, startedDay, endedDay);
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
                .name(name)
                .count(count)
                .price(price)
                .startedDay(startedDay)
                .endedDay(endedDay)
                .build();
    }
}