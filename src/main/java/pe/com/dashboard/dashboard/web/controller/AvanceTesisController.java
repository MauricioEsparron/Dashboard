package pe.com.dashboard.dashboard.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.AvanceTesisDTO;
import pe.com.dashboard.dashboard.domain.service.AvanceTesisService;
// import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity.EstadoRevision;

@RestController
@RequestMapping("/thesis-advances")
@RequiredArgsConstructor
public class AvanceTesisController {

    private final AvanceTesisService avanceTesisService;

    @GetMapping
    public ResponseEntity<List<AvanceTesisDTO>> findAllAdvances() {
        return ResponseEntity.ok(avanceTesisService.findAllAdvances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvanceTesisDTO> findAdvanceById(@PathVariable("id") int advanceId) {
        return avanceTesisService.findAdvanceById(advanceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AvanceTesisDTO>> findAdvancesByStudentId(@PathVariable Integer studentId) {
        return ResponseEntity.ok(avanceTesisService.findAdvancesByStudentId(studentId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AvanceTesisDTO>> findAdvancesByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(avanceTesisService.findAdvancesByStatus(status));
    }

    @GetMapping("/title")
    public ResponseEntity<List<AvanceTesisDTO>> findAdvancesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(avanceTesisService.findAdvancesByTitle(title));
    }

    @PostMapping("/create")
    public ResponseEntity<AvanceTesisDTO> createAdvance(@RequestBody AvanceTesisDTO advance) {
        AvanceTesisDTO created = avanceTesisService.createAdvance(advance);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdvance(@PathVariable("id") int advanceId, @RequestBody AvanceTesisDTO advance) {
        avanceTesisService.updateAdvance(advanceId, advance);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvance(@PathVariable("id") int advanceId) {
        avanceTesisService.deleteAdvance(advanceId);
        return ResponseEntity.noContent().build();
    }
}
