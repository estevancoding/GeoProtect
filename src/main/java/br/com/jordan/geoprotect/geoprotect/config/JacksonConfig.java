package br.com.jordan.geoprotect.geoprotect.config;

import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura o Jackson para serializar e desserializar corretamente os tipos de dados
 * geoespaciais da biblioteca JTS (e.g., Polygon), permitindo que a API manipule GeoJSON.
 */
@Configuration
public class JacksonConfig {

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
