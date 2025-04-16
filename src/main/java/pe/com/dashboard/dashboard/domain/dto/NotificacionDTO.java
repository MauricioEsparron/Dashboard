package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class NotificacionDTO {
    private Integer notificationId;
    private String description;
    private LocalDateTime date;
    private Integer read;
    private Integer userId;
    private UsuarioDTO user;
}
