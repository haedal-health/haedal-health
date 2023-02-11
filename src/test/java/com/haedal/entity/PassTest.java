package com.haedal.entity;

import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PassTest {

    @Autowired
    private PassRepository passRepository;

    @Test
    public void createPassEntityThanReturnsavedPassEntity(){
        Pass pass = new Pass();
        pass.setPassId(1L);
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);

    }

}