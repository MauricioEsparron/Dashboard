package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

// import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity.EstadoRevision;

@Getter
@Setter
public class AvanceTesisDTO {
    private Integer advanceId;
    private Integer studentId;
    private String title;
    private String description;
    private String fileUrl;
    private Integer idEstadoAvanceTesis;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime uploadDate;
    private EstadoAvanceTesisDTO stateProgressThesis;
    private UsuarioDTO student;
}