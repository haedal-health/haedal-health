package com.haedal.model.entity;

import com.haedal.model.UserDto;
import com.haedal.model.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 기본 생성자를 protected된 상태로 생성한다.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String phone;

    @Builder.Default
    private UserRole role = UserRole.USER;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}

