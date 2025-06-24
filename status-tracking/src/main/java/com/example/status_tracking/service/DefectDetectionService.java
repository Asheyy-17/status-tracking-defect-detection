package com.example.status_tracking.service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefectDetectionService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FASTAPI_URL = "http://localhost:8000/predict/";

    public String predictDefect(MultipartFile file) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Convert MultipartFile to ByteArrayResource
        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        // Create multipart request body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", new HttpEntity<>(fileResource, headers));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send request to FastAPI
        ResponseEntity<String> response = restTemplate.exchange(
                FASTAPI_URL, HttpMethod.POST, requestEntity, String.class
        );

        return response.getBody();
    }
}
