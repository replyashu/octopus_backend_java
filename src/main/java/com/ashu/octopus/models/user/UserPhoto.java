package com.ashu.octopus.models.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserPhoto {
    String publicId;
    MultipartFile file;
}
