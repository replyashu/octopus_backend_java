package com.ashu.octopus.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    private String userId;

    private String name;

    private String userPhone;

    private String imageUrl;

    private String email;

    private boolean isSubscribed;

    private String mediumOfRegistration;

    private boolean isGuestUser;

    private String notificationToken;

    @OneToMany
    @JoinTable(
            name = "favorite_dish",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "dishId")

    )
    private Set<Dish> favoriteDishes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish", referencedColumnName = "dishId")
    public Dish dish;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getMediumOfRegistration() {
        return mediumOfRegistration;
    }

    public void setMediumOfRegistration(String mediumOfRegistration) {
        this.mediumOfRegistration = mediumOfRegistration;
    }

    public boolean isGuestUser() {
        return isGuestUser;
    }

    public void setGuestUser(boolean guestUser) {
        isGuestUser = guestUser;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public User(String userId, String name, String userPhone, String imageUrl, String email,
                boolean isSubscribed, String mediumOfRegistration, boolean isGuestUser,
                String notificationToken, Set<Dish> favoriteDishes, Dish dish) {
        this.userId = userId;
        this.name = name;
        this.userPhone = userPhone;
        this.imageUrl = imageUrl;
        this.email = email;
        this.isSubscribed = isSubscribed;
        this.mediumOfRegistration = mediumOfRegistration;
        this.isGuestUser = isGuestUser;
        this.notificationToken = notificationToken;
        this.favoriteDishes = favoriteDishes;
        this.dish = dish;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", mediumOfRegistration='" + mediumOfRegistration + '\'' +
                ", isGuestUser=" + isGuestUser +
                ", notificationToken='" + notificationToken + '\'' +
                ", favoriteDishes=" + favoriteDishes +
                ", dish=" + dish +
                '}';
    }
}
