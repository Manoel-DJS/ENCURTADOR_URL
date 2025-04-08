package tech.buildrun.urlshort.infra;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("API - Encurtar Links")
                .displayName("Shortener API")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Shortener_API")
                        .description("API REST para encurtar links.")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("@Manoel")
                                .email("vinesouza9x9@gmail.com")
                        )
                        .license(new License()
                                .name("Github")
                                .url("https://github.com/Manoel-DJS")));

    }
}

