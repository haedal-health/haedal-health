package com.haedal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haedal.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //직렬화 속성무시
public class UserDto implements UserDetails {

    Long userId;
    String name;
    String phone;
    String password;
    UserRole role;


    public static UserDto of(Long userId,String name,String phone, String password, UserRole role){
        return new UserDto(userId, name, phone, password, role);
    }

    public static UserDto from(User user) {
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getPhone(),
                user.getPassword(),
                user.getRole()
        );
    }
    public User toEntity(UserDto userDto){
        return User.builder()
                .userId(userId)
                .name(name)
                .phone(phone)
                .password(password)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }


    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
