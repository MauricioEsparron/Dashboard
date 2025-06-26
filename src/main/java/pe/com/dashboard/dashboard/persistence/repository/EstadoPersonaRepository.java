package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.EstadoPersonaEntity;

@Repository
public interface EstadoPersonaRepository extends JpaRepository<EstadoPersonaEntity, Integer> {

}
