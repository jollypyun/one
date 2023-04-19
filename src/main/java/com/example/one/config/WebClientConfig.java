package com.example.one.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@Slf4j
public class WebClientConfig {
    @Value("${client.url}")
    private String url;
    @Value("${client.timeout}")
    private Integer connectTimeout;
    @Value("${client.read-time}")
    private Integer readTime;
    @Value("${client.write-time}")
    private Integer writeTime;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .build();
    }

    private HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(readTime))
                        .addHandlerLast(new WriteTimeoutHandler(writeTime)))
                .responseTimeout(Duration.ofSeconds(5));
    }
}
