package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**") // CORS를 적용할 url의 패턴을 정의
                .allowedOrigins("http://localhost:3000") // 허용할 origin을 정의
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS") // HTTP Method를 지정
                .allowCredentials(true);
    }
}
