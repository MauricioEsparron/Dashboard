package pe.com.dashboard.dashboard.persistence.mapper;

import org.mapstruct.*;
import pe.com.dashboard.dashboard.domain.dto.NotificacionDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.NotificacionEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface NotificacionMapper {

    @Mappings({
        @Mapping(source = "idNotificacion", target = "notificationId"),
        @Mapping(source = "descripcion", target = "description"),
        @Mapping(source = "fecha", target = "date"),
        @Mapping(source = "leido", target = "read"),
        @Mapping(source = "idUsuario", target = "userId"),
        @Mapping(source = "usuario", target = "user")
    })
    NotificacionDTO toDto(NotificacionEntity entity);

    List<NotificacionDTO> toDtos(List<NotificacionEntity> entities);

    @InheritInverseConfiguration
    NotificacionEntity toEntity(NotificacionDTO dto);
}
