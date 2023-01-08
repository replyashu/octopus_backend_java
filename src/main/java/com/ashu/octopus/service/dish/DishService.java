package com.ashu.octopus.service.dish;

import com.ashu.octopus.entity.Dish;

import java.util.List;

public interface DishService {

    public Dish saveDish(Dish dish);

    public List<Dish> fetchDishes();

    public Dish findDishById(Long dishId);

    public void deleteDish(Long dishId);

    public void updateDish(Long dishId, Dish dish);

    public Dish findDishByName(String dishName);

    public List<Dish> fetchFavorites();


//    public List<Dish> fetchDishList();
}
