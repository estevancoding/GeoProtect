package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.dto.ZonaDeRiscoDTO;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/zonas-de-risco")
public class ZonaDeRiscoController {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @GetMapping
    public ResponseEntity<List<ZonaDeRiscoDTO>> listarTodas() {
        List<ZonaDeRisco> zonas = zonaDeRiscoService.listarTodas();
        List<ZonaDeRiscoDTO> dtos = zonas.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ZonaDeRiscoDTO> criarZona(@Valid @RequestBody ZonaDeRiscoDTO zonaDeRiscoDTO) {
        ZonaDeRisco zonaDeRisco = toEntity(zonaDeRiscoDTO);
        ZonaDeRisco novaZona = zonaDeRiscoService.salvar(zonaDeRisco);
        return ResponseEntity.status(201).body(toDto(novaZona));
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