package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;
import pe.com.dashboard.dashboard.domain.service.ComentarioService;
import pe.com.dashboard.dashboard.persistence.mapper.CommentMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.ComentarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.ComentarioRepository;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {
    
    private final CommentMapper mapper;
    private final ComentarioRepository comentarioRepository;

    @Override
    public List<ComentarioDTO> findAllComments() {
        List<ComentarioEntity> comentarios = comentarioRepository.findAll();
        return mapper.toComments(comentarios);
    }

    @Override
    public Optional<ComentarioDTO> findCommentById(int commentId) {
        return comentarioRepository.findById(commentId)
                .map(mapper::toComment);
    }

    @Override
    public List<ComentarioDTO> findCommentsByUserId(Integer userId) {
        return mapper.toComments(comentarioRepository.findByIdUsuario(userId));
    }

    @Override
    public List<ComentarioDTO> findCommentsByAdvanceId(Integer advanceId) {
        return mapper.toComments(comentarioRepository.findByIdAvance(advanceId));
    }

    @Override
    public List<ComentarioDTO> findCommentsByState(Integer state) {
        return mapper.toComments(comentarioRepository.findByEstado(state));
    }

    @Override
    public ComentarioDTO createComment(ComentarioDTO comment) {
        ComentarioEntity comentario = mapper.toComentario(comment);
        comentario.setIdComentario(null); // Asegurar que se genera nuevo ID
        return mapper.toComment(comentarioRepository.save(comentario));
    }

    @Override
    public void updateComment(int commentId, ComentarioDTO comment) {
        ComentarioEntity comentarioEncontrado = comentarioRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        comentarioEncontrado.setDescripcion(comment.getDescription());
        comentarioEncontrado.setFecha(comment.getDate());
        comentarioEncontrado.setIdUsuario(comment.getUserId());
        comentarioEncontrado.setIdAvance(comment.getAdvanceId());
        comentarioEncontrado.setEstado(comment.getState());

        comentarioRepository.save(comentarioEncontrado);
    }

    @Override
    public void deleteComment(int commentId) {
        ComentarioEntity comentarioEncontrado = comentarioRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        comentarioRepository.delete(comentarioEncontrado);
    }
}
