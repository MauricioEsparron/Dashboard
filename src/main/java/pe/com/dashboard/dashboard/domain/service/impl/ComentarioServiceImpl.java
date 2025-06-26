package pe.com.dashboard.dashboard.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final CommentMapper mapper;
    @Autowired
    private final ComentarioRepository comentarioRepository;

    @Override
    public List<ComentarioDTO> findAllComments() {
        List<ComentarioEntity> comentarios = comentarioRepository.findAll();
        return mapper.toComments(comentarios);
    }

    @Override
    public Optional<ComentarioDTO> findCommentById(int commentId) {
        return comentarioRepository.findById(commentId)
                .map(mapper::toCommentDTO);
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
        // Si no se recibe fecha desde el DTO, establecer la fecha actual
        if (comment.getDate() == null) {
            comment.setDate(LocalDateTime.now());
        }

        ComentarioEntity comentario = mapper.toComentarioEntity(comment);
        comentario.setIdComentario(null); // Asegurar que se genera nuevo ID

        return mapper.toCommentDTO(comentarioRepository.save(comentario));
    }

    @Override
    public Optional<ComentarioDTO> updateComment(int commentId, ComentarioDTO comment) {
        Optional<ComentarioEntity> optionalComentario = comentarioRepository.findById(commentId);

        if (optionalComentario.isEmpty()) {
            return Optional.empty();
        }

        ComentarioEntity comentarioEncontrado = optionalComentario.get();
        boolean contentChanged = false;

        if (!comentarioEncontrado.getDescripcion().equals(comment.getDescription())) {
            comentarioEncontrado.setDescripcion(comment.getDescription());
            contentChanged = true;
        }

        if (!comentarioEncontrado.getIdUsuario().equals(comment.getUserId())) {
            comentarioEncontrado.setIdUsuario(comment.getUserId());
            contentChanged = true;
        }

        if (!comentarioEncontrado.getIdAvance().equals(comment.getAdvanceId())) {
            comentarioEncontrado.setIdAvance(comment.getAdvanceId());
            contentChanged = true;
        }

        if (!comentarioEncontrado.getEstado().equals(comment.getState())) {
            comentarioEncontrado.setEstado(comment.getState());
            contentChanged = true;
        }

        if (contentChanged) {
            comentarioEncontrado.setFecha(LocalDateTime.now());
        } else if (comment.getDate() != null) {
            comentarioEncontrado.setFecha(comment.getDate());
        }

        ComentarioEntity updatedEntity = comentarioRepository.save(comentarioEncontrado);

        return Optional.of(mapper.toCommentDTO(updatedEntity)); // Usa tu mapper para convertir a DTO
    }

    @Override
    public boolean deleteComment(int commentId) {
        if (!comentarioRepository.existsById(commentId)) {
            return false;
        }

        comentarioRepository.deleteById(commentId);
        return true;
    }

}
