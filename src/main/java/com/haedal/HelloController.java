package com.haedal;

import com.haedal.entity.Pass;
import com.haedal.repository.PassRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
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
}
