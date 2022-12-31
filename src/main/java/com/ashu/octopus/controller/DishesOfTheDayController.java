package com.ashu.octopus.controller;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DishesOfTheDayController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dish")
    public List<Dish> fetchDishList() {
        return dishService.fetchDishes();
    }

    @PostMapping("/dish/save")
    public Dish saveDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @GetMapping("/dish/{dishName}")
    public Dish findDishByName(@PathVariable String dishName) {
        return dishService.findDishByName(dishName);
    }
}
