package com.ashu.octopus.models.dish;

public class RateDishResponse {

    public Long totalRating;

    public double dishRating;

    public RateDishResponse(long totalRatings, double dishRating) {
        this.totalRating = totalRatings;
        this.dishRating = dishRating;
    }

    public Long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Long totalRating) {
        this.totalRating = totalRating;
    }

    public double getDishRating() {
        return dishRating;
    }

    public void setDishRating(double dishRating) {
        this.dishRating = dishRating;
    }
}
