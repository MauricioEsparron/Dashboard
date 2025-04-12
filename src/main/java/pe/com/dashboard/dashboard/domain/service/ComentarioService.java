package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.ComentarioDTO;

public interface ComentarioService {
    List<ComentarioDTO> findAllComments();

    Optional<ComentarioDTO> findCommentById(int commentId);

    List<ComentarioDTO> findCommentsByUserId(Integer userId);

    List<ComentarioDTO> findCommentsByAdvanceId(Integer advanceId);

    List<ComentarioDTO> findCommentsByState(Integer state);

    ComentarioDTO createComment(ComentarioDTO comment);

    void updateComment(int commentId, ComentarioDTO comment);
    
    void deleteComment(int commentId);
} 