package br.com.jordan.geoprotect.geoprotect.repository;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaDeRiscoRepository extends JpaRepository<ZonaDeRisco, Long> {

    @Query(value = "SELECT count(z) > 0 FROM ZonaDeRisco z WHERE ST_Contains(z.area, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) = true")
    boolean isPontoEmZonaDeRisco(@Param("longitude") double longitude, @Param("latitude") double latitude);

}