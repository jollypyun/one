package com.example.one.serviceimpl;

import com.example.one.service.CaptchaService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {
    @Value("${recaptcha.verify_url}")
    private String url;
    @Value("${recaptcha.secret_key}")
    private String key;
    private static final double HALF = 0.5;

    @Override
    public boolean verifyToken(String token) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozila/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String param = "secret=" + key + "&response=" + token;

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(param);
            wr.flush();
            wr.close();

            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            log.info(response.toString());
            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            return String.valueOf(jsonObject.get("success")).equals("true") && Integer.parseInt(String.valueOf(jsonObject.get("score"))) >= HALF;
        } catch (Exception e) {
            return false;
        }


    }
}
