package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.UserMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAllUsers() {
        List<UsuarioDTO> users = usuarioService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable("id") int userId) {
        Optional<UsuarioEntity> userEntity = usuarioService.findUserById(userId);

        if (userEntity.isPresent()) {
            UsuarioDTO userDTO = userMapper.toUser(userEntity.get()); 
            return new ResponseEntity<>(userDTO, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/state/{estado}")
    public ResponseEntity<List<UsuarioDTO>> findUserByState(@PathVariable int estado) {
        List<UsuarioDTO> users = usuarioService.findByState(estado);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<List<UsuarioDTO>> findUserByTypeUser(@PathVariable int userType) {
        List<UsuarioDTO> users = usuarioService.findTypeUser(userType);
        return new ResponseEntity<>(users, HttpStatus.OK); 
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO userDTO) {
        UsuarioDTO createdUser = usuarioService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UsuarioDTO user) {
        usuarioService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        usuarioService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
