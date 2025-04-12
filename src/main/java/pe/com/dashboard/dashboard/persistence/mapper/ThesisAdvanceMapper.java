package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.AvanceTesisDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ThesisAdvanceMapper {
    @Mappings({
        @Mapping(source = "idAvanceTesis", target = "advanceId"),
        @Mapping(source = "estudiante", target = "student"),
        @Mapping(source = "idEstudiante", target = "studentId"),
        @Mapping(source = "titulo", target = "title"),
        @Mapping(source = "descripcion", target = "description"),
        @Mapping(source = "archivoUrl", target = "fileUrl"),
        @Mapping(source = "fechaSubida", target = "uploadDate"),
        @Mapping(source = "estadoRevision", target = "revisionStatus")
    })
    AvanceTesisDTO toThesisAdvance(AvanceTesisEntity avanceTesis);

    List<AvanceTesisDTO> toThesisAdvances(List<AvanceTesisEntity> avancesTesis);

    @InheritInverseConfiguration
    AvanceTesisEntity toAvanceTesis(AvanceTesisDTO thesisAdvance);
} 