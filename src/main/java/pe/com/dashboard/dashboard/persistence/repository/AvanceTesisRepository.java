package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity.EstadoRevision;

@Repository
public interface AvanceTesisRepository extends JpaRepository<AvanceTesisEntity, Integer> {
    List<AvanceTesisEntity> findByIdEstudiante(Integer idEstudiante);
    List<AvanceTesisEntity> findByEstadoRevision(EstadoRevision estadoRevision);
    List<AvanceTesisEntity> findByTituloContaining(String titulo);
} 