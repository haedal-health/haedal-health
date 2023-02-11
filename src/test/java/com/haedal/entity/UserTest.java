
package com.haedal.entity;

import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // 각각의 모듈만 테스트 할 수 있도록 도움을 준다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserEntity 및 Repository 테스트")
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    // userRepository 인스턴스 생성 후 의존성주입?
    // 이떄 AutoWired가 의존성 주입을 못하는 이유는 모듈들이 결합되어 있기 때문일까?
    @Test
    @DisplayName("User 생성 테스트")
    //유저를 생성한 후 Jpa에서 save() 메소드가 반환하는 객체를 savedUser에 담는다.
    public void createUserEntityThanReturnsavedUserEntity() {
        User user = new User();
        user.setUserId(1L);
        user.setName("홍길동");
        user.setPhone("01012345678");
        User savedUser = userRepository.save(user);

        assertEquals(savedUser.getUserId(),1L);
    }
}
