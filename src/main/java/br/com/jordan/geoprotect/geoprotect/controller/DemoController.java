package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.dto.LocationDTO;
import br.com.jordan.geoprotect.geoprotect.dto.StatusDTO;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    // Variável para guardar o estado da nossa simulação em memória.
    private boolean isUserInRiskArea = false;

    @PostMapping("/api/demo/update-location")
    public ResponseEntity<Void> updateLocation(@RequestBody LocationDTO location) {

        // Atualiza nosso estado com base na verificação do serviço
        this.isUserInRiskArea = zonaDeRiscoService.isCoordenadaEmZonaDeRisco(location.getLat(), location.getLng());

        if (isUserInRiskArea) {
            System.out.println("### ALERTA! Status do usuário atualizado para: DENTRO da zona de risco! ###");
        } else {
            System.out.println("### Status do usuário atualizado para: FORA da zona de risco. ###");
        }

        return ResponseEntity.ok().build();
    }

    // NOVO ENDPOINT para o app do usuário consultar
    @GetMapping("/api/user/status")
    public ResponseEntity<StatusDTO> getUserStatus() {
        if (this.isUserInRiskArea) {
            StatusDTO status = new StatusDTO(true, "Você está em uma área de risco! Medidas de segurança extras ativadas.");
            return ResponseEntity.ok(status);
        } else {
            StatusDTO status = new StatusDTO(false, "Sua localização é segura.");
            return ResponseEntity.ok(status);
        }
    }
}