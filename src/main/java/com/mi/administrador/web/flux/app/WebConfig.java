package com.mi.administrador.web.flux.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/my_administration/**")
                .allowedOrigins("http://localhost:4201")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
               // .allowedHeaders(MediaType.APPLICATION_JSON_VALUE)
               // .exposedHeaders(MediaType.APPLICATION_JSON_VALUE)
                .allowCredentials(true).maxAge(3600);

        // Add more mappings...
    }
}
