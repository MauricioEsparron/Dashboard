    package pe.com.dashboard.dashboard.persistence.mapper;

    import java.util.List;
    import org.mapstruct.InheritInverseConfiguration;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.Mappings;

    import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;
    import pe.com.dashboard.dashboard.persistence.model.entity.ComentarioEntity;
    @Mapper(componentModel = "spring", uses = {UserMapper.class})
    public interface CommentMapper {
    
        @Mappings({
            @Mapping(source = "idComentario", target = "commentId"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "usuario", target = "user"),
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "idAvance", target = "advanceId"),
            @Mapping(source = "estado", target = "state")
        })
        ComentarioDTO toComment(ComentarioEntity comentario);
    
        List<ComentarioDTO> toComments(List<ComentarioEntity> comentarios);
    
        @InheritInverseConfiguration
        ComentarioEntity toComentario(ComentarioDTO comment);
    }
    
    