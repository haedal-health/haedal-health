package com.haedal.entity;

import com.haedal.entity.Booking;
import com.haedal.repository.BookingRepository;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("BookingEntity 매핑관계 테스트")
public class BookingTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassRepository passRepository;

    @Test
    @DisplayName("매핑 생성 테스트")
    void createBookingWithMapping_test(){
        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(1);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);

        User user = new User();
        user.setName("홍길동");
        user.setPhone("01012345678");
        User savedUser = userRepository.save(user);

        Booking booking = new Booking();
        booking.setStartTime(LocalDateTime.now().plusHours(1));
        booking.setEndedTime(LocalDateTime.now().plusHours(3));
        booking.setUserId(savedUser.getUserId());
        booking.setPassId(savedPass.getPassId());
        Booking savedBooking = bookingRepository.save(booking);

    }

    @Test
    @DisplayName("user 한 명이 가진 모든 이용권 조회하기(userId) - 여러개")
    void findAllPassfromOneUser_test2(){
        User user = new User();
        user.setName("홍길동");
        user.setPhone("01012345678");
        User savedUser = userRepository.save(user);

        for(int i=0; i<3; i++) {
            Pass pass = new Pass();
            pass.setName("해달헬스장 1일 이용권" + (i+1) + "번째");
            pass.setPrice(9000);
            pass.setCount(1);
            pass.setStartedDay(LocalDateTime.now().minusDays(1));
            pass.setEndedDay(LocalDateTime.now());
            Pass savedPass = passRepository.save(pass);

            Booking booking = new Booking();
            booking.setStartTime(LocalDateTime.now().plusHours(1));
            booking.setEndedTime(LocalDateTime.now().plusHours(3));
            booking.setUserId(savedUser.getUserId());
            booking.setPassId(savedPass.getPassId());
            bookingRepository.save(booking);

        }
        List<Booking> bookings = bookingRepository.findAllByUserId(savedUser.getUserId()).orElse(null);

        for(Booking b : bookings){
            Pass pass1 = passRepository.findById(b.getPassId()).orElse(null);
            if(pass1 != null){
                System.out.println(pass1.getName());
            }
        }
        assertEquals(bookings.size(), 3);
    }

}
