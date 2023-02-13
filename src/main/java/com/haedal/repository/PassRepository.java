package com.haedal.repository;

import com.haedal.entity.Booking;
import com.haedal.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PassRepository extends JpaRepository<Pass, Long> {

    public Optional<Pass> findByName(String name);
}
