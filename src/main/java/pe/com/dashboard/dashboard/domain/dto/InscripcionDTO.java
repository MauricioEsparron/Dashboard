package pe.com.dashboard.dashboard.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionDTO {
    private Integer idEnrollment;
    private Integer courseId;
    private String courseName; // opcional, se puede mapear desde curso.nombre
    private Integer studentId;
    private String studentName; // opcional, se puede mapear desde estudiante.nombre
    private Boolean accessGranted; // corresponde a accesoPermitido
    private LocalDateTime registrationDate; // corresponde a accesoPermitido
}
