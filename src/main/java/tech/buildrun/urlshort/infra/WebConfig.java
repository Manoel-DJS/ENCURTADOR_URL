package tech.buildrun.urlshort.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CORS_PERMISSION = {
        "http://localhost:4200", "https://encurtador-url-8lqz.onrender.com"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_PERMISSION)
                .allowedMethods("GET", "POST")
                .allowedHeaders("*");
    }
}
