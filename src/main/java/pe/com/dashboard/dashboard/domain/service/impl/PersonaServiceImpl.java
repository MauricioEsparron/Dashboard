package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.domain.service.PersonaService;
import pe.com.dashboard.dashboard.persistence.mapper.PersonMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoPersonaEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;
import pe.com.dashboard.dashboard.persistence.repository.EstadoPersonaRepository;
import pe.com.dashboard.dashboard.persistence.repository.PersonaRepository;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private final PersonaRepository personaRepository;

    @Autowired
    private final PersonMapper personMapper;

    @Autowired
    private EstadoPersonaRepository estadoPersonaRepository;

    @Override
    public List<PersonaDTO> findAllPersons() {
        List<PersonaEntity> personas = personaRepository.findAll();
        return personMapper.toPersons((personas));
    }

    @Override
    public Optional<PersonaEntity> findPersonById(int personId) {
        return personaRepository.findById(personId);
    }

    @Override
    public List<PersonaDTO> findPersonByAge(int age) {
        return personMapper.toPersons(personaRepository.findByEdad(age));
    }

    @Override
    public List<PersonaDTO> findPersonByAddress(String address) {
        return personMapper.toPersons(personaRepository.findByDireccion(address));
    }

    @Override
    public List<PersonaDTO> findPersonByState(int personState) {
        return personMapper.toPersons(personaRepository.findByEstadoPersonaIdEstadoPersona(personState));
    }

    @Override
    public PersonaDTO createPerson(PersonaDTO person) {
        PersonaEntity persona = personMapper.toPersona(person);
        persona.setIdPersona(null);
        return personMapper.toPerson(personaRepository.save(persona));
    }

    @Override
    public void updatePerson(int personId, PersonaDTO person) {
        PersonaEntity personaEncontrada = personaRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        personaEncontrada.setNombre(person.getName());
        personaEncontrada.setApellido(person.getLastname());
        personaEncontrada.setTelefono(person.getPhone());
        personaEncontrada.setEdad(person.getAge());
        personaEncontrada.setDni(person.getDni());
        personaEncontrada.setCorreo(person.getMail());
        personaEncontrada.setDireccion(person.getAddress());

        EstadoPersonaEntity estadoPersona = estadoPersonaRepository.findById(person.getPersonStateId()).orElseThrow(
                () -> new EntityNotFoundException("EstadoPersona no encontrado con ID: " + person.getPersonId()));
        personaEncontrada.setEstadoPersona(estadoPersona);

        personaRepository.save(personaEncontrada);
    }

    @Override
    public void deletePerson(int personId) {
        PersonaEntity personaEncontrada = personaRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaRepository.delete(personaEncontrada);
    }

}
