package Backend_Kimotors.Kimotors.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Kimotors_PDN")
                        .version("1.0.0")
                        .description("Documentación de la API para el proyecto Kimotors_PDN que permite consultar y comparar motocicletas."));
    }
}

// http://localhost:8080/swagger-ui/index.html
// http://localhost:8080/swagger-ui.html