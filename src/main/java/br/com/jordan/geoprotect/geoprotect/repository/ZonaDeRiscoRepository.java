package br.com.jordan.geoprotect.geoprotect.repository;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório Spring Data JPA para a entidade {@link ZonaDeRisco}.
 */
@Repository
public interface ZonaDeRiscoRepository extends JpaRepository<ZonaDeRisco, Long> {

    /**
     * Verifica se um ponto geográfico (longitude, latitude) está contido em alguma zona de risco.
     * Esta consulta utiliza a função nativa 'ST_Contains' do PostGIS, que não faz parte do JPA padrão.
     *
     * @param longitude A longitude da coordenada.
     * @param latitude A latitude da coordenada.
     * @return true se o ponto estiver em ao menos uma zona de risco, false caso contrário.
     */
    @Query(
            value = "SELECT count(*) > 0 FROM zonas_de_risco WHERE ST_Contains(area, ST_SetSRID(ST_MakePoint(?1, ?2), 4326))",
            nativeQuery = true
    )
    boolean isPontoEmZonaDeRisco(double longitude, double latitude);
}
