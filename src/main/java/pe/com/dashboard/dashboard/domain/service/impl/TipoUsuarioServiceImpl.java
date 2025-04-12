package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.TipoUsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.TipoUsuarioService;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
import pe.com.dashboard.dashboard.persistence.mapper.UserTypeMapper;

@Service
@RequiredArgsConstructor
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    @Autowired
    private final TipoUsuarioRepository tipoUsuarioRepository;
    
    @Autowired
    private final UserTypeMapper userTypeMapper;

    @Override
    public List<TipoUsuarioDTO> findAllTypeUser() {
        List<TipoUsuarioEntity> entities = tipoUsuarioRepository.findAll();
        return userTypeMapper.toUserTypes(entities);
    }

    @Override
    public Optional<TipoUsuarioDTO> findById(int userTypeId) {
        return tipoUsuarioRepository.findById(userTypeId)
                .map(userTypeMapper::toUserTyper);
    }

    @Override
    public TipoUsuarioDTO createUserType(TipoUsuarioDTO userTypeDTO) {
        TipoUsuarioEntity entity = userTypeMapper.toTipoUsuario(userTypeDTO);
        TipoUsuarioEntity saved = tipoUsuarioRepository.save(entity);
        return userTypeMapper.toUserTyper(saved);
    }

    @Override
    public void updateUserType(int userTypeId, TipoUsuarioDTO userTypeDTO) {
        Optional<TipoUsuarioEntity> optional = tipoUsuarioRepository.findById(userTypeId);
        if (optional.isPresent()) {
            TipoUsuarioEntity entity = optional.get();
            entity.setDescripcion(userTypeDTO.getDescription());
            tipoUsuarioRepository.save(entity);
        } else {
            throw new RuntimeException("Tipo de usuario no encontrado con ID: " + userTypeId);
        }
    }

    @Override
    public void deleteUserTyper(int userTypeId) {
        tipoUsuarioRepository.deleteById(userTypeId);
    }
}
