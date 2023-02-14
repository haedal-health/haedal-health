package com.haedal.entity;

import com.haedal.entity.Booking;
import com.haedal.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("UserEntity 및 Repository 테스트")
public class BookingTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    @DisplayName("booking 생성 테스트")
    public void createBookingEntityThanReturnsavedBookingEntity(){
        Booking booking = new Booking();

        booking.setStartTime(LocalDateTime.now().plusHours(1));
        booking.setEndedTime(LocalDateTime.now().plusHours(3));

        Booking savedBooking = bookingRepository.save(booking);

    }
    @Test
    @DisplayName("Booking id로 조회 테스트")
    public void detailTest_findByName_returnPassEntity(){
        Booking booking = new Booking();

        //booking.setBookingId(1L);
        booking.setStartTime(LocalDateTime.now().plusHours(1));
        booking.setEndedTime(LocalDateTime.now().plusHours(3));

        Booking savedBooking = bookingRepository.save(booking);


        Booking findedBooking = bookingRepository.findById(savedBooking.getBookingId()).orElse(null);

        assertEquals(findedBooking.getBookingId(), savedBooking.getBookingId());
        assertEquals(findedBooking.getStartTime(), savedBooking.getStartTime());
        assertEquals(findedBooking.getEndedTime(), savedBooking.getEndedTime());
    }

    @Test
    @DisplayName("booking 수정 테스트")
    public void update(){
        Booking booking = new Booking();

        booking.setStartTime(LocalDateTime.now().plusHours(1));
        booking.setEndedTime(LocalDateTime.now().plusHours(3));

        Booking savedBooking = bookingRepository.save(booking);


        Optional<Booking> findBooking = bookingRepository.findById(savedBooking.getBookingId());


        findBooking.ifPresent(booking1 -> {
            booking1.setStartTime(LocalDateTime.now().plusHours(4));
            booking1.setEndedTime(LocalDateTime.now().plusHours(10));
            Booking updatedBooking  = bookingRepository.save(booking1);


            assertEquals(updatedBooking.getBookingId(), booking1.getBookingId());
            assertEquals(updatedBooking.getStartTime(), booking1.getStartTime());
            assertEquals(updatedBooking.getEndedTime(), booking1.getEndedTime());
        });

    }

    @Test
    @DisplayName("booking 삭제 테스트")
    public void delete(){
        Booking booking = new Booking();

        booking.setStartTime(LocalDateTime.now().plusHours(1));
        booking.setEndedTime(LocalDateTime.now().plusHours(3));

        Booking savedBooking = bookingRepository.save(booking);


        Optional<Booking> findBooking = bookingRepository.findById(savedBooking.getBookingId());

        findBooking.ifPresent(booking1 -> {
            bookingRepository.delete(booking1);
        });

        Optional deleteBooking = bookingRepository.findById(1L);
        assertFalse(deleteBooking.isPresent());


    }
}
