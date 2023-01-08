package com.ashu.octopus.controller;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.models.RateDishRequest;
import com.ashu.octopus.models.RateDishResponse;
import com.ashu.octopus.service.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/dish/rate")
    public ResponseEntity<RateDishResponse> rateDish(@RequestBody RateDishRequest rateDishRequest) {

        Dish dish = dishService.findDishById(rateDishRequest.getDishId());
        long totalRatings = dish.getTotalRatings();
        double dishRating = dish.getDishRating();
        totalRatings += 1;

        dishRating = (dishRating * totalRatings + rateDishRequest.getDishRating()) / (totalRatings + 1);
        System.out.println("rating" + totalRatings);
        dish.setDishRating(dishRating);
        dish.setTotalRatings(totalRatings);
        System.out.println("value" + dish.getTotalRatings());

        dishService.saveDish(dish);
        RateDishResponse response = new RateDishResponse(totalRatings, dishRating);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
