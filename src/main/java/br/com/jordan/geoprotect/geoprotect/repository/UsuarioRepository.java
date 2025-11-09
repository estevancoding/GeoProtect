package br.com.jordan.geoprotect.geoprotect.repository;

import br.com.jordan.geoprotect.geoprotect.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório Spring Data JPA para a entidade {@link Usuario}.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo seu nome de usuário (username).
     * @param username O nome de usuário a ser buscado.
     * @return Um Optional contendo o usuário, se encontrado.
     */
    Optional<Usuario> findByEmail(String email);
}
