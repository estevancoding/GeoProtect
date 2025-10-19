package br.com.jordan.geoprotect.geoprotect.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.LineString;

@Entity
@Table(name = "caminhos")
@Getter
@Setter
public class Caminho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "geometry(LineString, 4326)", nullable = false)
    private LineString trajeto;

}