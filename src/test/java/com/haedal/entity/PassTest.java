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
import java.util.List;
import java.util.Optional;

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
        passRepository.findByName("해달헬스장 1일 이용권");
        assertEquals(savedPass.getPassId(), 1L);
    }

    @Test
    @DisplayName("Pass 이름으로 조회 테스트")
    public void detailTest_findByName_returnPassEntity(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);
        Pass findPass = passRepository.findByName(savedPass.getName()).orElse(null);
        assertEquals(findPass.getPassId(), savedPass.getPassId());
    }
    @Test
    @DisplayName("Pass ID로 조회 테스트")
    public void detailTest_findById_returnPassEntity(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass savedPass = passRepository.save(pass);
        Pass findPass = passRepository.findById(1L).orElse(null);
        assertEquals(findPass.getName(), savedPass.getName());
        //assertEquals(findPass, pass);
        //객체끼리 비교하면 안되는 걸까?

    }
    @Test
    @DisplayName("모든 Pass 조회 테스트")
    public void allReadTest_findByAll_returnallPassEntity(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);

        Pass pass2 = new Pass();
        pass2.setName("해달헬스장 3일 이용권");
        pass2.setPrice(27000);
        pass2.setCount(3);
        pass2.setStartedDay(LocalDateTime.now().minusDays(1));
        pass2.setEndedDay(LocalDateTime.now().plusDays(5));
        Pass savedPass2 = passRepository.save(pass2);

        List<Pass> passes = passRepository.findAll();
        assertEquals(passes.size(),2);
    }


    @Test
    @DisplayName("Pass 수정 테스트")
    public void update(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);

        Optional<Pass> findPass = passRepository.findById(1L);
        findPass.ifPresent( pass2 -> {
            pass2.setName("해달헬스장 3일 이용권");
            pass2.setPrice(3);
            Pass updatedPass = passRepository.save(pass2);
            System.out.println("업데이트 성공 " + updatedPass.getName());
            assertEquals(updatedPass.getName(), pass2.getName());
            assertEquals(updatedPass.getPassId(), pass2.getPassId());
        } );

         //이미 ID가 있기 떄문에 해당 entity가 수정된다.
    }

    @Test
    @DisplayName("Pass 삭제 테스트")
    public void delete(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);

        Optional<Pass> findPass = passRepository.findById(1L);
        assertTrue(findPass.isPresent());    // true

        findPass.ifPresent( pass2 -> {
            passRepository.delete(pass2);
        } );
        Optional<Pass> deletePass = passRepository.findById(1L);
        assertFalse(deletePass.isPresent());
    }
}