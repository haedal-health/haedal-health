package com.haedal.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;


public class PassDto {
    private Long passId;

    private String name;

    private Integer count;

    private Integer price;

    public PassDto(Long passId, String name, Integer count, Integer price) {
    }

    public static PassDto from(Pass pass){
        return new PassDto(
                pass.getPassId(),
                pass.getName(),
                pass.getCount(),
                pass.getPrice()
        );
    }
}
