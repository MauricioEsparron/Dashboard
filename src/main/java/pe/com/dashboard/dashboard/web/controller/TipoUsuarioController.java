package pe.com.dashboard.dashboard.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.dto.TipoUsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.TipoUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/type-user")
@RequiredArgsConstructor
public class TipoUsuarioController {

    @Autowired
    private final TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuarioDTO>> findAllUserTypes() {
        List<TipoUsuarioDTO> tipos = tipoUsuarioService.findAllTypeUser();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuarioDTO> findUserTypeById(@PathVariable int id) {
        return tipoUsuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<TipoUsuarioDTO> createUserType(@RequestBody TipoUsuarioDTO userTypeDTO) {
        TipoUsuarioDTO created = tipoUsuarioService.createUserType(userTypeDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserType(@PathVariable int id, @RequestBody TipoUsuarioDTO userTypeDTO) {
        tipoUsuarioService.updateUserType(id, userTypeDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable int id) {
        tipoUsuarioService.deleteUserTyper(id);
        return ResponseEntity.noContent().build();
    }
}
