package br.com.jordan.geoprotect.geoprotect.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para transportar dados de coordenadas geográficas (latitude e longitude).
 */
@Getter
@Setter
public class LocationDTO {

    private double lat;
    private double lng;
}
