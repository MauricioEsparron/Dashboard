package pe.com.dashboard.dashboard.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dashboard.dashboard.persistence.model.entity.AlertaEntity;

public interface AlertRepository extends JpaRepository<AlertaEntity, Integer> {

    // Buscar por ID de usuario
    List<AlertaEntity> findByIdUsuario(Integer idUsuario);

    // Buscar por rango de fechas
    List<AlertaEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}