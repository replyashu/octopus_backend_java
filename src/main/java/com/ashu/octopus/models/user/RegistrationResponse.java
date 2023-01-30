package com.ashu.octopus.models.user;

import java.util.Arrays;

public class RegistrationResponse {

    public String userUid;
    public String name;
    public String email;

    public String phoneNumber;

    public String profilePhoto;

    public byte[] userImage;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public RegistrationResponse() {
    }

    public RegistrationResponse(String userUid, String name, String email, String phoneNumber,
                                String profilePhoto, byte[] userImage) {
        this.userUid = userUid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "userUid='" + userUid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", userImage=" + Arrays.toString(userImage) +
                '}';
    }
}
