package com.ashu.octopus.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.patterns.Declare;

import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;

    private String dishName;

    private String dishUrl;

    private String dishType;

    private String dishDescription;

    private double dishRating;

    @ManyToMany(mappedBy = "favoriteDishes")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public double getDishRating() {
        return dishRating;
    }

    public void setDishRating(double dishRating) {
        this.dishRating = dishRating;
    }

    public Dish(Long dishId, String dishName, String dishUrl, String dishType, String dishDescription, double dishRating) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishUrl = dishUrl;
        this.dishType = dishType;
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
                ", dishtype='" + dishType + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishRating='" + dishRating + '\'' +
                '}';
    }
}
