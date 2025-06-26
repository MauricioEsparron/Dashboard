package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.EstadoCursoDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoCursoEntity;

@Mapper(componentModel = "spring")
public interface CourseStateMapper {
    @Mappings({
            @Mapping(source = "idEstadoCurso", target = "courseStateId"),
            @Mapping(source = "descripcion", target = "description")
    })
    EstadoCursoDTO toCourseState(EstadoCursoEntity estadoCurso);

    List<EstadoCursoDTO> toPersonStates(List<EstadoCursoEntity> estadosCurso);

    @InheritInverseConfiguration
    @Mapping(target = "cursos", ignore = true)
    EstadoCursoEntity toEstadoCurso(EstadoCursoDTO stateCourse);
}