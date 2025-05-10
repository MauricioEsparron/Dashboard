package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    List<CursoEntity> findByEstado(Integer estado);

    List<CursoEntity> findByProfesor_IdUsuario(Integer idUsuario);

    @Query("SELECT c FROM CursoEntity c LEFT JOIN FETCH c.inscripciones")
    List<CursoEntity> findAllWithInscripciones();
}
