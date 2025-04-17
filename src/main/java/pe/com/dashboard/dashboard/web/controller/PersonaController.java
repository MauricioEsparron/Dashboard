package pe.com.dashboard.dashboard.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.dto.PersonaDTO;
import pe.com.dashboard.dashboard.domain.service.PersonaService;

@RestController
@RequestMapping("/persons")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<PersonaDTO> findAllPersons() {
        return personaService.findAllPersons();
    }

    @GetMapping("/id/{id}")
    public Optional<PersonaDTO> findPersonById(@PathVariable("id") int personId) {
        return personaService.findPersonById(personId);
    }

    @GetMapping("/age/{value}")
    public List<PersonaDTO> findPersonByAge(@PathVariable("value") int age) {
        return personaService.findPersonByAge(age);
    }    

    @GetMapping("/address/{value}")
    public List<PersonaDTO> findPersonByAddress(@PathVariable("value") String address) {
        return personaService.findPersonByAddress(address);
    }

    @GetMapping("/active/{value}")
    public List<PersonaDTO> findPersonByActive(@PathVariable("value") Integer state) {
        return personaService.findPersonByActive(state);
    }

    @PostMapping("/createPerson")
    public PersonaDTO createPerson(@RequestBody PersonaDTO person) {
        return personaService.createPerson(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") int personId, @RequestBody PersonaDTO person) {
        personaService.updatePerson(personId, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") int personId) {
        personaService.deletePerson(personId);
    }
}
