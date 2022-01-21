package com.example.amtech.controllers;

import com.amazonaws.AmazonServiceException;
import com.example.amtech.services.S3ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller managing the admin page concerning images management of the application.
 * It provides an endpoint to get the images list and one to delete an image.
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class ImageController {
    private final S3ImageService s3ImageService;

    @GetMapping("/manage-images")
    public String manageImage(Model model) {
        model.addAttribute("imagesList", s3ImageService.getImgList());
        return "manage-images";
    }

    @DeleteMapping("/manage-images/delete/{id}")
    public String deleteImage(@PathVariable String id, Model model) {
        try {
            s3ImageService.deleteImg(id);
        } catch (AmazonServiceException e) {
            model.addAttribute("deleteError", "No image associated with this id");
            return "manage-images";
        }
        return "redirect:/admin/manage-images";
    }
}
