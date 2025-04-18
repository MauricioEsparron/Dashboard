package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.UserMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.PersonaRepository;
import pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public List<UsuarioDTO> findAllUsers() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return userMapper.toUsers(usuarios);
    }

    @Override
    public Optional<UsuarioEntity> findUserById(int userId) {
        return usuarioRepository.findById(userId);
    }

    @Override
    public List<UsuarioDTO> findByState(int state) {

        return userMapper.toUsers(usuarioRepository.findByEstado(state));
    }

    @Override
    public List<UsuarioDTO> findTypeUser(int userType) {
        return userMapper.toUsers(usuarioRepository.findByTipoUsuarioIdTipoUsuario(userType));
    }
    
    @Override
    public UsuarioDTO createUser(UsuarioDTO user) {
        // Esta línea ahora funcionará porque el método está definido
        if (usuarioRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
    
        // Encriptamos la contraseña antes de guardar
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // Actualiza la contraseña en el DTO
    
        // Mapear el DTO al Entity
        UsuarioEntity entity = userMapper.toUser(user);
        entity.setIdUsuario(null);
        UsuarioEntity saved = usuarioRepository.save(entity);
        return userMapper.toUser(saved);
    }
    

@Override
@Transactional
public void updateUser(int userId, UsuarioDTO user) {
    // Buscar el usuario existente
    UsuarioEntity existente = usuarioRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));

    // Actualizar los campos básicos
    existente.setUsername(user.getUsername());

    if (user.getPassword() != null && !user.getPassword().isBlank()) {
        existente.setContrasenia(passwordEncoder.encode(user.getPassword()));
    }

    // Asociar Persona
    PersonaEntity persona = personaRepository.findById(user.getPersonId())
            .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + user.getPersonId()));
    existente.setPersona(persona);

    // Asociar Tipo de Usuario
    TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(user.getUserTypeId())
            .orElseThrow(() -> new EntityNotFoundException("TipoUsuario no encontrado con ID: " + user.getUserTypeId()));
    existente.setTipoUsuario(tipoUsuario);

    // Estado
    existente.setEstado(user.getActive());

    // Guardar los cambios
    usuarioRepository.save(existente);
}

    @Override
    public void deleteUser(int userId) {
        UsuarioEntity existente = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        usuarioRepository.delete(existente);
    }

}
