package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.com.dashboard.dashboard.payload.JwtAuthenticationResponse;
import pe.com.dashboard.dashboard.security.JwtService;
import pe.com.dashboard.dashboard.domain.service.impl.UserDetailsServiceImpl;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationResponse authenticate(String username, String password) {

        // Autenticación del usuario
        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(username, password);
        
        authenticationManager.authenticate(authenticationToken);

        // Cargar detalles del usuario
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Generación del token JWT
        String token = jwtService.generateToken(userDetails);

        // Obtener los roles del usuario
        List<String> roles = userDetails.getAuthorities()
            .stream()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .collect(Collectors.toList());

        // Crear y devolver la respuesta
        return new JwtAuthenticationResponse(token, userDetails.getUsername(), roles);
    }
}
