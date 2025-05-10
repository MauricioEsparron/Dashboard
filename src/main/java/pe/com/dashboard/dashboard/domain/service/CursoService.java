package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;

public interface CursoService {

    List<CursoDTO> findAllCourses();

    Optional<CursoDTO> findCourseById(Integer id);

    List<CursoDTO> findCourseByState(Integer state);
    
    List<CursoDTO> findCourseByProfessorId(Integer professorId);
    
    List<CursoDTO> findCursosConConteo();

    CursoDTO createCourse(CursoDTO course);
    
    void updateCourse(int courseId, CursoDTO course);

    void deleteCourse(int courseId);

    void inscribirEstudianteEnCurso(Integer cursoId, Integer usuarioId);

}
