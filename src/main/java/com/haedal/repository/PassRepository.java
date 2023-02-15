package com.haedal.repository;

import com.haedal.entity.Booking;
import com.haedal.entity.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PassRepository extends JpaRepository<Pass, Long> {

    public Optional<Pass> findByName(String name);

    @Query("SELECT p FROM Pass p where p.passId IN (:passIds) ")
    Optional<List<Pass>> findAllByPassIds(@Param("passIds") List<Long> passIds);

}
