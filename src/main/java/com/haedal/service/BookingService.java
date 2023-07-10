package com.haedal.service;

import com.haedal.model.entity.Booking;
import com.haedal.model.entity.Pass;
import com.haedal.repository.BookingRepository;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PassRepository passRepository;

    //TODO : delete create()
    public Booking create(Booking booking) {
        Booking saved = bookingRepository.save(booking);
        return saved;
    }

    public List<Booking> registBooking(Long passId, Long userId) {
        //TODO : User가 실제 있는 유저인지
        //TODO : Pass가 실제 있는 이용권인지
        ArrayList<Booking> bookings = new ArrayList<>();
        Pass pass = passRepository.findById(passId).orElseThrow(()->
                new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        for(int i=0; i<pass.getCount(); i++){
            Booking saved = bookingRepository.save(new Booking(userId, passId));
            bookings.add(saved);
        }
        return bookings.stream().toList();

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
