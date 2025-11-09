package br.com.jordan.geoprotect.geoprotect.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidade JPA que representa um usuário da aplicação.
 * Implementa a interface UserDetails do Spring Security para integração.
 */
@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // TODO: Implementar uma entidade Role separada para um modelo mais robusto.
    // Por simplicidade inicial, estamos usando uma String de roles separadas por vírgula.
    @Column(nullable = false)
    private String roles = "ROLE_USER";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Transforma a string de roles em uma lista de SimpleGrantedAuthority
        return List.of(new SimpleGrantedAuthority(this.roles));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Por padrão, contas não expiram
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Por padrão, contas não são bloqueadas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Por padrão, credenciais não expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // Por padrão, usuários estão habilitados
    }
}
