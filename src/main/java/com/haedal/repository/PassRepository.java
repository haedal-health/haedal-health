package com.haedal.repository;

import com.haedal.entity.Booking;
import com.haedal.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository  extends JpaRepository<Pass, Long> {

}
