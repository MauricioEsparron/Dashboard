package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.EstadoUsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.EstadoUsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.UserStateMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.EstadoUsuarioRepository;

@Service
@RequiredArgsConstructor
public class EstadoUsuarioServiceImpl implements EstadoUsuarioService {

    @Autowired
    private final EstadoUsuarioRepository estadoUsuarioRepository;

    @Autowired
    private final UserStateMapper userStateMapper;

    @Override
    public List<EstadoUsuarioDTO> findAllStateUser() {
        List<EstadoUsuarioEntity> entities = estadoUsuarioRepository.findAll();
        return userStateMapper.toUserStates(entities);
    }

    @Override
    public Optional<EstadoUsuarioDTO> findById(int userStateId) {
        return estadoUsuarioRepository.findById(userStateId).map(userStateMapper::toUserState);
    }

    @Override
    public EstadoUsuarioDTO createUserState(EstadoUsuarioDTO userState) {
        EstadoUsuarioEntity entity = userStateMapper.toEstadoUsuario(userState);
        EstadoUsuarioEntity saved = estadoUsuarioRepository.save(entity);
        return userStateMapper.toUserState(saved);
    }

    @Override
    public void updateUserState(int userStateId, EstadoUsuarioDTO userState) {
        Optional<EstadoUsuarioEntity> optional = estadoUsuarioRepository.findById(userStateId);
        if (optional.isPresent()) {
            EstadoUsuarioEntity entity = optional.get();
            entity.setDescripcion(userState.getDescription());
            estadoUsuarioRepository.save(entity);
        } else {
            throw new RuntimeException("Estado usuario no encontrado");
        }
    }

    @Override
    public void deleteUserState(int userStateId) {
        estadoUsuarioRepository.deleteById(userStateId);
    }

}
