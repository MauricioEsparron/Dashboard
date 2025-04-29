package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer userId;
    private String username;
    private String password;
    private Integer personId;
    private Integer userTypeId;
    private Integer active;
    private String name;
    private PersonaDTO person;
    private TipoUsuarioDTO userType;


}
