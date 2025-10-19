package br.com.jordan.geoprotect.geoprotect.service;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.repository.ZonaDeRiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaDeRiscoService {

    @Autowired
    private ZonaDeRiscoRepository zonaDeRiscoRepository;

    public ZonaDeRisco salvar(ZonaDeRisco zonaDeRisco) {
        // No futuro, poderíamos adicionar validações aqui antes de salvar.
        // Por exemplo: não permitir salvar zonas com nomes duplicados.
        return zonaDeRiscoRepository.save(zonaDeRisco);
    }

    public List<ZonaDeRisco> listarTodas() {
        return zonaDeRiscoRepository.findAll();
    }

    public boolean isCoordenadaEmZonaDeRisco(double latitude, double longitude) {
        return zonaDeRiscoRepository.isPontoEmZonaDeRisco(longitude, latitude);
    }
}