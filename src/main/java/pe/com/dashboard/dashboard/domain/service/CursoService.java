package pe.com.dashboard.dashboard.domain.service;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<CursoDTO> getAll();

    Optional<CursoDTO> getById(Integer id);

    CursoDTO save(CursoDTO cursoDTO);

    void delete(Integer id);

    List<CursoDTO> getByStatus(String status);

    List<CursoDTO> getByProfessorId(Integer professorId);

    CursoDTO changeStatus(Integer id, String newStatus);

}
