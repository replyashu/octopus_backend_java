package com.ashu.octopus.service.worker;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class FirebaseImageService implements IImageService {


//    @EventListener
//    public void init(ApplicationReadyEvent event) {
//
//        // initialize Firebase
//
//        try {
//
//            ClassPathResource serviceAccount = new ClassPathResource("firebase-service-account.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
//                    .setStorageBucket(properties.getBucketName())
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//
//        }
//    }

    @Override
    public String getImageUrl(String name) {
        return "";
    }

    @Override
    public String save(MultipartFile file) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(file.getOriginalFilename());

        bucket.create(name, file.getBytes(), file.getContentType());

        return name;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {

        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(originalFileName);

        bucket.create(name, bytes);

        return name;
    }

    public String save(byte[] bufferedImage, String originalFileName) throws IOException {
//        Bucket bucket = StorageClient.getInstance().bucket();
        System.out.println("here1");

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream()))
                .setProjectId("octopus-375417").build().getService();

        String name = generateFileName(originalFileName);
        System.out.println("here2");
        Storage.BlobTargetOption targetOption = Storage.BlobTargetOption.disableGzipContent();
        storage.create(BlobInfo.newBuilder("samole", "octy").build(), bufferedImage, targetOption);
        System.out.println("here3");
        return name;
    }

    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }

    @Data
    @Configuration
    public class Properties {
        private String bucketName;
        private String imageUrl;
    }

}
