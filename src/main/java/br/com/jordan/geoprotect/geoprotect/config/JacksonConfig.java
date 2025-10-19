package br.com.jordan.geoprotect.geoprotect.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public JtsModule jtsModule() {
        // Este método cria e expõe um "Bean" do JtsModule para o Spring.
        // O Spring Boot irá detectar este Bean e registrá-lo automaticamente
        // no seu conversor de JSON (o ObjectMapper), ensinando-o a lidar
        // com tipos de dados geoespaciais como o Polygon.
        return new JtsModule();
    }
}