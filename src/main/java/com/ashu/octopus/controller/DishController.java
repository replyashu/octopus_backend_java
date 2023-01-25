package com.ashu.octopus.controller;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.entity.User;
import com.ashu.octopus.models.dish.MarkAsFavoriteRequest;
import com.ashu.octopus.models.dish.RateDishRequest;
import com.ashu.octopus.models.dish.RateDishResponse;
import com.ashu.octopus.models.dish.RemoveFavoriteRequest;
import com.ashu.octopus.service.dish.DishService;
import com.ashu.octopus.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/dish/mark_favorite")
    public ResponseEntity<Boolean> markAsFavorite(@RequestBody MarkAsFavoriteRequest markAsFavoriteRequest) {
        User user = userService.findByUserId(markAsFavoriteRequest.getUserUuid());
        Set<Dish> favoriteDishes = user.getFavoriteDishes();
        if (favoriteDishes == null || favoriteDishes.size() == 0) {
            favoriteDishes = new HashSet<>();
        }

        List<Dish> dish = dishService.fetchDishes();

        System.out.println(dish.size());
        System.out.println(markAsFavoriteRequest.getPosition());

        favoriteDishes.add(dish.get(markAsFavoriteRequest.position));
        user.setFavoriteDishes(favoriteDishes);

        if (user.getUserId() != null) {
            userService.saveUser(user);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/dish/get_favorite")
    public ResponseEntity<Set<Dish>> getFavoriteDish(@RequestParam String userId) {
        System.out.println(userId);
        User user = userService.findByUserId(userId);

        System.out.println(user);
        Set<Dish> dishes = user.getFavoriteDishes();

        if (dishes == null || dishes.size() == 0) {
            dishes = new HashSet<>();
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping("/dish/remove_favorite/{dish}")
    @ResponseBody
    public Boolean removeFavoriteDish(@RequestBody RemoveFavoriteRequest removeFavoriteRequest) {
        // Find user
        User user = userService.findByUserId(removeFavoriteRequest.getUserUuid());
        // Find dish
        Set<Dish> dish = user.getFavoriteDishes();
        dish.remove(removeFavoriteRequest.getDish());
        user.setFavoriteDishes(dish);
        userService.saveUser(user);
        return true;
    }
}
