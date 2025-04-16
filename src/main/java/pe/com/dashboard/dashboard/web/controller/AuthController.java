package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.service.AuthService;
import pe.com.dashboard.dashboard.payload.JwtAuthenticationResponse;
import pe.com.dashboard.dashboard.payload.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse response = authService.authenticate(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );
        return ResponseEntity.ok(response);
    }

    
}
