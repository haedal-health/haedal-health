package com.haedal.repository;
import com.haedal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByName(String name);


    User findByPhone(String phone);

    @Query("SELECT u FROM User u where u.userId IN (:userIds) ")
    Optional<List<User>> findAllByUserIds(@Param("userIds") List<Long> userIds);

    User save(User user);

    Optional<User> findById(Long userId);

}
