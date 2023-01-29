package com.ashu.octopus.entity;

import jakarta.persistence.*;
import org.aspectj.weaver.patterns.Declare;
import org.hibernate.type.descriptor.jdbc.LobTypeMappings;

import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dishId;

    private String dishName;

    @Lob
    private byte[] dishUrl;

    private String dishType;

    private String dishDescription;

    private double dishRating = 0;

    private Long totalRatings = 0l;

    private boolean isApproved = false;

    private String userId;

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

    public byte[] getDishUrl() {
        return dishUrl;
    }

    public void setDishUrl(byte[] dishUrl) {
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

    public Long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Long totalRatings) {
        this.totalRatings = totalRatings;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Dish(Long dishId, String dishName, byte[] dishUrl, String dishType, String dishDescription,
                double dishRating, Long totalRatings, boolean isApproved, String userId) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishUrl = dishUrl;
        this.dishType = dishType;
        this.dishDescription = dishDescription;
        this.dishRating = dishRating;
        this.totalRatings = totalRatings;
        this.isApproved = isApproved;
        this.userId = userId;
    }

    public Dish() {
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", dishType='" + dishType + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishRating=" + dishRating +
                ", totalRatings=" + totalRatings +
                ", isApproved=" + isApproved +
                '}';
    }
}
