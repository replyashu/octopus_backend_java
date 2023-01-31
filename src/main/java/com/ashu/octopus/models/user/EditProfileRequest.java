package com.ashu.octopus.models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileRequest {
    public String userUid;
    public String name;
    public String email;
    public String phoneNumber;
    public String profilePhoto;
    public byte[] userImage;
    public String userId;
}
