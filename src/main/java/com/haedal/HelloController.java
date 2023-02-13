package com.haedal;

import com.haedal.entity.Pass;
import com.haedal.repository.PassRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private PassRepository passRepository;

    @GetMapping("")
    public void hello() {
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);
        Pass findPass = passRepository.findById(1L).orElse(null);

        System.out.println(pass == findPass);

        //== 을 객체에 사용하면 assertSame처럼 주소를 비교하게 된다.
    }
}
