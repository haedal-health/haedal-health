package com.haedal.service;

import com.haedal.entity.User;
import com.haedal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /*
         회원가입
         */
    public User sign(User user){
        checkSameNameUser(user); // 같은 이름의 중복 유저 검증
        userRepository.save(user);
        return user;
    }

    private void checkSameNameUser(User user) {
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

    public User modifyOne(Long memberId,User user) {
        User originUser = userRepository.findById(memberId).orElse(null);

        originUser.setName(user.getName());
        originUser.setPhone(user.getPhone());
        return originUser;
    }
    public String deleteOne(Long id) {
       User user = userRepository.findById(id).orElse(null);
        String userName = user.getName();
        userRepository.delete(user);


        String answer = userName+"이 삭제되었습니다.";
        return answer;
    }f
}
