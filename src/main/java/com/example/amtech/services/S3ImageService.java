package com.example.amtech.services;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class S3ImageService {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    public S3ImageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String getFileUrl(String productName, String fileName) {
//        return s3Client.getUrl(bucketName, key).toExternalForm();
        String key = productName.replaceAll(" ", "_").toLowerCase();
        String fileExtension = FilenameUtils.getExtension(fileName);
        return "https://s3.eu-north-1.amazonaws.com/amt.tech.diduno.education/" + key + "." + fileExtension;
    }

    public void uploadFile(MultipartFile file, String productName) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = productName.replaceAll(" ", "_").toLowerCase();
        fileName += "." + FilenameUtils.getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        log.info("Nom fichier: " + fileName);
        s3Client.putObject(bucketName, fileName, fileObj);
        fileObj.delete();
        log.info("File uploaded !");
    }

    public void upload(File file, String productName) {
        String fileName = productName.replaceAll(" ", "_").toLowerCase();
        fileName += "." + FilenameUtils.getExtension(file.getName());
        s3Client.putObject(bucketName, fileName, file);
        log.info("File uploaded !");
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
