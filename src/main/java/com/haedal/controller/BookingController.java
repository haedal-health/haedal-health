package com.haedal.controller;

import com.haedal.entity.Booking;
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
    public Booking createBooking(@RequestBody Booking booking){

        Booking saved = bookingService.create(booking);
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
    public Booking modifyBooking(@PathVariable Long bookingId, Booking booking) {
        return bookingService.updateBooking(bookingId, booking);
    }
    @DeleteMapping("/{bookingId}")
    public String deleteBooking(@PathVariable Long bookingId) {
        return bookingService.deleteBooking(bookingId);
    }
}
