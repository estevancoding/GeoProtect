package br.com.jordan.geoprotect.geoprotect.service;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.repository.ZonaDeRiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço que centraliza a lógica de negócio para a entidade ZonaDeRisco.
 */
@Service
public class ZonaDeRiscoService {

    @Autowired
    private ZonaDeRiscoRepository zonaDeRiscoRepository;

    /**
     * Persiste uma entidade ZonaDeRisco no banco de dados.
     * @param zonaDeRisco A entidade a ser salva.
     * @return A entidade após ser salva (com ID preenchido).
     */
    public ZonaDeRisco salvar(ZonaDeRisco zonaDeRisco) {
        return zonaDeRiscoRepository.save(zonaDeRisco);
    }

    /**
     * Retorna todas as zonas de risco cadastradas.
     * @return Uma lista de todas as entidades ZonaDeRisco.
     */
    public List<ZonaDeRisco> listarTodas() {
        return zonaDeRiscoRepository.findAll();
    }

    /**
     * Verifica se uma coordenada geográfica está dentro de alguma zona de risco cadastrada.
     * @param latitude A latitude do ponto a ser verificado.
     * @param longitude A longitude do ponto a ser verificado.
     * @return true se a coordenada estiver em uma zona de risco, false caso contrário.
     */
    public boolean isCoordenadaEmZonaDeRisco(double latitude, double longitude) {
        // A ordem dos parâmetros é invertida para (longitude, latitude) para seguir
        // o padrão (X, Y) esperado por funções geoespaciais como a do PostGIS.
        return zonaDeRiscoRepository.isPontoEmZonaDeRisco(longitude, latitude);
    }
}
