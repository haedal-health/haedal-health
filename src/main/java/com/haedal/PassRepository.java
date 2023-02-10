package com.haedal;

import com.haedal.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository  extends JpaRepository<Booking, Long> {

}
