package com.haedal.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PassDto {

    Long id;

    String name;

    Integer count;

    Integer price;

    LocalDateTime startedDay;

    LocalDateTime endedDay;

    public PassDto(Long id, String name, Integer count, Integer price, LocalDateTime startedDay, LocalDateTime endedDay) {
    }

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
                .name(name)
                .count(count)
                .price(price)
                .startedDay(startedDay)
                .endedDay(endedDay)
                .build();
    }

}