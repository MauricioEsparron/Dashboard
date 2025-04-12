package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.com.dashboard.dashboard.domain.dto.TipoUsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-11T10:05:19-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserTypeMapperImpl implements UserTypeMapper {

    @Override
    public TipoUsuarioDTO toUserTyper(TipoUsuarioEntity tipoUsuario) {
        if ( tipoUsuario == null ) {
            return null;
        }

        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();

        tipoUsuarioDTO.setUserType( tipoUsuario.getIdTipoUsuario() );
        tipoUsuarioDTO.setDescription( tipoUsuario.getDescripcion() );

        return tipoUsuarioDTO;
    }

    @Override
    public List<TipoUsuarioDTO> toUserTypes(List<TipoUsuarioEntity> tiposUsuario) {
        if ( tiposUsuario == null ) {
            return null;
        }

        List<TipoUsuarioDTO> list = new ArrayList<TipoUsuarioDTO>( tiposUsuario.size() );
        for ( TipoUsuarioEntity tipoUsuarioEntity : tiposUsuario ) {
            list.add( toUserTyper( tipoUsuarioEntity ) );
        }

        return list;
    }

    @Override
    public TipoUsuarioEntity toTipoUsuario(TipoUsuarioDTO userType) {
        if ( userType == null ) {
            return null;
        }

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();

        tipoUsuarioEntity.setIdTipoUsuario( userType.getUserType() );
        tipoUsuarioEntity.setDescripcion( userType.getDescription() );

        return tipoUsuarioEntity;
    }
}
