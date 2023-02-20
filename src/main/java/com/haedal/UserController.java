package com.haedal;


import com.haedal.entity.User;
import com.haedal.repository.UserRepository;
import com.haedal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @GetMapping("")
    public String createForm(){
        /*
        User user = new User();
        user.setName("윤재용");
        user.setPhone("01099105948");

         */

        return "유저 폼으로";
    }

    //사용자 생성 요청
    @PostMapping("")
    public String create(User user){
        userRepository.save(user);
        return "처음페이지로";
    }



    //사용자 전체 조회 페이지 요청
    @GetMapping("all")
    public List list(Model model){
        List<User> users = userService.findUsers();

        return users;
    }



    //사용자 수정 페이지 요청
    //사용자 수정


    //사용자 삭제
}
