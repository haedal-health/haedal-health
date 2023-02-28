package com.haedal.service;

import com.haedal.entity.Pass;
import com.haedal.entity.PassDto;
import com.haedal.repository.PassRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("서비스 테스트")
@ExtendWith(MockitoExtension.class)
class PassServiceTest {
    @InjectMocks
    private PassService passService;

    @Mock private PassRepository passRepository;
    @DisplayName("Pass 를 주면 Pass를 생성해 pass를 리턴한다")
    @Test
    void pass_createTest() {
        // Given
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        Pass pass2 = new Pass();
        pass2.setName("해달헬스장 1일 이용권");
        pass2.setPrice(9000);
        pass2.setCount(1);
        pass2.setStartedDay(LocalDateTime.now().minusDays(1));
        pass2.setEndedDay(LocalDateTime.now());

        //when then
        Pass result = passService.create(pass);
        //  assertThat(result).isNotNull();
        then(passRepository).should().save(pass);

    }
    @DisplayName("ID로 조회하면, pass를 반환한다.")
    @Test
    void givenPassIdreturnPass() {
        // Given
        Long passId = 1L;
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        ReflectionTestUtils.setField(pass, "passId", 1L);
        given(passRepository.findById(passId)).willReturn(Optional.of(pass));

        // When
        Pass pass2 = passService.getPass(passId);

        // Then
        assertThat(pass2)
                .hasFieldOrPropertyWithValue("name", pass.getName());
        then(passRepository).should().findById(passId);
    }

    @DisplayName("pass 전체 조회가 잘 되는지 서비스 테스트")
    @Test
    void returnAllPassList(){
        //given
        given(passRepository.findAll()).willReturn(createPassList());
        //when
        List<PassDto> passDtos = passService.getAll();
        //then
        for(PassDto p : passDtos){
            System.out.println("테스트 + " + p.getId());
        }
        assertThat(passDtos)
                .hasSize(10)
                .hasOnlyElementsOfType(PassDto.class);
        then(passRepository).should().findAll();
    }
    @DisplayName("pass 수정 서비스 테스트")
    @Test
    void updatePassTest() throws Exception {
        //given
        Pass pass = createPass();
        given(passRepository.findById(pass.getPassId())).willReturn(Optional.of(pass));

        //when
        Pass passUpdated = pass;
        passUpdated.setName("수정된 이름");
        Pass updated = passService.updatePass(pass.getPassId(), PassDto.from(passUpdated));
        //then
        assertSame(updated, passUpdated);
        assertEquals(updated.getPassId(), pass.getPassId());
        assertThat(updated)
                .hasFieldOrPropertyWithValue("name",  passUpdated.getName());

        then(passRepository).should().findById(pass.getPassId());
    }


    private Pass createPass() {
        Pass pass = new Pass();
        pass.setPassId(1L);
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        return pass;
    }

    private List<Pass> createPassList() {
        List<Pass> passes = new ArrayList<>();
        for(int i=1; i<11; i++) {
            Pass pass = new Pass();
            pass.setPassId(Integer.toUnsignedLong(i));
            pass.setName("해달헬스장 1일 이용권");
            pass.setPrice(9000);
            pass.setCount(1);
            pass.setStartedDay(LocalDateTime.now().minusDays(1));
            pass.setEndedDay(LocalDateTime.now());
            passes.add(pass);
            System.out.println("테스트 - create : " + pass.getPassId());
        }
        return passes;
    }
}