package be.iccbxl.pid.reservationsspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**") // /api/**" = accepte toutes les requetes qui commencent par /api
                .allowedOrigins("http://localhost:8081")  // Autoriser les requêtes depuis Vue.js
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Méthodes autorisées
                .allowedHeaders("*")  // Autoriser tous les headers
                .allowCredentials(true);  // Autoriser les cookies (si nécessaire)
    }
}
