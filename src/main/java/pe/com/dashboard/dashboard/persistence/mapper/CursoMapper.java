package pe.com.dashboard.dashboard.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.Curso;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mappings({
        @Mapping(source = "idcurso", target = "idCourse"),
        @Mapping(source = "nombre", target = "name"),
        @Mapping(source = "descripcion", target = "description"),
        @Mapping(source = "idprofesor", target = "professorId"),
        @Mapping(source = "tipoUsuarioEntity", target = "typeUser"),
        @Mapping(source = "estado", target = "courseStatus"),
        @Mapping(source = "fechaInicio", target = "startDate"),
        @Mapping(source = "fechaFin", target = "endDate")
    })
    CursoDTO toCursoDTO(Curso curso);

    List<CursoDTO> toCursoDTOs(List<Curso> cursos);

    @InheritInverseConfiguration
    Curso toCurso(CursoDTO cursoDTO);
}
