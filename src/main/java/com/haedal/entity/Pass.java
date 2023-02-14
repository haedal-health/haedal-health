package com.haedal.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passId;

    private String name;

    private Integer count;

    private LocalDateTime startedDay;

    private LocalDateTime endedDay;

    private Integer price;
//
//    @OneToMany(mappedBy="pass")
//    private List<Booking> bookings;
//    //양방향 연결이며, 오로지
//    // pass에서만 조회, 저장, 수정, 삭제가 가능함.
    
}
