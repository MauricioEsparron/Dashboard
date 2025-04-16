package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    private final UsuarioRepository usuarioRepository;
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
    public Optional<UsuarioDTO> findUserById(int userId) {
        return usuarioRepository.findById(userId)
                .map(userMapper::toUser);
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
        UsuarioEntity entity = userMapper.toUser(user);
        entity.setIdUsuario(null); // Para que se genere nuevo ID
        UsuarioEntity saved = usuarioRepository.save(entity);
        return userMapper.toUser(saved);
    }

    @Override
    public void updateUser(int userId, UsuarioDTO user) {
        UsuarioEntity existente = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        existente.setUsername(user.getUsername());
        existente.setContrasenia(passwordEncoder.encode(user.getPassword()));

        // Buscar y asignar la Persona
        PersonaEntity persona = personaRepository.findById(user.getPersonId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + user.getPersonId()));
        existente.setPersona(persona);

        // Buscar y asignar el Tipo de Usuario
        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(user.getUserTypeId())
                .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado con ID: " + user.getUserTypeId()));
        existente.setTipoUsuario(tipoUsuario);
        existente.setEstado(user.getActive());
        usuarioRepository.save(existente);
    }

    @Override
    public void deleteUser(int userId) {
        UsuarioEntity existente = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        usuarioRepository.delete(existente);
    }

}
