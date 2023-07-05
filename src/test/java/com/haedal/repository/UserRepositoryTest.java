package com.haedal.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest  // 각각의 모듈만 테스트 할 수 있도록 도움을 준다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserEntity 및 Repository 테스트")
class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//    // userRepository 인스턴스 생성 후 의존성주입?
//    // 이떄 AutoWired가 의존성 주입을 못하는 이유는 모듈들이 결합되어 있기 때문일까?
//    @Test
//    @DisplayName("User 생성 테스트")
//    //유저를 생성한 후 Jpa에서 save() 메소드가 반환하는 객체를 savedUser에 담는다.
//    public void createUserEntityThanReturnsavedUserEntity() {
//        User user = new User();
//        // user.setUserId(1L);
//        user.setName("홍길동");
//        user.setPhone("01012345678");
//        User savedUser = userRepository.save(user);
//
//        assertEquals(savedUser.getUserId(),1L);
//    }
//
//    @Test
//    @DisplayName("User 조회 테스트")
//    public void findUserEntityByName(){
//
//        User user = new User();
//        user.setName("홍길동");
//        user.setPhone("010123456578");
//
//        //User savedUser = userRepository.findByName("홍길동");
//
//
//    }
//
//    @Test
//    @DisplayName("User 조회 테스트")
//    public void findUserEntityById(){
//
//        User user = new User();
//        user.setName("홍길동");
//        user.setPhone("010123456578");
//        User savedUser = userRepository.save(user);
//
//        User findedUser = userRepository.findById(savedUser.getUserId()).orElse(null);
//
//        assertSame(findedUser,user);
//    }
//
//    @Test
//    @DisplayName("User 수정 테스트")
//    public void changedUserEntityc(){
//        User user = new User();
//        user.setName("홍길동");
//        user.setPhone("010123456578");
//        User savedUser = userRepository.save(user);
//
//        savedUser.setName("윤재용");
//        savedUser.setPhone("01099105948");
//
//        User updatedUser = userRepository.save(savedUser);
//
//        assertEquals(updatedUser.getName(),savedUser.getName());
//        assertEquals(updatedUser.getUserId(),savedUser.getUserId());
//        assertEquals(updatedUser.getPhone(),savedUser.getPhone());
//    }
//
//    @Test
//    @DisplayName("User 삭제 테스트 - test")
//    public void delete(){}
}
