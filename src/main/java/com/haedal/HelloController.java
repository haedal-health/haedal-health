package com.haedal;

import com.haedal.model.entity.Pass;
import com.haedal.repository.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("")
public class HelloController {

    @Autowired
    private PassRepository passRepository;

    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView model) {
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);

        model.addObject("hello", "Test화면입니다.");
        model.setViewName("hello");

        return model;
    }
    @GetMapping("/main")
    public ModelAndView login(ModelAndView model){

        model.addObject("login", "로그인이 필요합니다.");
        model.setViewName("main/login");
        return model;
    }

    @GetMapping("/login")
    public ModelAndView loginPost(ModelAndView model){
        //kakao Controller호출//

        model.addObject("login", "카카오 로그인 페이지로 들어갑니다.");
        //model.setViewName("login/success");

        return model;
    }
}
