package br.com.jordan.geoprotect.geoprotect.controller;

import br.com.jordan.geoprotect.geoprotect.domain.Usuario;
import br.com.jordan.geoprotect.geoprotect.dto.LoginRequestDTO;
import br.com.jordan.geoprotect.geoprotect.dto.LoginResponseDTO;
import br.com.jordan.geoprotect.geoprotect.service.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo processo de autenticação.
 */
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * Realiza a autenticação do usuário e retorna um token JWT se as credenciais forem válidas.
     * @param data DTO contendo o username e password.
     * @return Um DTO com o token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
