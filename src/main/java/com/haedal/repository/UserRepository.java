package com.haedal.repository;
import com.haedal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);


    User findByPhone(String phone);



}
