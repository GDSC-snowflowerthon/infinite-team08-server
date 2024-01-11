package com.snowflake.team_8.controller;

import com.snowflake.team_8.domain.CommentRequest;
import com.snowflake.team_8.domain.ImageGenerationResponse;
import com.snowflake.team_8.service.ImageProcessingService;
import com.snowflake.team_8.service.PresignedUrlService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final PresignedUrlService presignedUrlService;

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
}
