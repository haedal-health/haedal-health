package com.haedal.repository;


import com.haedal.UserForm;
import com.haedal.entity.User;
import com.haedal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 추후 변경 예정
public class UserController {

    private UserService userService ;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //사용자 생성페이지 요청
    @GetMapping("")
    public User createForm(){
        User user = new User();
        user.setName("윤재용");
        user.setPhone("01099105948");
        return user;
    }

    //사용자 생성 요청
    @PostMapping("")
    public String create(UserForm form){
        User user = new User();
        user.setName(form.getName());
        user.setPhone(form.getPhone());

        userService.join(user);
        return "처음페이지로";
    }



    //사용자 전체 조회 페이지 요청
    @GetMapping("1")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users",users);
        return "리스트 페이지로";
    }



    //사용자 수정 페이지 요청
    //사용자 수정


    //사용자 삭제
}
