package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import pe.com.dashboard.dashboard.domain.dto.InscripcionDTO;
import pe.com.dashboard.dashboard.domain.service.InscripcionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<InscripcionDTO> findAllEnrollments() {
        return inscripcionService.findAllInscriptions();
    }

    @GetMapping("/{id}")
    public Optional<InscripcionDTO> findEnrollmentById(@PathVariable Integer id) {
        return inscripcionService.findInscriptionById(id);
    }

    @GetMapping("/student/{estudianteId}")
    public List<InscripcionDTO> findEnrollmentsByStudentId(@PathVariable Integer estudianteId) {
        return inscripcionService.findInscriptionsByStudentId(estudianteId);
    }

    @GetMapping("/course/{cursoId}")
    public List<InscripcionDTO> findEnrollmentsByCourseId(@PathVariable Integer cursoId) {
        return inscripcionService.findInscriptionsByCourseId(cursoId);
    }

    @PostMapping
    public InscripcionDTO createEnrollment(@RequestBody InscripcionDTO enrollmentDTO) {
        return inscripcionService.createInscription(enrollmentDTO);
    }

    @PutMapping("/{id}")
    public void updateEnrollment(@PathVariable Integer id, @RequestBody InscripcionDTO enrollmentDTO) {
        inscripcionService.updateInscription(id, enrollmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Integer id) {
        inscripcionService.deleteInscription(id);
    }

    @PostMapping("/{cursoId}/inscribir/{usuarioId}")
    public ResponseEntity<?> inscribirEstudiante(
            @PathVariable Integer cursoId,
            @PathVariable Integer usuarioId) {

        try {
            inscripcionService.enrollStudentToCourse(cursoId, usuarioId);
            return ResponseEntity.ok().body(
                    Map.of("success", true, "message", "Inscripción exitosa"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(
                    Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(
                    Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(
                    Map.of("success", false, "error", e.getMessage()));
        }
    }
}
