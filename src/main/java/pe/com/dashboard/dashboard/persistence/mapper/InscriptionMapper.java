package pe.com.dashboard.dashboard.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.InheritInverseConfiguration;

import pe.com.dashboard.dashboard.domain.dto.InscripcionDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscriptionMapper {

    @Mappings({
        @Mapping(source = "idInscripcion", target = "idEnrollment"),
        @Mapping(source = "curso.idCurso", target = "courseId"),
        @Mapping(source = "curso.nombre", target = "courseName"),
        @Mapping(source = "estudiante.idUsuario", target = "studentId"),
        @Mapping(source = "estudiante.persona.nombre", target = "studentName"),
        @Mapping(source = "accesoPermitido", target = "accessGranted")
    })
    InscripcionDTO toInscripcion(InscripcionEntity entity);

    List<InscripcionDTO> toInscripciones(List<InscripcionEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "curso", ignore = true)       // Lo asignas manualmente en el Service
    @Mapping(target = "estudiante", ignore = true)  // También lo asignas desde el Service
    InscripcionEntity toInscripcion(InscripcionDTO dto);
}
