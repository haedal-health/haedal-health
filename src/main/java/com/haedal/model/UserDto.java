package com.haedal.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haedal.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //직렬화 속성무시
public class UserDto {

    Long userId;
    String name;
    String phone;
    UserRole role;


    public static UserDto of(Long userId,String name,String phone, UserRole role){
        return new UserDto(userId, name, phone, role);
    }

    public static UserDto from(User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getPhone(),
                user.getRole()
        );
    }
    public User toEntity(UserDto userDto){
        return User.builder()
                .userId(userId)
                .name(name)
                .phone(phone)
                .build();
    }

}
