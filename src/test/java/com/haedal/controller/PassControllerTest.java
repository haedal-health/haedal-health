package com.haedal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.haedal.config.ObjectMapperConfig;
import com.haedal.entity.Pass;


import com.haedal.service.PassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PassController.class)        // WebMvc Test Annotation ( UserApiController 를 테스트)
@AutoConfigureMockMvc                       // MockMvc 자동 설정 Annotation
@Import(ObjectMapperConfig.class)
class PassControllerTest {
    private final MockMvc mockMvc;

    @MockBean private PassService articleService;
    @Autowired
    private ObjectMapper objectMapper;

    public PassControllerTest(
            @Autowired MockMvc mvc
    ) {
        this.mockMvc = mvc;
    }

    @Test
    @DisplayName("Pass 생성 테스트")
    public void saveTest() throws Exception
    {
        // given pass 만들고
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());


        // when & then
        //post 호출 시 성공할 것이다.
        mockMvc.perform(post("/pass")
                        .content(objectMapper.writeValueAsString(pass))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("Pass 임시 테스트")
    public void helloTest() throws Exception {
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());


        // when & then
        mockMvc.perform(post("/pass/hello")
                        .content(objectMapper.writeValueAsString(pass))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
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


        // when
        mockMvc.perform(
                post("/pass")  // post 로 테스트
                        .content(objectMapper.writeValueAsString(pass))
                        .contentType(MediaType.APPLICATION_JSON)
                )                // then
                .andExpect(status().isOk())
                .andDo(print());
        then(articleService).should().create(any(Pass.class));
    }
}