package com.haedal.repository;


import com.haedal.entity.Booking;
import com.haedal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Optional<Booking> findByUserId(Long userId);
    Optional<List<Booking>> findAllByUserId(Long userId);
}
