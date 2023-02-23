package com.haedal.entity;

import com.haedal.repository.BookingRepository;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("PassEntity 매핑관계 테스트")
public class PassTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassRepository passRepository;

    @Test
    @DisplayName("해당 pass를 이용하는 user목록조회")
    void findAllUserByPassId() {

        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(2);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);

        for(int i=0; i<2; i++) {
            User user = new User();
            user.setName("홍길동"+i);
            user.setPhone("01012345678");
            User savedUser = userRepository.save(user);

            Booking booking = new Booking();
            booking.setStartTime(LocalDateTime.now().plusHours(1));
            booking.setEndedTime(LocalDateTime.now().plusHours(3));
            booking.setUserId(savedUser.getUserId());
            booking.setPassId(savedPass.getPassId());
            bookingRepository.save(booking);

            Booking booking2 = new Booking();
            booking2.setStartTime(LocalDateTime.now().plusHours(2));
            booking2.setEndedTime(LocalDateTime.now().plusHours(4));
            booking2.setUserId(savedUser.getUserId());
            booking2.setPassId(savedPass.getPassId());
            bookingRepository.save(booking2);
        }

     //   select * from user where user.user_id in
     //   (select booking.user_id from pass join booking where booking.pass_id = pass.pass_id and pass.pass_id=1);
        List<Long> userIds = bookingRepository.findAllUserIdByPassId(savedPass.getPassId());
        if(userIds!=null){
            for(Long user : userIds){
                System.out.println(user);
            }
        }
        List<User> useres = userRepository.findAllByUserIds(userIds).orElse(null);

        if(useres!=null){
            for(User u : useres){
                System.out.println(u.getName());
            }
            assertEquals(useres.size(), 2);
        }

    }
}