package com.ashu.octopus.repository;

import com.ashu.octopus.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    public Dish findByDishNameIgnoreCase(String dishName);

}
