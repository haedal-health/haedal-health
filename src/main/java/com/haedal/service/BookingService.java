package com.haedal.service;

import com.haedal.model.entity.Booking;
import com.haedal.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking create(Booking booking) {
        Booking saved = bookingRepository.save(booking);
        return saved;
    }
    public Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Booking입니다 - bookingId: " + bookingId));
    }

    public List<Booking> getAllbyPassAndUser(Long passId, Long userId) {
        if (passId == null & userId == null) return bookingRepository.findAll();
        else if(passId==null) return bookingRepository.findAllByUserId(userId);
        else if(userId==null) return bookingRepository.findAllByPassId(passId);
        else {
            return bookingRepository.findAllByPassIdAndUserId(passId, userId);
        }
    }

    public Booking updateBooking(Long bookingId, Booking booking) {
        Booking updated = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Booking입니다 - bookingId: " + bookingId));
        updated.setStartTime(booking.getStartTime());
        updated.setEndedTime(booking.getEndedTime());
        return updated;
    }

    public String deleteBooking(Long bookingId) {
        Booking deleted = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Booking입니다 - bookingId: " + bookingId));
        bookingRepository.delete(deleted);
        String answer = deleted.getBookingId() + "이 삭제되었습니다.";
        return answer;
    }
}
