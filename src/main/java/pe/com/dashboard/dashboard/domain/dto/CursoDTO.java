package pe.com.dashboard.dashboard.domain.dto;

import lombok.Data;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;

import java.util.Date;

@Data
public class CursoDTO {
    private Integer idCourse;
    private String name;
    private String description;
    private Integer professorId;
    private TipoUsuarioEntity typeUser;
    private String courseStatus;
    private Date startDate;
    private Date endDate;
}
