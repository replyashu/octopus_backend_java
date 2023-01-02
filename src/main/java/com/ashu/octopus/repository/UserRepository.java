package com.ashu.octopus.repository;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
