package com.haedal.controller;

import com.haedal.model.PassDto;
import com.haedal.model.UserDto;
import com.haedal.model.entity.Booking;
import com.haedal.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("")
    public Booking userRegist(@RequestBody PassDto pass, UserDto user){
        //Todo : pass의 count수만큼 임시 예약 생성

        Booking saved = new Booking();// bookingService.create(pass, user);
        return saved;
    }
    @GetMapping("/{bookingId}")
    public Booking detailBooking(@PathVariable Long bookingId){
        Booking booking = bookingService.getBooking(bookingId);
        return booking;
    }
    @GetMapping("")
    public List<Booking> listBooking(@RequestParam(value = "pass", required = false) Long passId, @RequestParam(value = "user", required = false) Long userId){
        return bookingService.getAllbyPassAndUser(passId, userId);
    }
    @PatchMapping("/{bookingId}")
    public Booking modifyBooking(@PathVariable Long bookingId, @RequestBody  Booking booking) {
        return bookingService.updateBooking(bookingId, booking);
    }
    @DeleteMapping("/{bookingId}")
    public String resetBooking(@PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }
}
