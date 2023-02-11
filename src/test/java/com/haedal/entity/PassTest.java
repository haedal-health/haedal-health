package com.haedal.entity;

import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("PassEntity 및 Repository 테스트")
public class PassTest {

    @Autowired
    private PassRepository passRepository;

    @Test
    @DisplayName("Pass 생성 테스트")
    public void createPassEntityThanReturnsavedPassEntity(){
        Pass pass = new Pass();
        //pass.setPassId(1L);
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);
        assertEquals(savedPass.getPassId(), 1L);
    }

    @Test
    @DisplayName("Pass 조회 테스트")
    public void detail(){}

    @Test
    @DisplayName("Pass 수정 테스트")
    public void update(){}

    @Test
    @DisplayName("Pass 삭제 테스트")
    public void delete(){}
}