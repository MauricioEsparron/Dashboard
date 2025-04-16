package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAllUsers() {
        List<UsuarioDTO> users = usuarioService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable("userId") int userId) {
        Optional<UsuarioDTO> user = usuarioService.findUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @GetMapping("/state/{estado}")
    public ResponseEntity<List<UsuarioDTO>> findByState(@PathVariable("estado") int estado) {
        List<UsuarioDTO> users = usuarioService.findByState(estado);
        return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK
    }

    
    @GetMapping("/type/{userType}")
    public ResponseEntity<List<UsuarioDTO>> findTypeUser(@PathVariable("userType") int userType) {
        List<UsuarioDTO> users = usuarioService.findTypeUser(userType);
        return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK
    }


    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO user) {
        UsuarioDTO createdUser = usuarioService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); 
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") int userId, @RequestBody UsuarioDTO user) {
        usuarioService.updateUser(userId, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        usuarioService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}
