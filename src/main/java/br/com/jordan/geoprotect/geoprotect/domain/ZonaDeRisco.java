package br.com.jordan.geoprotect.geoprotect.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Polygon;

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

    @Column(columnDefinition = "geometry(Polygon, 4326)", nullable = false)
    private Polygon area;

}