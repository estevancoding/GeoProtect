package br.com.jordan.geoprotect.geoprotect.service;

import br.com.jordan.geoprotect.geoprotect.domain.Caminho;
import br.com.jordan.geoprotect.geoprotect.repository.CaminhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaminhoService {

    @Autowired
    private CaminhoRepository caminhoRepository;

    // Como teremos apenas um caminho, este método busca o primeiro que encontrar.
    public Optional<Caminho> buscarPrimeiroCaminho() {
        // O método findAll() retorna uma lista, pegamos o primeiro elemento se existir.
        return caminhoRepository.findAll().stream().findFirst();
    }
}