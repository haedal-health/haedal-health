package com.haedal.service;

import com.haedal.model.entity.Booking;
import com.haedal.model.entity.Pass;
import com.haedal.model.entity.User;
import com.haedal.repository.BookingRepository;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PassRepository passRepository;

    public List<Booking> registBooking(Long passId, Long userId) {
        //TODO : //User(Pass)NotFound Error 만들기
        ArrayList<Booking> bookings = new ArrayList<>();
        Pass pass = passRepository.findById(passId).orElseThrow(()->
                new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("존재하지 않는 User입니다 - userId: " + userId));
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

    public Booking updateBooking(Long bookingId, LocalDateTime started, LocalDateTime ended, String teacher) {
        Booking updated = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Booking입니다 - bookingId: " + bookingId));
        updated.setStartTime(started);
        updated.setEndedTime(ended);
        updated.setTeacher(teacher);
        return updated;
    }
    public List<Booking> resetBooking(Long passId, Long userId) {
        List<Booking> bookings = bookingRepository.findAllByPassIdAndUserId(passId, userId);
        for(Booking booking : bookings){
            booking.setStartTime(null);
            booking.setEndedTime(null);
            booking.setTeacher("");
        }
        return bookings;
    }

    public String deleteBooking(Long passId, Long userId) {
        List<Booking> bookings = bookingRepository.findAllByPassIdAndUserId(passId, userId);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bookings.size(); i++) {
            bookingRepository.delete(bookings.get(i));
            sb.append(bookings.get(i).getBookingId()+", ");
        }
        sb.append(" 가 삭제되었습니다");
        return sb.toString();
    }



}
