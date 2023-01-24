package com.ashu.octopus.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer notificationId;

    private String message;

    private Date createdAt;

    private boolean isRead;

    @ManyToOne
    private User user;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification() {
    }

    public Notification(Integer notificationId, String message, Date createdAt, boolean isRead, User user) {
        this.notificationId = notificationId;
        this.message = message;
        this.createdAt = createdAt;
        this.isRead = isRead;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", isRead=" + isRead +
                ", user=" + user +
                '}';
    }
}
