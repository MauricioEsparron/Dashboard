package pe.com.dashboard.dashboard.domain.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pe.com.dashboard.dashboard.domain.dto.CursoDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<CursoDTO> findAllCourses();

    Optional<CursoDTO> findCourseById(Integer courseId);

    List<CursoDTO> findCourseByState(int state);

    List<CursoDTO> findCourseByProfessorId(Integer professorId);

    CursoDTO createCourse(CursoDTO course);

    void updateCourse(int courseId, CursoDTO course);

    void deleteCourse(int courseId);

    CursoDTO saveCursoWithImage(CursoDTO cursoDTO, MultipartFile imagen) throws IOException;

    Resource getImageResource(String nombreArchivo) throws IOException;
}
