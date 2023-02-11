
package com.haedal.entity;

import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Autowired
    private UserRepository userRepository;
    // userRepository 인스턴스 생성 후 의존성주입?
    // 이떄 AutoWired가 의존성 주입을 못하는 이유는 모듈들이 결합되어 있기 때문일까?

    @Test
    //유저를 생성한 후 Jpa에서 save() 메소드가 반환하는 객체를 savedUser에 담는다.
    public void createUserEntityThanReturnsavedUserEntity(){
        User user = new User();
        user.setUserId(1L);
        user.setName("홍길동");
        user.setPhone("01012345678");

        User savedUser = userRepository.save(user);
    }

}
