/*
package com.haedal.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("유저 생성")
    @Test // 생성
    public void userCreateTest(){

    }

    @DisplayName("유저 전체 조회")
    @Test //
    public void userListTest() throws Exception{

        String json = "[{\"userId\":1,\"name\":\"윤재용\",\"phone\":\"01099105948\"}]";

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .andExpect(MockMvcResultMatchers.content().string(json))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("유저 단일 id 조회")
    @Test
    public void userFindTest(){

    }
    @DisplayName("유저 단일 id 수정")
    @Test
    public void userModifyTest(){

    }

    @DisplayName("유저 단일 id 삭제")
    @Test
    public void userDeleteTest(){

    }

}
*/