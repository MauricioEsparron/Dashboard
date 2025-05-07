package pe.com.dashboard.dashboard.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAll() {
        return ResponseEntity.ok(cursoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getById(@PathVariable Integer id) {
        return cursoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.save(cursoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@PathVariable Integer id, @RequestBody CursoDTO cursoDTO) {
        if (!cursoService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cursoDTO.setIdCourse(id);
        return ResponseEntity.ok(cursoService.save(cursoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!cursoService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CursoDTO>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(cursoService.getByStatus(status));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<CursoDTO>> getByProfessor(@PathVariable Integer professorId) {
        return ResponseEntity.ok(cursoService.getByProfessorId(professorId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CursoDTO> changeStatus(@PathVariable Integer id, @RequestParam String status) {
        CursoDTO updated = cursoService.changeStatus(id, status);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

}
