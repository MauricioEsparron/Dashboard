package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;

public interface PersonaService {

    List<PersonaDTO> findAllPersons();

    Optional<PersonaDTO> findPersonById(int personId);

    List<PersonaDTO> findPersonByAge(int age);

    List<PersonaDTO> findPersonByAddress(String address);

    List<PersonaDTO> findPersonByActive (Integer state);

    PersonaDTO createPerson(PersonaDTO person);

    void updatePerson(int personId, PersonaDTO person);

    void deletePerson(int personId);
}
