package br.com.jordan.geoprotect.geoprotect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

@Data
public class ZonaDeRiscoDTO {
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "A área não pode ser nula.")
    private Polygon area;
}
