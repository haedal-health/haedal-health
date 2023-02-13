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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passId;

    private String name;

    private Integer count;

    private LocalDateTime startedDay;

    private LocalDateTime endedDay;

    private Integer price;

    @OneToMany(mappedBy="pass")
    List<Booking> bookings;
    //이용권을
    
}
