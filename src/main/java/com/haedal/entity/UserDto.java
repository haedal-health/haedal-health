package com.haedal.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserDto {
    public Long userId;
    public String name;
    public String phone;
    public UserDto(Long userId,String name,String phone){
        this.userId = userId;
        this.name =name;
        this.phone = phone;

    }
    public static UserDto from(User user) {
        return new UserDto(user.getUserId(),user.getName(),user.getPhone());
    }
    public static User toEntity(UserDto userDto){
        return new User(userDto.getUserId(),userDto.getName(),userDto.getPhone());
    }

}
