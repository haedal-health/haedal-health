package com.haedal.controller;

import com.haedal.controller.request.BookingRegisterRequest;
import com.haedal.controller.request.BookingUpdateRequest;
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
    public List<Booking> userRegist(@RequestBody BookingRegisterRequest request){

        List<Booking> saved = bookingService.registBooking(request.getPassId(), request.getUserId());// bookingService.create(pass, user);
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
    public Booking modifyBooking(@PathVariable Long bookingId, @RequestBody BookingUpdateRequest request) {
        return bookingService.updateBooking(bookingId, request.getStartTime(), request.getEndedTime(), request.getTeacher());
    }
    //TODO
    @PostMapping("/{bookingId}")
    public String resetBooking(@PathVariable Long bookingId) {

        return "";//bookingService.resetBooking(bookingId);
    }

    //TODO : delete ALL
    @DeleteMapping("/{bookingId}")
    public String deleteBooking(@RequestParam(value = "pass", required = false) Long passId, @RequestParam(value = "user", required = false) Long userId) {
        return "";//bookingService.deleteBooking(passId, userId);
    }
}
