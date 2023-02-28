package com.haedal.repository;


import com.haedal.entity.Booking;
import com.haedal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Optional<Booking> findByUserId(Long userId);
    List<Booking> findAllByUserId(Long userId);
    List<Booking> findAllByPassId(Long passId);


    @Query("select b.passId from Booking b where b.userId=(:userId)")
    List<Long> findAllPassIdByUserId(@Param("userId") Long userId);

    List<Booking> findAllByPassIdAndUserId(Long passId, Long userId);

    @Query("select b.userId from Booking b where b.passId=(:passId)")
    List<Long> findAllUserIdByPassId(@Param("passId") Long passId);

}
