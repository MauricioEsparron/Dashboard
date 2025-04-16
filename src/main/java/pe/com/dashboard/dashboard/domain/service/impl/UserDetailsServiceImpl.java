package pe.com.dashboard.dashboard.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Obtenemos el rol del tipo de usuario
        String rolDescripcion = usuario.getTipoUsuario().getDescripcion(); // Ej: "ADMIN", "ESTUDIANTE"
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + rolDescripcion.toUpperCase());

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getContrasenia(),
                Collections.singletonList(authority)
        );
    }
}
