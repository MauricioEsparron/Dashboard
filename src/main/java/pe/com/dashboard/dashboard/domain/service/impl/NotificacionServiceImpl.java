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
import java.util.Objects;


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
        NotificacionEntity notificacion = mapper.toEntity(notificacionDTO);
    
        // Fecha actual si no viene
        if (notificacion.getFecha() == null) {
            notificacion.setFecha(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
    
        // Valor por defecto de 'leído'
        if (notificacion.getLeido() == null) {
            notificacion.setLeido(0); // no leído
        }
    
        // Validar existencia del usuario
        if (notificacion.getIdUsuario() != null) {
            UsuarioEntity usuario = userService.findUserById(notificacion.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            notificacion.setUsuario(usuario);
        } else {
            throw new RuntimeException("ID de usuario requerido para guardar notificación");
        }
    
        NotificacionEntity saved = notiRepository.save(notificacion);
        return mapper.toDto(saved);
    }
    

    @Override
    public NotificacionDTO update(Integer id, NotificacionDTO dto) {
        NotificacionEntity notificacionExistente = notiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    
        boolean contenidoModificado = false;
    
        // Comparar campos para actualizar y detectar cambios
        if (!Objects.equals(notificacionExistente.getDescripcion(), dto.getDescription())) {
            notificacionExistente.setDescripcion(dto.getDescription());
            contenidoModificado = true;
        }
    
        if (!Objects.equals(notificacionExistente.getLeido(), dto.getRead())) {
            notificacionExistente.setLeido(dto.getRead());
            contenidoModificado = true;
        }
    
        if (contenidoModificado) {
            notificacionExistente.setFecha(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        } else if (dto.getDate() != null) {
            notificacionExistente.setFecha(dto.getDate().truncatedTo(ChronoUnit.SECONDS));
        }
    
        return mapper.toDto(notiRepository.save(notificacionExistente));
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
