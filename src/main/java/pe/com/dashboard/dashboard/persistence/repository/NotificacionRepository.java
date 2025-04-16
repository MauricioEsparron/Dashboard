package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dashboard.dashboard.persistence.model.entity.NotificacionEntity;

import java.util.List;

public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Integer> {
    List<NotificacionEntity> findByIdUsuario(Integer idUsuario);

}
