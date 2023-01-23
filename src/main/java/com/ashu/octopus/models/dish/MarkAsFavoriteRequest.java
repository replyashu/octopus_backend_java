package com.ashu.octopus.models.dish;

public class MarkAsFavoriteRequest {

    public String userUuid;

    public Integer position;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public MarkAsFavoriteRequest(String userUuid, Integer position) {
        this.userUuid = userUuid;
        this.position = position;
    }

    public MarkAsFavoriteRequest() {
    }

    @Override
    public String toString() {
        return "MarkAsFavoriteRequest{" +
                "userUuid='" + userUuid + '\'' +
                ", position=" + position +
                '}';
    }
}
