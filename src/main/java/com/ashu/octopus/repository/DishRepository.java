package com.ashu.octopus.repository;

import com.ashu.octopus.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    public Dish findByDishNameIgnoreCase(String dishName);
//
//    @Query(value = "select dish_id from favorite_dish where user_id = :userId")
//    public List<String> findDishesForUser(String userId);

}
