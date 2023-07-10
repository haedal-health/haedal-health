package com.haedal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haedal.config.ObjectMapperConfig;
import com.haedal.model.entity.Booking;
import com.haedal.service.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookingController.class)        // WebMvc Test Annotation ( UserApiController 를 테스트)
@AutoConfigureMockMvc                    // MockMvc 자동 설정 Annotation
@Import(ObjectMapperConfig.class)
class BookingControllerTest {
    private final MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;
    @Autowired
    private ObjectMapper objectMapper;

    public BookingControllerTest(@Autowired MockMvc mockMvc) throws Exception {
        this.mockMvc = mockMvc;
    }
    @Test
    @DisplayName("생성 테스트")
    public void createTest() throws Exception {
        // given
        Booking book = createBooking();
        // when
        mockMvc.perform(
                        post("/booking")  // post 로 테스트
                                .content(objectMapper.writeValueAsString(book))
                                .contentType(MediaType.APPLICATION_JSON)
                )                // then
                .andExpect(status().isOk())
                .andDo(print());
        then(bookingService).should().create(any(Booking.class));
    }
    @Test
    @DisplayName("GET - 단일 조회")
    public void giveBookingIdandReturnBooking() throws Exception {
        //given
        Long Id = 1L;
        given(bookingService.getBooking(Id)).willReturn(createBooking());

        //when&then
        mockMvc.perform(get("/booking/"+Id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(bookingService).should().getBooking(Id);
    }
    @Test
    @DisplayName("GET - 전체 조회 : pass, userId 주어질때")
    public void givenIdandReturnBookingAll() throws Exception {
        //given
        given(bookingService.getAllbyPassAndUser(1L, 1L)).willReturn(createBookingList());

        //when&then
        mockMvc.perform(get("/booking?pass=1&user=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(bookingService).should().getAllbyPassAndUser(1L, 1L);
    }
    @Test
    @DisplayName("GET - 전체 조회 - Id없이 모든 예약화면")
    public void giveNothingandReturnBookingAll() throws Exception {
        //given
        given(bookingService.getAllbyPassAndUser(null,null)).willReturn(createBookingList());

        //when&then
        mockMvc.perform(get("/booking"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(bookingService).should().getAllbyPassAndUser(null, null);
    }
    @Test
    @DisplayName("GET - 전체 조회 - passId만 줄 때 모든 예약화면")
    public void givePassandReturnBookingAll() throws Exception {
        //given
        given(bookingService.getAllbyPassAndUser(1L,null)).willReturn(createBookingList());

        //when&then
        mockMvc.perform(get("/booking?pass=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(bookingService).should().getAllbyPassAndUser(1L, null);
    }
    @Test
    @DisplayName("GET - 전체 조회 - userId만 줄떄 모든 예약화면")
    public void giveUserandReturnBookingAll() throws Exception {
        //given
        given(bookingService.getAllbyPassAndUser(null,1L)).willReturn(createBookingList());

        //when&then
        mockMvc.perform(get("/booking?user=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        then(bookingService).should().getAllbyPassAndUser(null, 1L);
    }
    @Test
    @DisplayName("Patch - 수정")
    public void giveBookingUpdatedReturnUpdated() throws Exception {
        //given
        Booking updated = createBooking();
        given(bookingService.updateBooking(1L, updated)).willReturn(updated);

        //when&then
        mockMvc.perform(patch("/booking/1")
                    .content(objectMapper.writeValueAsString(updated))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        //then(bookingService).should().updateBooking(1L, updated);
    }

    @Test
    @DisplayName("DELETE - 삭제 ")
    public void giveBookingReturnStringDeleted() throws Exception {
        //given
        Booking deleted = createBooking();
        given(bookingService.deleteBooking(1L)).willReturn(deleted.getBookingId()+"이 삭제되었습니다.");

        //when&then
        mockMvc.perform(delete("/booking/1"))
                .andExpect(status().isOk());

        then(bookingService).should().deleteBooking(1L);
    }

    private Booking createBooking() {
        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setStartTime(LocalDateTime.now().minusHours(1));
        booking.setEndedTime(LocalDateTime.now());
        booking.setUserId(1L);
        booking.setPassId(1L);
        return booking;
    }


    private List<Booking> createBookingList() {
        List<Booking> bookings = new ArrayList<>();
        for(int i=1; i<11; i++) {
//            Booking booking = new Booking();
//            booking.setBookingId(Integer.toUnsignedLong(i));
//            booking.setStartTime(LocalDateTime.now().minusHours(1));
//            booking.setEndedTime(LocalDateTime.now());
//            booking.setUserId(1L);
//            booking.setPassId(1L);
//            bookings.add(booking);
        }
        return bookings;
    }
}