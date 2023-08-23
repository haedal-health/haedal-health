package com.haedal.service;

import com.haedal.model.UserDto;
import com.haedal.model.entity.User;
import com.haedal.repository.UserRepository;
import com.haedal.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    /*
         회원가입
         */
    @Transactional
    public User sign(User user){
        checkSameNameUser(user); // 같은 이름의 중복 유저 검증
        User savedUser = userRepository.save(new User(user.getName(), encoder.encode(user.getPassword()), user.getPhone(), user.getRole()));
        return savedUser;
    }
    public void checkSameNameUser(User user) {
        // 같은 이름이 있는 중복 회원X
        userRepository.findByName(user.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
    전체 유저 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long memberId) {

        return userRepository.findById(memberId).orElse(null);
    }

    @Transactional
    public User modifyOne(Long memberId,User user) {
        User originUser = userRepository.findById(memberId).orElse(null);

        originUser.setName(user.getName());
        originUser.setPhone(user.getPhone());
        originUser.setRole(user.getRole());
        return originUser;
    }
    @Transactional
    public String deleteOne(Long id) {
        User user = userRepository.findById(id).orElse(null);
        String userName = user.getName();
        userRepository.delete(user);

        String answer = userName+"이 삭제되었습니다.";
        return answer;
    }

    public String login(String name, String password) {
        //name 존재 체크
        User user = userRepository.findByName(name).orElseThrow(()->
             new IllegalStateException(String.format("존재하지 않는 name : %s 입니다.", name)));

        //비밀번호 체크
        if(!encoder.matches(password, user.getPassword())){
            //암호화된 비밀번호로 저장하기 때문에 암호화된 pass와 일치하는지 검사가 필요하다
            //==> !userEntity.getPassword().equals(password))
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        //토큰 생성
        String token = JwtTokenUtils.generateToken(name, secretKey, expiredTimeMs);
        //토큰 반환
        return token;
    }

    public UserDto getUserbyUserName(String userName) {
        return userRepository.findByName(userName).map(UserDto::from).orElse(null);
    }
}
