package com.example.amtech.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class S3ImageService {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    public static String S3_ERROR = "error";

    public S3ImageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String getImgUrl(String fileKey) {
//        String key = productName.replaceAll(" ", "_").toLowerCase();
//        String fileExtension = FilenameUtils.getExtension(fileName);
//        return "https://s3.eu-north-1.amazonaws.com/amt.tech.diduno.education/" + key + "." + fileExtension;
        return "https://s3.eu-north-1.amazonaws.com/amt.tech.diduno.education/" + fileKey;
    }

    public List<S3ObjectSummary> getImgList() {
        return s3Client.listObjectsV2(bucketName).getObjectSummaries();
    }

    private String generateFileKey(String fileName, String productName) {
        String fileKey = productName.replaceAll(" ", "_").toLowerCase();
        fileKey += "." + FilenameUtils.getExtension(Objects.requireNonNull(fileName));
        return fileKey;
    }

    public String uploadImg(MultipartFile file, String productName) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileKey = generateFileKey(file.getOriginalFilename(), productName);
        log.info(fileKey);
        try {
            s3Client.putObject(bucketName, fileKey, fileObj);
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
            return S3_ERROR;
        }
        fileObj.delete();
        return fileKey;
    }

    public String updateImg(MultipartFile file, String productName) {
        String fileKey = generateFileKey(file.getOriginalFilename(), productName);
        String res = deleteImg(fileKey);
        String key = uploadImg(file, productName);
        return key;
    }

    public String deleteImg(String fileKey) {
        try {
            s3Client.deleteObject(bucketName, fileKey);
        } catch (AmazonServiceException e) {
            log.error(e.getErrorMessage());
            return S3_ERROR;
        }
        return fileKey;
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
