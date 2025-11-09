package br.com.jordan.geoprotect.geoprotect.service.security;

import br.com.jordan.geoprotect.geoprotect.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serviço responsável pela geração e validação de tokens JWT.
 */
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.issuer}")
    private String issuer;

    /**
     * Gera um token JWT para um usuário autenticado.
     * @param usuario O usuário para o qual o token será gerado.
     * @return O token JWT como uma String.
     */
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer) // Emissor do token
                    .withSubject(usuario.getUsername()) // Assunto do token (usuário)
                    .withExpiresAt(genExpirationDate()) // Data de expiração
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    /**
     * Valida um token JWT e retorna o nome de usuário (subject) se o token for válido.
     * @param token O token JWT a ser validado.
     * @return O nome de usuário contido no token.
     * @throws JWTVerificationException se o token for inválido.
     */
    public String validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    /**
     * Gera a data de expiração do token (configurado para 2 horas a partir de agora).
     * @return Um Instant representando a data/hora de expiração.
     */
    private Instant genExpirationDate() {
        return Instant.now().plusSeconds(7200); // 2 horas em segundos
    }
}
