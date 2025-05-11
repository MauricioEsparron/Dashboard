package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;


import java.util.List;
import java.util.Map;
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
public ResponseEntity<?> inscribirEstudiante(
        @PathVariable Integer cursoId,
        @PathVariable Integer usuarioId) {
    
    try {
        cursoService.enrollStudentToCourse(cursoId, usuarioId);
        return ResponseEntity.ok().body(
            Map.of("success", true, "message", "Inscripción exitosa")
        );
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(404).body(
            Map.of("success", false, "error", e.getMessage())
        );
    } catch (SecurityException e) {
        return ResponseEntity.status(403).body(
            Map.of("success", false, "error", e.getMessage())
        );
    } catch (IllegalStateException e) {
        return ResponseEntity.status(409).body(
            Map.of("success", false, "error", e.getMessage())
        );
    }
}}