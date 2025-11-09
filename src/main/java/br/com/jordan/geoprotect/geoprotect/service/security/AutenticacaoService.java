package br.com.jordan.geoprotect.geoprotect.service.security;

import br.com.jordan.geoprotect.geoprotect.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço que implementa a lógica de carregamento de usuário para o Spring Security.
 */
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método chamado pelo Spring Security para carregar um usuário pelo seu username.
     * @param username O nome de usuário a ser buscado.
     * @return Um objeto UserDetails representando o usuário.
     * @throws UsernameNotFoundException se o usuário não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
