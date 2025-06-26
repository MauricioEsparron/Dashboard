package pe.com.dashboard.dashboard.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;
import pe.com.dashboard.dashboard.domain.service.ComentarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> findAllComments() {
        try {
            List<ComentarioDTO> comentarios = comentarioService.findAllComments();
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> findCommentById(@PathVariable("id") int commentId) {
        try {
            Optional<ComentarioDTO> comment = comentarioService.findCommentById(commentId);
            return comment.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByUserId(@PathVariable Integer userId) {
        try {
            List<ComentarioDTO> comentarios = comentarioService.findCommentsByUserId(userId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/advance/{advanceId}")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByAdvanceId(@PathVariable Integer advanceId) {
        try {
            List<ComentarioDTO> comentarios = comentarioService.findCommentsByAdvanceId(advanceId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/state")
    public ResponseEntity<List<ComentarioDTO>> findCommentsByState(@RequestParam("value") Integer state) {
        try {
            List<ComentarioDTO> comentarios = comentarioService.findCommentsByState(state);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> createComment(@RequestBody ComentarioDTO comment) {
        try {
            ComentarioDTO saved = comentarioService.createComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComment(@PathVariable("id") int commentId,
                                                       @RequestBody ComentarioDTO comment) {
        try {
            Optional<ComentarioDTO> updated = comentarioService.updateComment(commentId, comment);
            return updated.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int commentId) {
        try {
            boolean deleted = comentarioService.deleteComment(commentId);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
