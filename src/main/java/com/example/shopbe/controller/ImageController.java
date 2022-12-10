package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.ImageDTO;
import com.example.shopbe.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestApiV1
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id){
        return VsResponseUtil.ok(imageService.getImage(id));
    }

    @PostMapping("/images")
    public ResponseEntity<?> createImage(@RequestPart("file")MultipartFile file){
        return VsResponseUtil.ok(imageService.createImage(file));
    }

    @PatchMapping("/images/{id}")
    public ResponseEntity<?> changeImage(@PathVariable Long id, ImageDTO imageDTO){
        return VsResponseUtil.ok(imageService.changeImage(id, imageDTO));
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id){
        return VsResponseUtil.ok(imageService.deleteImage(id));
    }
}
