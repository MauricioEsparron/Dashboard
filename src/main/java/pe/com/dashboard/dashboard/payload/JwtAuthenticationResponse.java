package pe.com.dashboard.dashboard.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor // Crea un constructor con todos los parámetros
public class JwtAuthenticationResponse {

    private String token;
    private String username;
    private String nombre;
    private String rol;

}
