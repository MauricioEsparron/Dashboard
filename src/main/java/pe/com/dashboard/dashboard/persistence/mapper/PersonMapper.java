package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

@Mapper(componentModel = "spring", uses = { PersonStateMapper.class })
public interface PersonMapper {
    @Mappings({
            @Mapping(source = "idPersona", target = "personId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "lastname"),
            @Mapping(source = "telefono", target = "phone"),
            @Mapping(source = "edad", target = "age"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "correo", target = "mail"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "estadoPersona", target = "personState"),
            @Mapping(source = "idEstadoPersona", target = "personStateId"),
    })
    PersonaDTO toPerson(PersonaEntity persona);

    List<PersonaDTO> toPersons(List<PersonaEntity> personas);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(source = "personState", target = "estadoPersona")
    PersonaEntity toPersona(PersonaDTO person);
}
