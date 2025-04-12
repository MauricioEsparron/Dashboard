package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-11T21:00:36-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;

    @Override
    public UsuarioDTO toUser(UsuarioEntity usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setUserId( usuario.getIdUsuario() );
        usuarioDTO.setUsername( usuario.getUsername() );
        usuarioDTO.setPassword( usuario.getContrasenia() );
        usuarioDTO.setPerson( personMapper.toPerson( usuario.getPersona() ) );
        usuarioDTO.setPersonId( usuario.getIdPersona() );
        usuarioDTO.setUserType( userTypeMapper.toUserTyper( usuario.getTipoUsuario() ) );
        usuarioDTO.setUserTypeId( usuario.getIdTipoUsuario() );
        usuarioDTO.setActive( usuario.getEstado() );

        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> toUsers(List<UsuarioEntity> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( UsuarioEntity usuarioEntity : usuarios ) {
            list.add( toUser( usuarioEntity ) );
        }

        return list;
    }

    @Override
    public UsuarioEntity toUser(UsuarioDTO user) {
        if ( user == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setIdUsuario( user.getUserId() );
        usuarioEntity.setUsername( user.getUsername() );
        usuarioEntity.setContrasenia( user.getPassword() );
        usuarioEntity.setPersona( personMapper.toPersona( user.getPerson() ) );
        usuarioEntity.setIdPersona( user.getPersonId() );
        usuarioEntity.setTipoUsuario( userTypeMapper.toTipoUsuario( user.getUserType() ) );
        usuarioEntity.setIdTipoUsuario( user.getUserTypeId() );
        usuarioEntity.setEstado( user.getActive() );

        return usuarioEntity;
    }
}
