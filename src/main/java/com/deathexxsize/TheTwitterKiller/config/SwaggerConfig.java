package com.deathexxsize.TheTwitterKiller.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring boot REST API")
                .description("Spring boot REST API")
                .contact(new Contact().
                        name("Chotam messenger"))
                        .version("1.0.0"));
    }
}
