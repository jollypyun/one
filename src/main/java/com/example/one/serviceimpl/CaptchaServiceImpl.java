package com.example.one.serviceimpl;

import com.example.one.service.CaptchaService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Value("${recaptcha.verify_url}")
    private String url;
    @Value("${recaptcha.secret_key}")
    private String key;
    private static final double HALF = 0.5;

    @Override
    public boolean verifyToken(String token) {
        try {
            HttpHeaders httpHeaders= new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("secret", key);
            map.add("response", token);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return String.valueOf(jsonObject.get("success")).equals("true") && Double.parseDouble(String.valueOf(jsonObject.get("score"))) >= HALF;
        } catch (Exception e) {
            return false;
        }


    }
}
