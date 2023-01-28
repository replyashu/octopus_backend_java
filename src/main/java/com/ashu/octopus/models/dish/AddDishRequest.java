package com.ashu.octopus.models.dish;

import com.ashu.octopus.entity.Dish;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDishRequest {
    public String dishName;
    public String dishDescription;
    public String dishUrl;
    public String userId;
}
