package pe.com.dashboard.dashboard.domain.service;

import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.impl.UserDetailsServiceImpl;
import pe.com.dashboard.dashboard.payload.JwtAuthenticationResponse;
import pe.com.dashboard.dashboard.persistence.mapper.UserMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;
import pe.com.dashboard.dashboard.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserMapper userMapper;

        // Constructor para que Spring inyecte UserMapper
        public AuthService(UserMapper userMapper) {
            this.userMapper = userMapper;
        }

    public JwtAuthenticationResponse authenticate(String username, String password) {

        // 1. Autenticar
          // 1. Autenticar
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password)
    );

    // 2. Obtener UserDetails
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    // 3. Generar Token
    String token = jwtService.generateToken(userDetails);

    // 4. Traer el UsuarioEntity
    UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    // 5. Convertir a UsuarioDTO
    UsuarioDTO usuarioDTO = userMapper.toUser(usuarioEntity);

    // 6. Obtener roles
    List<String> roles = userDetails.getAuthorities().stream()
            .map(auth -> auth.getAuthority())
            .collect(Collectors.toList());

    // 7. Construir respuesta usando el DTO
    return new JwtAuthenticationResponse(
            token,
            username,
            usuarioDTO.getName(), // <-- Ahora desde el DTO
            roles
    );
}

}
