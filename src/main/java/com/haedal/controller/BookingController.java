package com.haedal.controller;

import com.haedal.controller.request.BookingDeleteRequest;
import com.haedal.controller.request.BookingRegisterRequest;
import com.haedal.controller.request.BookingResetRequest;
import com.haedal.controller.request.BookingUpdateRequest;
import com.haedal.model.PassDto;
import com.haedal.model.UserDto;
import com.haedal.model.entity.Booking;
import com.haedal.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping(value = "/kakkoLogin/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @RequestMapping(value="/login")
    public String login(@RequestParam("code") String code) {
        System.out.println("code : " + code);
        return "index";
    }

    @GetMapping("/api/hello")
    public String test(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        for(Cookie c : cookie){
            System.out.println(c.getDomain());
        }
        return "Hello, world!";
    }

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
    @PatchMapping("")
    public List<Booking> resetBooking(@RequestBody BookingResetRequest request) {

        return bookingService.resetBooking(request.getPassId(), request.getUserId());
    }

    //TODO : delete ALL
    @DeleteMapping("/{bookingId}")
    public String deleteBooking(@RequestBody BookingDeleteRequest request) {
        return bookingService.deleteBooking(request.getPassId(), request.getUserId());
    }
}
