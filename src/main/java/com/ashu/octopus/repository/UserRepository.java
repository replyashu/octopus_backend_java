package com.ashu.octopus.repository;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    @Query(value = "SELECT * FROM user_data u WHERE u.user_id = :userId",
            nativeQuery = true)
    User findUserById(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user_data u WHERE u.email = :email",
            nativeQuery = true)
    User findUserByEmail(@Param("email") String email);
}
