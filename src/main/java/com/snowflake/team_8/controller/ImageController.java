package com.snowflake.team_8.controller;

import com.snowflake.team_8.domain.Image;
import com.snowflake.team_8.domain.SavingImageRequest;
import com.snowflake.team_8.repository.ImageRepository;
import com.snowflake.team_8.service.ImageProcessingService;
import com.snowflake.team_8.service.PresignedUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final PresignedUrlService presignedUrlService;
    private final ImageRepository imageRepository;
    private final ImageProcessingService imageProcessingService;


    @PostMapping("/image_to_text")
    public String processImageToText(@RequestBody String imageUrl) {
        return imageProcessingService.processImageToText(imageUrl);
    }

    @GetMapping("/presigned-url/upload")
    public ResponseEntity<String> getPresignedUploadUrl(@RequestParam String filename) {
        try {
            String url = presignedUrlService.getPresignedUploadUrl(filename);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            // Replace with appropriate error handling
            return ResponseEntity.internalServerError().body("Error generating URL");
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generateImage(@RequestParam String prompt) {
        try {
            String response = imageProcessingService.generateImage(prompt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to generate image");
        }
    }

    @GetMapping("/translate")
    public ResponseEntity<String> translateToKorean(@RequestParam String prompt) {
        try {
            String response = imageProcessingService.translateToKorean(prompt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to translate");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveImage(@RequestBody SavingImageRequest imageRequest){
        Long imageId = imageProcessingService.saveImage(imageRequest);
        return ResponseEntity.ok(imageId);
    }

    @GetMapping("/all")
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
