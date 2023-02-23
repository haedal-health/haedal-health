package com.haedal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haedal.config.ObjectMapperConfig;
import com.haedal.entity.Pass;


import com.haedal.entity.PassDto;
import com.haedal.service.PassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.net.URI;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PassController.class)        // WebMvc Test Annotation ( UserApiController 를 테스트)
@AutoConfigureMockMvc                       // MockMvc 자동 설정 Annotation
@Import(ObjectMapperConfig.class)
class PassControllerTest {
    private final MockMvc mockMvc;

    @MockBean private PassService passService;
    @Autowired
    private ObjectMapper objectMapper;

    public PassControllerTest(@Autowired MockMvc mockMvc) throws Exception {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("pass 생성 테스트")
    public void createTest() throws Exception {
        // given
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());

        PassDto request = PassDto.from(pass);
        // when
        mockMvc.perform(
                        post("/pass")  // post 로 테스트
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )                // then
                .andExpect(status().isOk())
                .andDo(print());
        then(passService).should().create(any(Pass.class));
    }
    @Test
    @DisplayName("GET - 단일 조회")
    public void givePassIdandReturnPass() throws Exception {
        //given
        Long passId = 1L;
        given(passService.getPass(passId)).willReturn(createPass());

        //when&then
        mockMvc.perform(get("/pass/"+passId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(passService).should().getPass(passId);
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

    private PassDto createPassDto() {
        return PassDto.of(
                1L,
                "해달헬스장 1일 이용권",
                9000,
                1,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );
    }
}