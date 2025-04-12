package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.domain.service.PersonaService;
import pe.com.dashboard.dashboard.persistence.mapper.PersonMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;
import pe.com.dashboard.dashboard.persistence.repository.PersonaRepository;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    
    
    @Autowired
    private final PersonMapper mapper;
    
    @Autowired
    public PersonaRepository personaRepository;
    
    @Override
    public List<PersonaDTO> findAllPersons() {
        List<PersonaEntity> personas = personaRepository.findAll();
        return mapper.toPersons((personas));
    }

    @Override
    public Optional<PersonaDTO> findPersonById(int personId) {
        return personaRepository.findById(personId).map(persona -> mapper.toPerson(persona));
    }

    @Override
    public List<PersonaDTO> findPersonByAge(int age) {
        return mapper.toPersons(personaRepository.findByEdad(age));
    }
    
    @Override
    public List<PersonaDTO> findPersonByAddress(String address) {
        return mapper.toPersons(personaRepository.findByDireccion(address));
    }
    
    @Override
    public List<PersonaDTO> findPersonByActive(Integer state) {
        return mapper.toPersons(personaRepository.findByEstado(state));
    }

    @Override
    public PersonaDTO createPerson(PersonaDTO person) {
        PersonaEntity persona = mapper.toPersona(person);
        persona.setIdPersona(null);
        return mapper.toPerson(personaRepository.save(persona));
    }

    @Override
    public void updatePerson(int personId, PersonaDTO person) {
        PersonaEntity personaEncontrada = personaRepository.findById(personId).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        
        personaEncontrada.setNombre(person.getName());
        personaEncontrada.setApellido(person.getLastname());
        personaEncontrada.setTelefono(person.getPhone());
        personaEncontrada.setEdad(person.getAge());
        personaEncontrada.setDni(person.getDni());
        personaEncontrada.setCorreo(person.getMail());
        personaEncontrada.setDireccion(person.getAddress());
        personaEncontrada.setEstado(person.getState());

        personaRepository.save(personaEncontrada);
    }

    @Override
    public void deletePerson(int personId) {
        PersonaEntity  personaEncontrada = personaRepository.findById(personId).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaRepository.delete(personaEncontrada);
    }

}
