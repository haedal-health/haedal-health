package com.haedal.controller;


import com.haedal.entity.User;
import com.haedal.entity.UserDto;
import com.haedal.repository.UserRepository;
import com.haedal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController // 추후 변경 예정
public class UserController {

    private UserService userService ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    //사용자 생성페이지 요청

    //사용자 생성 요청
    @PostMapping("/user")
    public UserDto create(@RequestBody UserDto userDto){
        User user = UserDto.toEntity(userDto);
        User pullUser  = userService.sign(user);

        return UserDto.from(pullUser);
    }



    //사용자 전체 조회 페이지 요청
    @GetMapping("/user")
    public List list(){

        List<User> users = userService.findUsers();
        List<UserDto> userDtos = users.stream()
                .map(u -> UserDto.from(u))
                .collect(Collectors.toList());


        return userDtos;
    }

    @GetMapping("/user/{id}")
    public UserDto findUser(@PathVariable(value = "id")long id){

        User user = userService.findOne(id);

        return UserDto.from(user);

    }

    @PatchMapping("/user/{id}")
    public UserDto modify(@PathVariable(value = "id")long id, @RequestBody User user){
        User changedUser = userService.modifyOne(id,user);

        return UserDto.from(changedUser);
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable(value = "id")long id)
    {
        return userService.deleteOne(id);
    }

}
