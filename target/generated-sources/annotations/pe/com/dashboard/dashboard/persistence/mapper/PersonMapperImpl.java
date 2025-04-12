package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-11T22:32:15-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonaDTO toPerson(PersonaEntity persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaDTO personaDTO = new PersonaDTO();

        personaDTO.setPersonId( persona.getIdPersona() );
        personaDTO.setName( persona.getNombre() );
        personaDTO.setLastname( persona.getApellido() );
        personaDTO.setPhone( persona.getTelefono() );
        personaDTO.setAge( persona.getEdad() );
        personaDTO.setDni( persona.getDni() );
        personaDTO.setMail( persona.getCorreo() );
        personaDTO.setAddress( persona.getDireccion() );
        personaDTO.setState( persona.getEstado() );

        return personaDTO;
    }

    @Override
    public List<PersonaDTO> toPersons(List<PersonaEntity> personas) {
        if ( personas == null ) {
            return null;
        }

        List<PersonaDTO> list = new ArrayList<PersonaDTO>( personas.size() );
        for ( PersonaEntity personaEntity : personas ) {
            list.add( toPerson( personaEntity ) );
        }

        return list;
    }

    @Override
    public PersonaEntity toPersona(PersonaDTO person) {
        if ( person == null ) {
            return null;
        }

        PersonaEntity personaEntity = new PersonaEntity();

        personaEntity.setIdPersona( person.getPersonId() );
        personaEntity.setNombre( person.getName() );
        personaEntity.setApellido( person.getLastname() );
        personaEntity.setTelefono( person.getPhone() );
        personaEntity.setEdad( person.getAge() );
        personaEntity.setDni( person.getDni() );
        personaEntity.setCorreo( person.getMail() );
        personaEntity.setDireccion( person.getAddress() );
        personaEntity.setEstado( person.getState() );

        return personaEntity;
    }
}
