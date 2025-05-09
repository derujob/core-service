package edu.bk.core.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .build();
    }
}