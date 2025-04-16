package pe.com.dashboard.dashboard.domain.service;

import pe.com.dashboard.dashboard.domain.dto.NotificacionDTO;

import java.util.List;

public interface NotificacionService {
    List<NotificacionDTO> getAll();

    NotificacionDTO getById(Integer id);

    NotificacionDTO save(NotificacionDTO notificacionDTO);

    NotificacionDTO update(Integer id, NotificacionDTO notificacionDTO);

    void delete(Integer id);

    List<NotificacionDTO> getByUserId(Integer userId);

}
