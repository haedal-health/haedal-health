package com.haedal.entity;


import com.haedal.repository.BookingRepository;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserEntity 매핑 테스트")
public class UserTest {
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PassRepository passRepository;
//
//
//    @Test
//    @DisplayName("user 한 명이 가진 모든 pass 조회하기(userId) - 여러개")
//    void findAllPassfromOneUser_test2(){
//        User user = new User();
//        user.setName("홍길동");
//        user.setPhone("01012345678");
//        User savedUser = userRepository.save(user);
//
//        for(int i=0; i<3; i++) {
//            Pass pass = new Pass();
//            pass.setName("해달헬스장 1일 이용권" + (i+1) + "번째");
//            pass.setPrice(9000);
//            pass.setCount(1);
//            pass.setStartedDay(LocalDateTime.now().minusDays(1));
//            pass.setEndedDay(LocalDateTime.now());
//            Pass savedPass = passRepository.save(pass);
//
//            Booking booking = new Booking();
//            booking.setStartTime(LocalDateTime.now().plusHours(1));
//            booking.setEndedTime(LocalDateTime.now().plusHours(3));
//            booking.setUserId(savedUser.getUserId());
//            booking.setPassId(savedPass.getPassId());
//            bookingRepository.save(booking);
//
//        }
//        Booking booking = new Booking();
//        booking.setStartTime(LocalDateTime.now().plusHours(1));
//        booking.setEndedTime(LocalDateTime.now().plusHours(3));
//        booking.setUserId(savedUser.getUserId());
//        booking.setPassId(1L);
//        bookingRepository.save(booking);
//
//        List<Booking> bookings = bookingRepository.findAll();
//        assertEquals(bookings.size(), 4);
//
//
//        List<Long> passIds = bookingRepository.findAllPassIdByUserId(savedUser.getUserId());
//        if(passIds!=null){
//            for(Long pass : passIds){
//                System.out.println(pass);
//            }
//        }
//        List<Pass> passes = passRepository.findAllByPassIds(passIds).orElse(null);
//
//        if(passes!=null){
//            for(Pass pass : passes){
//                System.out.println(pass.getName());
//            }
//            assertEquals(passes.size(), 3);
//        }
//
//}
}
