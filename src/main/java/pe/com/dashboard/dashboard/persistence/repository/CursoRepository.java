package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.dashboard.dashboard.persistence.model.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
