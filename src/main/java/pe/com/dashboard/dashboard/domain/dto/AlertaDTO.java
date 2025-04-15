package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertaDTO {
    private Integer alertId;
    private String description;
    private LocalDateTime date;
    private Integer subscription;
    private Integer userId;
    private UsuarioDTO user; 
}
