package com.ashu.octopus.controller;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.service.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dish")
    public ResponseEntity<List<Dish>> fetchDishList() {
        List<Dish> dishes = dishService.fetchDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping("/dish/save")
    public Dish saveDish(@RequestBody Dish dish) {
        return dishService.saveDish(dish);
    }

    @GetMapping("/dish/{dishName}")
    public Dish findDishByName(@PathVariable String dishName) {
        return dishService.findDishByName(dishName);
    }

    @DeleteMapping("/dish/{dishName}")
    public void deleteDishByName(@PathVariable String dishName) {
        long dishId = dishService.findDishByName(dishName).getDishId();
        dishService.deleteDish(dishId);
    }
}
