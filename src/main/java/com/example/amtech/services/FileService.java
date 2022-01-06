package com.example.amtech.services;

import com.example.amtech.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Service
public class FileService {

    public void saveImgFile(MultipartFile multipartFile, Product product) {
        String imgDir = "images/product/";
        String dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String fileName = "img_" + dateName + "." + FilenameUtils.getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            saveFile(imgDir, fileName, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            fileName = "no-product-image.png";
        }
        product.setImg(fileName);
    }

    private static void saveFile(String uploadDir, String fileName,
                                 MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
