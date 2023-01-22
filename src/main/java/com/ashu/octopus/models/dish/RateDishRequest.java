package com.ashu.octopus.models.dish;

public class RateDishRequest {

    public Long dishId;
    public float dishRating;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public float getDishRating() {
        return dishRating;
    }

    public void setDishRating(float dishRating) {
        this.dishRating = dishRating;
    }
}
