package com.ashu.octopus.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    private String userId;

    private String name;

    private String user_phone;

    private String imageUrl;

    private String email;

    private boolean isSubscribed;

    private String modeOfRegister;

    private boolean isGuestUser;

    @OneToMany
    @JoinTable(
            name = "favorite_dish",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "dishId")

    )
    private Set<Dish> favoriteDishes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Dish> getFavoriteDishes() {
        return favoriteDishes;
    }

    public void setFavoriteDishes(Set<Dish> favoriteDishes) {
        this.favoriteDishes = favoriteDishes;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public String getModeOfRegister() {
        return modeOfRegister;
    }

    public void setModeOfRegister(String modeOfRegister) {
        this.modeOfRegister = modeOfRegister;
    }

    public boolean isGuestUser() {
        return isGuestUser;
    }

    public void setGuestUser(boolean guestUser) {
        isGuestUser = guestUser;
    }

    public User(String userId, String name, String user_phone, String imageUrl, String email,
                boolean isSubscribed, String modeOfRegister, boolean isGuestUser, Set<Dish> favoriteDishes) {
        this.userId = userId;
        this.name = name;
        this.user_phone = user_phone;
        this.imageUrl = imageUrl;
        this.email = email;
        this.isSubscribed = isSubscribed;
        this.modeOfRegister = modeOfRegister;
        this.isGuestUser = isGuestUser;
        this.favoriteDishes = favoriteDishes;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", modeOfRegister='" + modeOfRegister + '\'' +
                ", isGuestUser=" + isGuestUser +
                ", favoriteDishes=" + favoriteDishes +
                '}';
    }
}
