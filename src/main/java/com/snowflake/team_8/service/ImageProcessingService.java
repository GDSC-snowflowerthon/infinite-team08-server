package com.snowflake.team_8.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snowflake.team_8.domain.CommentRequest;
import com.snowflake.team_8.domain.ImageGenerationRequest;
import com.snowflake.team_8.domain.ImageGenerationResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.json.JSONObject;



@Service
@RequiredArgsConstructor
@Transactional
public class ImageProcessingService {



    @Value("${chatgpt.api.key}")
    private String apiKey;

    private final PresignedUrlService presignedUrlService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String fastApiUrl_process = "http://localhost:8000/process_image/";
    private final String fastApiUrl_generate = "http://localhost:8000/generate_image/";
    private final String fastApiUrl_translate = "http://localhost:8000/translate_to_korean/";
    /**
     * 이미지 설명을 반환하는 메서드이다.
     *
     * @param imageUrl 이미지 url
     * @return 이미지 설명 text
     */
    public String processImageToText(String imageUrl) {
        String presignedUrl = presignedUrlService.getPresignedUrl(imageUrl);
        String extractedPresignedUrl = extractBaseUrl(presignedUrl);
        HttpEntity<ImageRequest> request = new HttpEntity<>(new ImageRequest(extractedPresignedUrl));
        ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl_process, request, String.class);
        return response.getBody();
    }


    static class ImageRequest {
        @JsonProperty("image_url")
        private String image_url;

        public ImageRequest(String image_url) {
            this.image_url = image_url;
        }

    }

    public static String extractBaseUrl(String fullUrl) {
        int queryParamStart = fullUrl.indexOf("?");
        if (queryParamStart != -1) {
            return fullUrl.substring(0, queryParamStart);
        }
        return fullUrl;
    }

    public String generateImage(String prompt) {
        try {
            ImagePromptRequest request = new ImagePromptRequest();
            request.setPrompt(prompt);

            ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl_generate, request, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate image");
        }
    }

    public String translateToKorean(String text) {
        try {
            TranslationRequest request = new TranslationRequest();
            request.setText(text);

            ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl_translate, request, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to translate text");
        }
    }

    @Setter
    static class ImagePromptRequest {
        @JsonProperty("prompt")
        private String prompt;
    }

    @Setter
    static class TranslationRequest {
        @JsonProperty("text")
        private String text;
    }
}

