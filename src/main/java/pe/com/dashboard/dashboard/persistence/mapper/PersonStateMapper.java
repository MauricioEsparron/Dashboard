package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.EstadoPersonaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoPersonaEntity;

@Mapper(componentModel = "spring")
public interface PersonStateMapper {
    @Mappings({
            @Mapping(source = "idEstadoPersona", target = "personStateId"),
            @Mapping(source = "descripcion", target = "description")
    })
    EstadoPersonaDTO toPersonState(EstadoPersonaEntity estadoPersona);

    List<EstadoPersonaDTO> toPersonStates(List<EstadoPersonaEntity> estadosPersona);

    @InheritInverseConfiguration
    @Mapping(target = "personas", ignore = true)
    EstadoPersonaEntity toEstadoPersona(EstadoPersonaDTO statePerson);
}
