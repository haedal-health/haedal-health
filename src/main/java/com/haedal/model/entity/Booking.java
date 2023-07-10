package com.haedal.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDateTime startTime;

    private LocalDateTime endedTime;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "passId", insertable = false, updatable = false)
    private Long passId;

    public Booking(Long userId, Long passId) {
        this.userId = userId;
        this.passId = passId;
        this.startTime = null;
        this.endedTime = null;
    }
}
