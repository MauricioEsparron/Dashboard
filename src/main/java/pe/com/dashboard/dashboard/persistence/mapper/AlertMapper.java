package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.AlertaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.AlertaEntity;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface AlertMapper {

    @Mappings({
        @Mapping(source = "idAlerta", target = "alertId"),
        @Mapping(source = "descripcion", target = "description"),
        @Mapping(source = "date", target = "date"),
        @Mapping(source = "suscripcion", target = "subscription"),
        @Mapping(source = "idUsuario", target = "userId"),
        @Mapping(source = "usuario", target = "user")
    })
    AlertaDTO toAlert(AlertaEntity alerta);

    List<AlertaDTO> toAlerts(List<AlertaEntity> alertas);

    @InheritInverseConfiguration
    AlertaEntity toAlertEntity(AlertaDTO alertaDTO);
}

