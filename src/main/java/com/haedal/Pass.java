package com.haedal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passId;

    private String name;

    private Integer count;

    private LocalDateTime startedDay;

    private LocalDateTime endedDay;

    private Integer price;
    
}
