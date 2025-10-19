package br.com.jordan.geoprotect.geoprotect.repository;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaDeRiscoRepository extends JpaRepository<ZonaDeRisco, Long> {

    @Query(
            value = "SELECT count(*) > 0 FROM zonas_de_risco WHERE ST_Contains(area, ST_SetSRID(ST_MakePoint(?1, ?2), 4326))",
            nativeQuery = true
    )
    boolean isPontoEmZonaDeRisco(double longitude, double latitude);
}