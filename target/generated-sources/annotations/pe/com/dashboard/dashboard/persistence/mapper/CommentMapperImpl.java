package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.ComentarioEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-12T00:17:08-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ComentarioDTO toComment(ComentarioEntity comentario) {
        if ( comentario == null ) {
            return null;
        }

        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setCommentId( comentario.getIdComentario() );
        comentarioDTO.setDescription( comentario.getDescripcion() );
        comentarioDTO.setDate( comentario.getFecha() );
        comentarioDTO.setUser( userMapper.toUser( comentario.getUsuario() ) );
        comentarioDTO.setUserId( comentario.getIdUsuario() );
        comentarioDTO.setAdvanceId( comentario.getIdAvance() );
        comentarioDTO.setState( comentario.getEstado() );

        return comentarioDTO;
    }

    @Override
    public List<ComentarioDTO> toComments(List<ComentarioEntity> comentarios) {
        if ( comentarios == null ) {
            return null;
        }

        List<ComentarioDTO> list = new ArrayList<ComentarioDTO>( comentarios.size() );
        for ( ComentarioEntity comentarioEntity : comentarios ) {
            list.add( toComment( comentarioEntity ) );
        }

        return list;
    }

    @Override
    public ComentarioEntity toComentario(ComentarioDTO comment) {
        if ( comment == null ) {
            return null;
        }

        ComentarioEntity comentarioEntity = new ComentarioEntity();

        comentarioEntity.setIdComentario( comment.getCommentId() );
        comentarioEntity.setDescripcion( comment.getDescription() );
        comentarioEntity.setFecha( comment.getDate() );
        comentarioEntity.setUsuario( userMapper.toUser( comment.getUser() ) );
        comentarioEntity.setIdUsuario( comment.getUserId() );
        comentarioEntity.setIdAvance( comment.getAdvanceId() );
        comentarioEntity.setEstado( comment.getState() );

        return comentarioEntity;
    }
}
