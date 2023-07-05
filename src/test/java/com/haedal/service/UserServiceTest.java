package com.haedal.service;

import com.haedal.model.entity.User;
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
        User user = newUser(1L);				// UserId가 1인 user를 생성
        Mockito.when(userRepository.save(user))  // userService에서 userRepository.save(user)를 사용할때
                .thenReturn(user);   		// request를 return 해라.

        //when  userService.sign 메서드를 실행하고 반환값을 저장.userService.sign()은 저장된 user를 반환하도록 했음.
        User result = userService.sign(user);


        //then 반환된 값이 예상하는 반환값과 동일한지 확인
        assertThat(result)
                .isEqualTo(user);

        Mockito.verify(userRepository).save(user);   // 해당 함수가 실행되었는지 확인

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
        User result = userService.modifyOne(1L,changedUser);

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
