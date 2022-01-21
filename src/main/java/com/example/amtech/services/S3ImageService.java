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

/**
 * Service offering image management through the S3 bucket service of AWS.
 * It provides methods to upload, update and delete images.
 * It also provides the images list and image url retrieval.
 */
@Slf4j
@Service
public class S3ImageService {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    public S3ImageService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String getImgUrl(String fileKey) {
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

    public String uploadImg(MultipartFile file, String productName) throws AmazonServiceException {
        File fileObj = convertMultiPartFileToFile(file);
        String fileKey = generateFileKey(file.getOriginalFilename(), productName);
        log.info(fileKey);
        s3Client.putObject(bucketName, fileKey, fileObj);
        fileObj.delete();
        return fileKey;
    }

    public String updateImg(MultipartFile file, String productName) throws AmazonServiceException {
        String fileKey = generateFileKey(file.getOriginalFilename(), productName);
        deleteImg(fileKey);
        return uploadImg(file, productName);
    }

    public void deleteImg(String fileKey) throws AmazonServiceException {
        s3Client.deleteObject(bucketName, fileKey);
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
