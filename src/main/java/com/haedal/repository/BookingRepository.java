package com.haedal.repository;


import com.haedal.model.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>{

    //Optional<Booking> findByUserId(Long userId);
    List<Booking> findAllByUserId(Long userId);
    List<Booking> findAllByPassId(Long passId);

    Page<Booking> findAllByUserId(Long userId, Pageable pageable);
    Page<Booking> findAllByPassId(Long passId, Pageable pageable);
    Page<Booking> findAllByPassIdAndUserId(Long passId, Long userId,Pageable pageable);

    @Query("select b.passId from Booking b where b.userId=(:userId)")
    List<Long> findAllPassIdByUserId(@Param("userId") Long userId);

    List<Booking> findAllByPassIdAndUserId(Long passId, Long userId);


    @Query("select b.userId from Booking b where b.passId=(:passId)")
    List<Long> findAllUserIdByPassId(@Param("passId") Long passId);

    Booking save(Booking booking);

    Optional<Booking> findById(Long bookingId);

    void delete(Booking booking);
}
