package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.EstadoUsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoUsuarioEntity;

@Mapper(componentModel = "spring")
public interface UserStateMapper {
    @Mappings({
            @Mapping(source = "idEstadoUsuario", target = "userStateId"),
            @Mapping(source = "descripcion", target = "description")
    })
    EstadoUsuarioDTO toUserState(EstadoUsuarioEntity estadoUsuario);

    List<EstadoUsuarioDTO> toUserStates(List<EstadoUsuarioEntity> estadosUsuario);
    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    EstadoUsuarioEntity toEstadoUsuario(EstadoUsuarioDTO stateUser);
}
