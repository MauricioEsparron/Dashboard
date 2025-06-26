package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

public interface PersonaService {

    List<PersonaDTO> findAllPersons();

    Optional<PersonaEntity> findPersonById(int personId);

    List<PersonaDTO> findPersonByAge(int age);

    List<PersonaDTO> findPersonByAddress(String address);

    List<PersonaDTO> findPersonByState(int estado);

    PersonaDTO createPerson(PersonaDTO person);

    void updatePerson(int personId, PersonaDTO person);

    void deletePerson(int personId);
}
