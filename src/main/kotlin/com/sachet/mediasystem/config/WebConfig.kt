package com.sachet.mediasystem.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.RouterFunctions.resources

@Configuration
@EnableWebFlux
class CorsGlobal: WebFluxConfigurer{

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "PUT", "POST", "DELETE")
            .maxAge(3600)
    }

}

@Configuration
class WebConfiguration {

    @Bean
    fun resRouter() = resources("/**", ClassPathResource("upload/"))

}