package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.UserMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UserMapper userMapper;

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
    public List<UsuarioDTO> findByState(boolean estado) {
        int estadoInt = estado ? 1 : 0;
        return userMapper.toUsers(usuarioRepository.findByEstado(estadoInt));
    }

    @Override
    public List<UsuarioDTO> findTypeUser(int userType) {
        return userMapper.toUsers(usuarioRepository.findByIdTipoUsuario(userType));
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
    existente.setContrasenia(user.getPassword()); 
    existente.setIdPersona(user.getPersonId()); 
    existente.setIdTipoUsuario(user.getUserTypeId()); 
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
