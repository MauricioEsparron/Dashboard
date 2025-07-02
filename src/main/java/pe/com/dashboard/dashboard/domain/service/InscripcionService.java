package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.InscripcionDTO;

public interface InscripcionService {

    List<InscripcionDTO> findAllInscriptions();

    Optional<InscripcionDTO> findInscriptionById(Integer id);

    List<InscripcionDTO> findInscriptionsByStudentId(Integer studentId);

    List<InscripcionDTO> findInscriptionsByCourseId(Integer courseId);

    void enrollStudentToCourse(Integer cursoId, Integer usuarioId);

    InscripcionDTO createInscription(InscripcionDTO inscripcion);

    void updateInscription(int inscripcionId, InscripcionDTO inscripcion);

    void deleteInscription(int inscripcionId);
}