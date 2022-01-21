package com.example.amtech.controllers;

import com.example.amtech.services.S3ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class ImageController {
    private final S3ImageService s3ImageService;

    @GetMapping("/manage-images")
    public String manageImage(Model model) {
        model.addAttribute("imagesList", s3ImageService.getImgList());
        log.info("In get manage-images");
        return "manage-images";
    }

    @PostMapping("/manage-images")
    public String insertImage(@RequestParam("image") MultipartFile multipartFile, Model model) {

        // TODO ajouter un champ nom, sinon le getOriginalFilename renvoit aussi l'extension et donc apparaît à double
        String fileKey = s3ImageService.uploadImg(multipartFile, Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (fileKey.equals(S3ImageService.S3_ERROR)) {
            log.error("Error uploading file");
            model.addAttribute("uploadError", "Error uploading image");
            return "manage-images";
        }
        return "redirect:/admin/manage-images";
    }

    @DeleteMapping("/manage-images/delete/{id}")
    public String deleteImage(@PathVariable String id, Model model) {
        String fileKey = s3ImageService.deleteImg(id);
        if (fileKey.equals(S3ImageService.S3_ERROR)) {
            log.error("Error deleting file");
            model.addAttribute("deleteError", "No image associated with this id");
            return "manage-images";
        }
        return "redirect:/admin/manage-images";
    }
}
