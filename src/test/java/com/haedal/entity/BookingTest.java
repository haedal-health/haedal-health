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
    @DisplayName("passId, userId로 모든 이용권 조회하기")
    void findBookingByPassIdAndUserId_test(){
        User user = new User();
        user.setName("홍길동");
        user.setPhone("01012345678");
        User savedUser = userRepository.save(user);

        Pass pass = new Pass();
        pass.setName("해달헬스장 1일 이용권");
        pass.setPrice(9000);
        pass.setCount(2);
        pass.setStartedDay(LocalDateTime.now().minusDays(1));
        pass.setEndedDay(LocalDateTime.now());
        Pass savedPass = passRepository.save(pass);


        for(int i=0; i<2; i++) {

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
        List<Booking> bookings = bookingRepository.findAllByPassIdAndUserId(pass.getPassId(), user.getUserId());
        if(bookings!=null){
            for(Booking booking: bookings){
                System.out.println(booking.getPassId());
            }
            assertEquals(bookings.size(),4);
        }

    }

}
