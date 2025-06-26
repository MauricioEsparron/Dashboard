package pe.com.dashboard.dashboard.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.domain.service.PersonaService;
import pe.com.dashboard.dashboard.persistence.mapper.PersonMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

@RestController
@RequestMapping("/persons")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> findAllPersons() {
        List<PersonaDTO> persons = personaService.findAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonaDTO> findPersonById(@PathVariable("id") int personId) {
        Optional<PersonaEntity> personEntity = personaService.findPersonById(personId);

        if (personEntity.isPresent()) {
            PersonaDTO personDTO = personMapper.toPerson(personEntity.get());
            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/age/{edad}")
    public List<PersonaDTO> findPersonByAge(@PathVariable int age) {
        return personaService.findPersonByAge(age);
    }

    @GetMapping("/address/{direccion}")
    public List<PersonaDTO> findPersonByAddress(@PathVariable String address) {
        return personaService.findPersonByAddress(address);
    }

    @GetMapping("/state/{estado}")
    public ResponseEntity<List<PersonaDTO>> findPersonByState(@PathVariable int state) {
        List<PersonaDTO> persons = personaService.findPersonByState(state);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> createPerson(@RequestBody PersonaDTO personDTO) {
        PersonaDTO createPerson = personaService.createPerson(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("id") int personId, @RequestBody PersonaDTO person) {
        personaService.updatePerson(personId, person);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") int personId) {
        personaService.deletePerson(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
