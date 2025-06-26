package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.EstadoCursoEntity;

@Repository
public interface EstadoCursoRepository extends JpaRepository<EstadoCursoEntity, Integer> {

}