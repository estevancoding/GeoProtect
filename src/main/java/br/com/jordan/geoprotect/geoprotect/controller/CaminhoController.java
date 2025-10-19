package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.domain.Caminho;
import br.com.jordan.geoprotect.geoprotect.service.CaminhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/caminho")
public class CaminhoController {

    @Autowired
    private CaminhoService caminhoService;

    @GetMapping
    public ResponseEntity<Caminho> getCaminho() {
        // Busca o caminho no serviço e o retorna.
        // Se não encontrar nenhum, retorna 404 Not Found.
        return caminhoService.buscarPrimeiroCaminho()
                .map(caminho -> ResponseEntity.ok(caminho))
                .orElse(ResponseEntity.notFound().build());
    }
}