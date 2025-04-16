package pe.com.dashboard.dashboard.domain.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.dashboard.dashboard.domain.dto.NotificacionDTO;
import pe.com.dashboard.dashboard.domain.service.NotificacionService;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.NotificacionMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.NotificacionEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.NotificacionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notiRepository;
    @Autowired
    private NotificacionMapper mapper;
    @Autowired
    private UsuarioService userService;

    @Override
    public List<NotificacionDTO> getAll() {
        return mapper.toDtos(notiRepository.findAll());
    }

    @Override
    public NotificacionDTO getById(Integer id) {
        return notiRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public NotificacionDTO save(NotificacionDTO notificacionDTO) {
        NotificacionEntity notificacionEntity = mapper.toEntity(notificacionDTO);

        if (notificacionEntity.getFecha() == null) {
            notificacionEntity.setFecha(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)); // Fecha sin milisegundos
        }

        if (notificacionEntity.getLeido() == null) {
            notificacionEntity.setLeido(0); // No leído por defecto
        }
        if (notificacionEntity.getIdUsuario() != null) {
            UsuarioEntity usuario = userService.findUserById(notificacionEntity.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            notificacionEntity.setUsuario(usuario);
        }
        NotificacionEntity savedNotificacion = notiRepository.save(notificacionEntity);

        return mapper.toDto(savedNotificacion);
    }

    @Override
    public NotificacionDTO update(Integer id, NotificacionDTO dto) {
        Optional<NotificacionEntity> optional = notiRepository.findById(id);
        if (optional.isEmpty())
            return null;

        NotificacionEntity existing = optional.get();
        existing.setDescripcion(dto.getDescription());
        existing.setLeido(dto.getRead());
        existing.setFecha(dto.getDate() != null ? dto.getDate() : existing.getFecha());

        return mapper.toDto(notiRepository.save(existing));
    }

    @Override
    public void delete(Integer id) {
        notiRepository.deleteById(id);
    }

    @Override
    public List<NotificacionDTO> getByUserId(Integer userId) {
        return mapper.toDtos(notiRepository.findByIdUsuario(userId));
    }

}
