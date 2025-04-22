package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.EstadoAvanceTesisDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoAvanceTesisEntity;

@Mapper(componentModel = "spring")
public interface ThesisProgressStatusMapper {

    @Mappings({
        @Mapping(source = "idEstadoAvanceTesis", target = "statusProgressThesisId"),
        @Mapping(source = "descripcion", target = "description")
    })
    EstadoAvanceTesisDTO toStatusProgressThesis(EstadoAvanceTesisEntity estadoAvanceTesis);

    List<EstadoAvanceTesisDTO> toStatusProgressThesiss(List<EstadoAvanceTesisEntity> estadoAvanceTesis);

    @InheritInverseConfiguration
    @Mapping(target = "avancesTesis", ignore = true)
    EstadoAvanceTesisEntity toEstadoAvanceTesis(EstadoAvanceTesisDTO statusProgressThesis);

    // 👇 Estos dos métodos solucionan el error de mapear Integer <-> DTO
    default EstadoAvanceTesisDTO map(Integer id) {
        if (id == null) return null;
        EstadoAvanceTesisDTO dto = new EstadoAvanceTesisDTO();
        dto.setStatusProgressThesisId(id); // 👈 asegúrate que este sea el nombre correcto del campo
        return dto;
    }

    default Integer map(EstadoAvanceTesisDTO dto) {
        return dto != null ? dto.getStatusProgressThesisId() : null;
    }
}
