package com.ashu.octopus.service;

import com.ashu.octopus.entity.Dish;
import com.ashu.octopus.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> fetchDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findDishById(Long dishId) {
        return dishRepository.findById(dishId).get();
    }

    @Override
    public void deleteDish(Long dishId) {
        dishRepository.deleteById(dishId);
    }

    @Override
    public void updateDish(Long dishId, Dish dish) {

    }

    @Override
    public Dish findDishByName(String dishName) {
        return dishRepository.findByDishNameIgnoreCase(dishName);
    }

    @Override
    public List<Dish> fetchFavorites() {
        return dishRepository.findAll();
    }

//    @Override
//    public List<Dish> fetchDishList() {
//        return dishRepository.findAll();
//    }
}
