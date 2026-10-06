package etfbl.ip.internshipserviceip.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:8081")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
