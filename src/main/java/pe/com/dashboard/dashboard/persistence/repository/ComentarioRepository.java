package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.ComentarioEntity;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Integer> {
    List<ComentarioEntity> findByIdUsuario(Integer idUsuario);
    List<ComentarioEntity> findByIdAvance(Integer idAvance);
    List<ComentarioEntity> findByEstado(Integer estado);
} 