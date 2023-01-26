package com.ashu.octopus.models.dish;

import com.ashu.octopus.entity.Dish;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveFavoriteRequest {
    public String userUuid;
    public Long dishId;
}
