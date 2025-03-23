package com.bk.reactive.app.commerce.admin.my.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class WebConfig(
    @Value("\${black.food.frontend.base.url}")
    private val frontendBaseUrl: String
) : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        val maxAge = 3600L
        val allowCredentials = true

        registry.addMapping("/**")
            .allowedOrigins(frontendBaseUrl)
            .allowedMethods("POST", "GET", "PUT", "DELETE") // .allowedHeaders(MediaType.APPLICATION_JSON_VALUE)
            // .exposedHeaders(MediaType.APPLICATION_JSON_VALUE)
            .allowCredentials(allowCredentials).maxAge(maxAge)
        // Add more mappings...
    }
}