package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.dto.LocationDTO;
import br.com.jordan.geoprotect.geoprotect.dto.StatusDTO;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @PostMapping("/api/demo/check-location")
    public ResponseEntity<StatusDTO> checkLocation(@RequestBody LocationDTO location) {

        boolean isInRiskArea = zonaDeRiscoService.isCoordenadaEmZonaDeRisco(location.getLat(), location.getLng());

        if (isInRiskArea) {
            System.out.println("### ALERTA! Coordenada recebida está DENTRO da zona de risco! ###");
            StatusDTO status = new StatusDTO(true, "Você está em uma área de risco! Medidas de segurança extras ativadas.");
            return ResponseEntity.ok(status);
        } else {
            System.out.println("### Coordenada recebida está FORA da zona de risco. ###");
            StatusDTO status = new StatusDTO(false, "Sua localização é segura.");
            return ResponseEntity.ok(status);
        }
    }
}