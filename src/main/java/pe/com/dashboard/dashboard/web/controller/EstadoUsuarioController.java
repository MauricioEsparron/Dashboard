package pe.com.dashboard.dashboard.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.EstadoUsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.EstadoUsuarioService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/state-user")
@RequiredArgsConstructor
public class EstadoUsuarioController {

    @Autowired
    private final EstadoUsuarioService estadoUsuarioService;

    @GetMapping
    public ResponseEntity<List<EstadoUsuarioDTO>> findAllUserStates() {
        List<EstadoUsuarioDTO> states = estadoUsuarioService.findAllStateUser();
        return ResponseEntity.ok(states);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoUsuarioDTO> findUserStateById(@PathVariable int id) {
        return estadoUsuarioService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstadoUsuarioDTO> createUserState(@RequestBody EstadoUsuarioDTO userStateDTO) {
        EstadoUsuarioDTO created = estadoUsuarioService.createUserState(userStateDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserState(@PathVariable int id, @RequestBody EstadoUsuarioDTO userStateDTO) {
        estadoUsuarioService.updateUserState(id, userStateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserState(@PathVariable int id) {
        estadoUsuarioService.deleteUserState(id);
        return ResponseEntity.noContent().build();
    }
}
