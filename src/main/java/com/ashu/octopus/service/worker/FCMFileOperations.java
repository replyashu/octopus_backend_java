package com.ashu.octopus.service.worker;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class FCMFileOperations {

    private Storage storage;

    public String saveTest(MultipartFile file, String fileName) throws IOException {
        String imageName = fileName;
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        BlobId blobId = BlobId.of("octopus_images", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .build();
        storage.create(blobInfo, file.getInputStream());
        return imageName;
    }
}
