package com.haedal;


import com.haedal.entity.User;
import com.haedal.repository.UserRepository;
import com.haedal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PostMapping("user")
    public String create(User user){
        userService.sign(user);
        return "처음페이지로";
    }



    //사용자 전체 조회 페이지 요청
    @GetMapping("user")
    public List list(){


        List<User> users = userService.findUsers();

        return users;
    }

    @GetMapping("user/{id}")
    public Optional<User> findUser(@PathVariable(value = "id")long id){
        return userService.findOne(id);
    }

    @PatchMapping("user/{id}")
    public Optional<User> modify(@PathVariable(value = "id")long id, User user){
        return userService.modifyOne(id,user);
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable(value = "id")long id)
    {
        return userService.deleteOne(id);
    }

}
