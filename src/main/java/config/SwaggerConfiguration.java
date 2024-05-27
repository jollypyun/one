package config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("원자 조회")
                        .version("1.0")
                        .description("원자 조회를 위한 API"));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"api/v1/**"};
        String[] packageToScan = {"com.example.springdoc"};
        return GroupedOpenApi.builder()
                .group("springdoc-openapi")
                .pathsToMatch(paths)
                .packagesToScan(packageToScan)
                .build();
    }
}
