
package com.haedal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.haedal.entity.User;
import com.haedal.entity.UserDto;
import com.haedal.repository.UserRepository;
import com.haedal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    public UserControllerTest(@Autowired MockMvc mockMvc) throws Exception {
        this.mockMvc = mockMvc;
    }


    /*
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

     */


    @DisplayName("?????? ?????? ??????")
    @Test // ??????
    public void userCreateTest() throws Exception {
        //UserDto??? ????????? UserDto??? ????????????.
        //userService.sign(user)??? ???????????? ????????? ????????????.
        //given
        UserDto userDto = newUserDto(1L); //post???????????? ????????? ???

        Mockito.when(userService.sign(any(User.class))).thenReturn(UserDto.toEntity(userDto));

        //when
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(userDto))
                //then
        ).andExpect(jsonPath("userId").value(1L))
                .andExpect(jsonPath("name").value("?????????"))
                .andExpect(jsonPath("phone").value("01012345678"));

    }

    @DisplayName("?????? ?????? ??????")
    @Test //
    public void userListTest() throws Exception{

        //given
        UserDto userDto = newUserDto(1L);
        String json = "[{\"userId\":1,\"name\":\"?????????\",\"phone\":\"01012345678\"}]";
        Mockito.when(userService.findUsers()).thenReturn(newUsers());


        //when
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @DisplayName("?????? ?????? id ??????")
    @Test
    public void userFindTest () throws Exception {

        //given
        Mockito.when(userService.findOne(1L)).thenReturn(newUser(1L));

        //when
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                ) //then
                .andExpect(jsonPath("userId").value(1L))
                .andExpect(jsonPath("name").value("?????????"))
                .andExpect(jsonPath("phone").value("01012345678"));

    }
    @DisplayName("?????? ?????? id ??????")
    @Test
    public void userModifyTest() throws Exception {

        User user = newUser(1L);
        User user1 = user;
        user1.setName("?????????");
        user1.setPhone("01011112222");
        //given
        Mockito.when(userService.modifyOne(eq(1L),any(User.class))).thenReturn(user1);

        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/user/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user1))
                )

                .andExpect(jsonPath("userId").value(1L))
                .andExpect(jsonPath("name").value("?????????"))
                .andExpect(jsonPath("phone").value("01011112222"));


    }

    @DisplayName("?????? ?????? id ??????")
    @Test
    public void userDeleteTest() throws Exception {
        Mockito.when(userService.deleteOne(1L)).thenReturn("???????????? ?????????????????????.");

        //when
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/user/1")
                                .contentType(MediaType.APPLICATION_JSON)
                );//then
                // .andExpect(content().string("???????????? ?????????????????????."));
                // ?????? ?????? ???????????? ????????? ????????? @WebMvcTest + addFilters(new CharacterEncodingFilter("UTF-8", true))  ??? ?????????


    }


    private UserDto newUserDto(Long id){

        UserDto userDto = new UserDto(id,"?????????","01012345678");

        return userDto;
    }

    private User newUser(Long id){

        User user = new User();
        user.setUserId(id);
        user.setName("?????????");
        user.setPhone("01012345678");
        return user;
    }

    private List<User> newUsers(){
        List<User> users = new ArrayList<>();
        for(int i = 0; i< 10; i++)
        {
            users.add(newUser(i+1L));
        }
        return users;
     }

}