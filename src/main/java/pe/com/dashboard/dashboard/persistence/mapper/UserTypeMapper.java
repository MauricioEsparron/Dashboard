package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.TipoUsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;

@Mapper(componentModel = "spring")
public interface UserTypeMapper {
    @Mappings({
        @Mapping(source = "idTipoUsuario", target = "userType"),
        @Mapping(source = "descripcion", target = "description")
    })
    TipoUsuarioDTO toUserTyper(TipoUsuarioEntity tipoUsuario);

    List<TipoUsuarioDTO> toUserTypes(List<TipoUsuarioEntity> tiposUsuario);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    TipoUsuarioEntity toTipoUsuario(TipoUsuarioDTO userType);
}
