package com.haedal.controller;


import com.haedal.controller.request.UserLoginRequest;
import com.haedal.controller.response.UserLoginResponse;
import com.haedal.model.entity.User;
import com.haedal.model.UserDto;
import com.haedal.repository.UserRepository;
import com.haedal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request){
        String token = userService.login(request.getName(), request.getPassword());

        return new UserLoginResponse(token);
    }


    //사용자 생성페이지 요청

    //사용자 생성 요청
    @PostMapping("/join")
    public UserDto create(@RequestBody User user){
        //TODO : USER DTO-> USER Request

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
