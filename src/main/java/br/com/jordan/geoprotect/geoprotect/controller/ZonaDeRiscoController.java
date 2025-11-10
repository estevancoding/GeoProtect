package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.dto.LocationDTO;
import br.com.jordan.geoprotect.geoprotect.dto.StatusDTO;
import br.com.jordan.geoprotect.geoprotect.dto.ZonaDeRiscoDTO;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller REST para gerenciar as operações CRUD de Zonas de Risco.
 */
@RestController
@RequestMapping("/api/v1/zonas-de-risco")
public class ZonaDeRiscoController {

    private static final Logger log = LoggerFactory.getLogger(ZonaDeRiscoController.class);

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    /**
     * Lista todas as zonas de risco cadastradas.
     * @return Uma resposta com a lista de DTOs de zonas de risco e status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<ZonaDeRiscoDTO>> listarTodas() {
        List<ZonaDeRisco> zonas = zonaDeRiscoService.listarTodas();
        List<ZonaDeRiscoDTO> dtos = zonas.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Cria uma nova zona de risco com base nos dados fornecidos.
     * @param zonaDeRiscoDTO DTO com os dados para a criação da nova zona.
     * @return Uma resposta com a zona de risco recém-criada e status HTTP 201.
     */
    @PostMapping
    public ResponseEntity<ZonaDeRiscoDTO> criarZona(@Valid @RequestBody ZonaDeRiscoDTO zonaDeRiscoDTO) {
        ZonaDeRisco zonaDeRisco = toEntity(zonaDeRiscoDTO);
        ZonaDeRisco novaZona = zonaDeRiscoService.salvar(zonaDeRisco);
        return ResponseEntity.status(201).body(toDto(novaZona));
    }

    /**
     * Verifica se uma determinada coordenada está dentro de uma zona de risco.
     * @param locationDTO DTO contendo a latitude e longitude.
     * @return Um DTO de status indicando se está em área de risco e uma mensagem.
     */
    @PostMapping("/verificar")
    public ResponseEntity<StatusDTO> verificarLocalizacao(@Valid @RequestBody LocationDTO locationDTO) {
        boolean emZonaDeRisco = zonaDeRiscoService.isCoordenadaEmZonaDeRisco(locationDTO.getLat(), locationDTO.getLng());

        if (emZonaDeRisco) {
            log.warn("ALERTA! Coordenada [lat:{}, lon:{}] está DENTRO da zona de risco!", locationDTO.getLat(), locationDTO.getLng());
            StatusDTO status = new StatusDTO(true, "Você está em uma área de risco!");
            return ResponseEntity.ok(status);
        } else {
            log.info("Coordenada [lat:{}, lon:{}] está FORA da zona de risco.", locationDTO.getLat(), locationDTO.getLng());
            StatusDTO status = new StatusDTO(false, "Sua localização é segura.");
            return ResponseEntity.ok(status);
        }
    }

    private ZonaDeRiscoDTO toDto(ZonaDeRisco zonaDeRisco) {
        ZonaDeRiscoDTO dto = new ZonaDeRiscoDTO();
        dto.setId(zonaDeRisco.getId());
        dto.setNome(zonaDeRisco.getNome());
        dto.setArea(zonaDeRisco.getArea());
        return dto;
    }

    private ZonaDeRisco toEntity(ZonaDeRiscoDTO dto) {
        ZonaDeRisco zonaDeRisco = new ZonaDeRisco();
        zonaDeRisco.setNome(dto.getNome());
        zonaDeRisco.setArea(dto.getArea());
        return zonaDeRisco;
    }
}
