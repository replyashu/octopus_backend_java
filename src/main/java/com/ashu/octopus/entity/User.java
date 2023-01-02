package com.ashu.octopus.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    private String name;

    private String user_phone;

    private String imageUrl;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "favorite_dish",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dishId")

    )
    private Set<Dish> favoriteDishes;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public User(long user_id, String name, String user_phone, String imageUrl, String email, Set<Dish> favoriteDishes) {
        this.user_id = user_id;
        this.name = name;
        this.user_phone = user_phone;
        this.imageUrl = imageUrl;
        this.email = email;
        this.favoriteDishes = favoriteDishes;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", favoriteDishes=" + favoriteDishes +
                '}';
    }
}
