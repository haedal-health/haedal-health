package com.haedal.service;

import com.haedal.entity.User;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DisplayName("유저서비스테스트")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @DisplayName("sign 서비스 테스트")
    @Test
    void SignTest(){
        //given
        User request = newUser(1L);
        Mockito.when(userRepository.save(request))
                .thenReturn(request);

        //when
        User result = userService.sign(request);

        //then
        assertThat(result)
                .isEqualTo(request);

        Mockito.verify(userRepository).save(request);
    }


    @Test
    @DisplayName("findall 서비스 테스트")
    void findAllTest(){

        //given
        List<User> users = newUsers();
        Mockito.when(userRepository.findAll()).thenReturn(users);

        //when
        List<User> result = userService.findUsers();

        //then
        assertThat(result)
                .isEqualTo(users);
        Mockito.verify(userRepository).findAll();
    }

    @DisplayName("finone 서비스 테스트")
    @Test
    void findOneTest(){

        //given
        User user = newUser(1L);
        long id = 1L;
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when
        User result = userService.findOne(1L);

        //then
        assertThat(result)
                .isEqualTo(user);
        Mockito.verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("modify 테스트")
    void modifyTest(){
        //given
        User user = newUser(1L);
        User changedUser = newUser(2L);
        changedUser.setName("김철수");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when
        User result = userService.modifyOne(1L,user);

        //then
        assertThat(result)
                .isEqualTo(user);
        Mockito.verify(userRepository).findById(1L);



    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest(){
        //given
        User user = newUser(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //when
        String result = userService.deleteOne(1L);

        //then
        System.out.println(result);

    }



    private User newUser(Long id){

        User user = new User();
        user.setUserId(id);
        user.setName("홍길동");
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
