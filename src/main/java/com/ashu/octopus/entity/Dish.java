package com.ashu.octopus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;

    private String dishName;

    private String dishUrl;

    private String dishtype;

    private String dishDescription;

    private String dishRating;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishUrl() {
        return dishUrl;
    }

    public void setDishUrl(String dishUrl) {
        this.dishUrl = dishUrl;
    }

    public String getDishtype() {
        return dishtype;
    }

    public void setDishtype(String dishtype) {
        this.dishtype = dishtype;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishRating() {
        return dishRating;
    }

    public void setDishRating(String dishRating) {
        this.dishRating = dishRating;
    }

    public Dish(Long dishId, String dishName, String dishUrl, String dishtype, String dishDescription, String dishRating) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishUrl = dishUrl;
        this.dishtype = dishtype;
        this.dishDescription = dishDescription;
        this.dishRating = dishRating;
    }

    public Dish() {
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishUrl='" + dishUrl + '\'' +
                ", dishtype='" + dishtype + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishRating='" + dishRating + '\'' +
                '}';
    }
}
