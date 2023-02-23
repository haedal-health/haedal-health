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
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

}