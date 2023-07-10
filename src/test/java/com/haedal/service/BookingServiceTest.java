package com.haedal.service;

import com.haedal.model.entity.Booking;
import com.haedal.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("서비스 테스트")
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;
    @DisplayName("booking 생성")
    @Test
    void createTest() {
//        Booking book = createBooking();
//        Booking result = createBooking();
//        given(bookingRepository.save(book)).willReturn(result);
//        Booking saved = bookingService.create(book);

//        assertEquals(saved, result);
//        then(bookingRepository).should().save(book);
    }
    @DisplayName("booking id로 조회")
    @Test
    void reateTest() {
        // Given
//        Booking given = createBooking();
//        given(bookingRepository.findById(1L)).willReturn(Optional.of(given));
//        //when then
//        Booking result = bookingService.getBooking(1L);
//
//        then(bookingRepository).should().findById(1L);

        // Then
//        assertThat(result)
//                .hasFieldOrPropertyWithValue("bookingId", given.getBookingId());
//        then(bookingRepository).should().findById(1L);
    }

    @DisplayName("booking 전체 조회가 잘 되는지 서비스 테스트")
    @Test
    void returnAllPassList(){
        //given
        given(bookingRepository.findAllByPassIdAndUserId(1L, 1L)).willReturn(createBookingList());
        //when
        List<Booking> bookings = bookingService.getAllbyPassAndUser(1L, 1L);
        //then
        for(Booking b : bookings){
            System.out.println("테스트 + " + b.getBookingId() + "UserID "+b.getUserId()+"PassId "+b.getPassId());
        }
        assertThat(bookings)
                .hasSize(10)
                .hasOnlyElementsOfType(Booking.class);
        then(bookingRepository).should().findAllByPassIdAndUserId(1L, 1L);
    }
//    @DisplayName("booking 수정 서비스 테스트")
//    @Test
//    void updatePassTest() throws Exception {
        //given
//        Booking given = createBooking();
//        given(bookingRepository.findById(1L)).willReturn(Optional.of(given));
//
//        //when
//        Booking update = createBooking();
//        LocalDateTime time = LocalDateTime.now().plusHours(3);
//        update.setEndedTime(time);
//        Booking result = bookingService.updateBooking(1L, update);
//        //then
//        assertEquals(update.getBookingId(), result.getBookingId());
//        then(bookingRepository).should().findById(given.getBookingId());
//    }
//
//    @DisplayName("booking 삭제 서비스 테스트")
//    @Test
//    void deletePassTest() throws Exception {
//        //given
//        Booking given = createBooking();
//        given(bookingRepository.findById(1L)).willReturn(Optional.of(given));
//        //when
//        String word = bookingService.deleteBooking(1L);
//        //then
//        assertEquals(word, given.getBookingId()+"이 삭제되었습니다.");
//        then(bookingRepository).should().findById(given.getBookingId());
//        then(bookingRepository).should().delete(given);
//    }

//    private Booking createBooking() {
//        Booking booking = new Booking();
//        booking.setBookingId(1L);
//        booking.setStartTime(LocalDateTime.now().minusHours(1));
//        booking.setEndedTime(LocalDateTime.now());
//        booking.setUserId(1L);
//        booking.setPassId(1L);
//        return booking;
//    }


    private List<Booking> createBookingList() {
//        List<Booking> bookings = new ArrayList<>();
//        for(int i=1; i<11; i++) {
//            Booking booking = new Booking();
//            booking.setBookingId(Integer.toUnsignedLong(i));
//            booking.setStartTime(LocalDateTime.now().minusHours(1));
//            booking.setEndedTime(LocalDateTime.now());
//            booking.setUserId(1L);
//            booking.setPassId(1L);
//            bookings.add(booking);
//        }
//        return bookings;
    }
}