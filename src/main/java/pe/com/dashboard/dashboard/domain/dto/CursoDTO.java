package pe.com.dashboard.dashboard.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CursoDTO {
    private Integer idCourse;
    private String name;
    private String description;
    private String imageUrl;
    private Integer professorId; // ID del profesor
    private String typeUserDescription; // Descripción del tipo de usuario
    private Integer typeUserId; // ✅ ID del tipo de usuario (necesario para actualizar)
    private Integer restrictedAccess;
    private Integer state;
    private String professorEmail;
    private String professorFirstName;
    private String professorLastName;
    private String professorFullName;    
    private Integer studentCount;


    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
}
