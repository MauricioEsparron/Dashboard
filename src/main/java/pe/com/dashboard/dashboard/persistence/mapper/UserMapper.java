package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

@Mapper(componentModel = "spring", uses = {PersonMapper.class,UserTypeMapper.class})
public interface UserMapper {
    @Mappings({
        @Mapping(source = "idUsuario", target = "userId"),
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "contrasenia", target = "password"),
        @Mapping(source = "persona", target = "person"),
        @Mapping(source = "idPersona", target = "personId", ignore = false),
        @Mapping(source = "tipoUsuario", target = "userType"),
        @Mapping(source = "idTipoUsuario", target = "userTypeId", ignore = false),
        @Mapping(source = "estado", target = "active"),
        @Mapping(source = "persona.nombre", target = "name"),
    })
    UsuarioDTO toUser(UsuarioEntity usuario);

    List<UsuarioDTO> toUsers(List<UsuarioEntity> usuarios);

    @InheritInverseConfiguration
    @Mapping(source = "person", target = "persona")
    @Mapping(source = "userType", target = "tipoUsuario")
    UsuarioEntity toUser(UsuarioDTO user);
    

}
