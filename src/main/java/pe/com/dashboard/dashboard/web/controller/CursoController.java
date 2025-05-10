package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<CursoDTO> findAllCourses() {
        return cursoService.findAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<CursoDTO> findCourseById(@PathVariable Integer id) {
        return cursoService.findCourseById(id);
    }

    @GetMapping("/state/{id}")
    public List<CursoDTO> findCourseByState(@PathVariable Integer id) {
        return cursoService.findCourseByState(id);
    }

    @GetMapping("/professor/{id}")
    public List<CursoDTO> findCourseByProfessorId(@PathVariable Integer id) {
        return cursoService.findCourseByProfessorId(id);
    }

    @PostMapping
    public CursoDTO createCourse(@RequestBody CursoDTO course) {
        return cursoService.createCourse(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") int courseId, @RequestBody CursoDTO course) {
        cursoService.updateCourse(courseId, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") int courseId) {
        cursoService.deleteCourse(courseId);
    }

    @PostMapping("/{cursoId}/inscribir/{usuarioId}")
    public ResponseEntity<String> inscribirEstudianteEnCurso(
            @PathVariable Integer cursoId,
            @PathVariable Integer usuarioId) {
        try {
            cursoService.inscribirEstudianteEnCurso(cursoId, usuarioId);
            return ResponseEntity.ok("Estudiante inscrito correctamente al curso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado.");
        }
    }
}
