package com.example.one.serviceimpl;

import com.example.one.service.ExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalServiceImpl implements ExternalService {
//    private final WebClient webClien+-------t;

    @Override
    public void getInfo() {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory("http://apis.data.go.kr/1262000/CountryKorTradeService2");
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(uriBuilderFactory)
                .build();
//        String decode = URLDecoder.decode("2R4dpqjc8wA5hk0mcHWWaxp%2FFtSKWGggntnC4aG%2BcL%2FYTcclK3dqc6etDCmc2autJ0YEuekme85qwh1IlLb3mA%3D%3D", StandardCharsets.UTF_8);
        var block = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getCountryKorTradeList2")
                        .queryParam("serviceKey", "2R4dpqjc8wA5hk0mcHWWaxp%2FFtSKWGggntnC4aG%2BcL%2FYTcclK3dqc6etDCmc2autJ0YEuekme85qwh1IlLb3mA%3D%3D")
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 10)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(Mono::error)
                .block();

        System.out.println("block = " + block);
//        Gson gson = new GsonBuilder().create();
//        TradeCountryResponse response = gson.fromJson(mono.block(), TradeCountryResponse.class);


    }
}
