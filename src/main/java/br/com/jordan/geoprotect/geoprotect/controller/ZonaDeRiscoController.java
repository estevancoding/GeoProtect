package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zonas-de-risco")
public class ZonaDeRiscoController {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @GetMapping
    public ResponseEntity<List<ZonaDeRisco>> listarTodas() {
        List<ZonaDeRisco> zonas = zonaDeRiscoService.listarTodas();
        return ResponseEntity.ok(zonas);
    }

    @PostMapping
    public ResponseEntity<ZonaDeRisco> criarZona(@RequestBody ZonaDeRisco zonaDeRisco) {
        ZonaDeRisco novaZona = zonaDeRiscoService.salvar(zonaDeRisco);
        return ResponseEntity.status(201).body(novaZona);
    }
}