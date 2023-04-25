package com.haedal.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserDto (
        Long userId,
        String name,
        String phone
){

    public static UserDto of(Long userId,String name,String phone){
        return new UserDto(userId, name, phone);
    }
    public static UserDto from(User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getPhone()
        );
    }
    public User toEntity(UserDto userDto){
        return User.builder()
                .userId(userId)
                .name(name)
                .phone(phone).build();
    }

}
