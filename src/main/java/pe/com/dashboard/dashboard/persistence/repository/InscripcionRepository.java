package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionEntity, Integer>{
    
    boolean existsByCurso_IdCursoAndEstudiante_IdUsuario(Integer idCurso, Integer idUsuario);

}
