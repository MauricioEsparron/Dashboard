package pe.com.dashboard.dashboard.persistence.mapper;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

@Mapper(componentModel = "spring"/*,  uses = {CategoryMapper.class}*/)
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
        @Mapping(source = "estado", target = "state"),
    })
    PersonaDTO toPerson(PersonaEntity persona);

    List<PersonaDTO> toPersons(List<PersonaEntity> personas);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    PersonaEntity toPersona(PersonaDTO person);
}
