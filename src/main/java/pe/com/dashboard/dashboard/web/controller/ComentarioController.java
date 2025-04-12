package pe.com.dashboard.dashboard.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;
import pe.com.dashboard.dashboard.domain.service.ComentarioService;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> findAllComments() {
        return ResponseEntity.ok(comentarioService.findAllComments());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ComentarioDTO> findCommentById(@PathVariable("id") int commentId) {
        return comentarioService.findCommentById(commentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(comentarioService.findCommentsByUserId(userId));
    }

    @GetMapping("/advance/{advanceId}")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByAdvanceId(@PathVariable("advanceId") Integer advanceId) {
        return ResponseEntity.ok(comentarioService.findCommentsByAdvanceId(advanceId));
    }

    @GetMapping("/state")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByState(@RequestParam("value") Integer state) {
        return ResponseEntity.ok(comentarioService.findCommentsByState(state));
    }

    @PostMapping("/create")
    public ResponseEntity<ComentarioDTO> createComment(@RequestBody ComentarioDTO comment) {
        return ResponseEntity.ok(comentarioService.createComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") int commentId, @RequestBody ComentarioDTO comment) {
        comentarioService.updateComment(commentId, comment);
        return ResponseEntity.noContent().build(); // Devuelve 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int commentId) {
        comentarioService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // Devuelve 204
    }
}
