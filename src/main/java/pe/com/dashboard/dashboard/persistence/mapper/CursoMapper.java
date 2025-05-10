package pe.com.dashboard.dashboard.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mappings({
            @Mapping(source = "idCurso", target = "idCourse"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "profesor.idUsuario", target = "professorId"),
            @Mapping(source = "profesor", target = "professorEmail", qualifiedByName = "mapProfesorCorreo"),
            @Mapping(source = "profesor", target = "professorFirstName", qualifiedByName = "mapProfesorNombres"),
            @Mapping(source = "profesor", target = "professorLastName", qualifiedByName = "mapProfesorApellidos"),
            @Mapping(source = "profesor", target = "professorFullName", qualifiedByName = "mapProfesorNombreCompleto"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "fechaInicio", target = "startDate"),
            @Mapping(source = "fechaFin", target = "endDate"),
            @Mapping(source = "accesoRestringido", target = "restrictedAccess"),
            @Mapping(source = "tipoUsuarioEntity.idTipoUsuario", target = "typeUserId"),
            @Mapping(source = "tipoUsuarioEntity.descripcion", target = "typeUserDescription"),
            @Mapping(source = "inscripciones", target = "studentCount", qualifiedByName = "mapInscripcionesCount"),

    })
    CursoDTO toCurso(CursoEntity curso);

    List<CursoDTO> toCursos(List<CursoEntity> cursos);

    @InheritInverseConfiguration
    @Mapping(target = "profesor", ignore = true)
    @Mapping(target = "inscripciones", ignore = true)
    @Mapping(target = "accesoRestringido", source = "restrictedAccess")
    @Mapping(source = "typeUserId", target = "tipoUsuarioEntity", qualifiedByName = "mapTypeUserIdToTipoUsuarioEntity")
    CursoEntity toCurso(CursoDTO cursoDTO);

    @Named("mapTypeUserIdToTipoUsuarioEntity")
    default TipoUsuarioEntity mapTypeUserIdToTipoUsuarioEntity(Integer typeUserId) {
        if (typeUserId == null) {
            return null;
        }
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setIdTipoUsuario(typeUserId);
        return tipoUsuarioEntity;
    }

    @Named("mapProfesorCorreo")
    default String mapProfesorCorreo(UsuarioEntity profesor) {
        if (profesor == null || profesor.getPersona() == null) {
            return null;
        }
        return profesor.getPersona().getCorreo();
    }

    @Named("mapProfesorNombres")
    default String mapProfesorNombres(UsuarioEntity profesor) {
        if (profesor == null || profesor.getPersona() == null) {
            return null;
        }
        return profesor.getPersona().getNombre();
    }

    @Named("mapProfesorApellidos")
    default String mapProfesorApellidos(UsuarioEntity profesor) {
        if (profesor == null || profesor.getPersona() == null) {
            return null;
        }
        return profesor.getPersona().getApellido();
    }

    @Named("mapProfesorNombreCompleto")
    default String mapProfesorNombreCompleto(UsuarioEntity profesor) {
        if (profesor == null || profesor.getPersona() == null) {
            return null;
        }
        String nombres = profesor.getPersona().getNombre();
        String apellidos = profesor.getPersona().getApellido();
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }

    @Named("mapInscripcionesCount")
    default Integer mapInscripcionesCount(List<?> inscripciones) {
        if (inscripciones == null) {
            return 0;
        }
        return inscripciones.size();
    }

}
