package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ComentarioDTO {
    private Integer commentId;
    private String description;
    private LocalDateTime date;
    private Integer userId;
    private Integer advanceId; 
    private Integer state;
    private UsuarioDTO user; 
}
