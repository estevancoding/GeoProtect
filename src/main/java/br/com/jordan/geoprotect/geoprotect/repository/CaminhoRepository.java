package br.com.jordan.geoprotect.geoprotect.repository;

import br.com.jordan.geoprotect.geoprotect.domain.Caminho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminhoRepository extends JpaRepository<Caminho, Long> {

}