package br.com.jordan.geoprotect.geoprotect.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Polygon;

/**
 * Entidade JPA que representa uma zona de risco geográfica no banco de dados.
 */
@Entity
@Table(name = "zonas_de_risco")
@Getter
@Setter
public class ZonaDeRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    /**
     * Define a coluna específica do PostGIS para armazenar polígonos no sistema de
     * coordenadas WGS 84 (padrão GPS).
     */
    @Column(columnDefinition = "geometry(Polygon, 4326)", nullable = false)
    private Polygon area;

}
