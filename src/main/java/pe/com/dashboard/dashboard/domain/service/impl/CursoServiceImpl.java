package pe.com.dashboard.dashboard.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;
import pe.com.dashboard.dashboard.persistence.mapper.CursoMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.Curso;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoCurso;
import pe.com.dashboard.dashboard.persistence.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Override
    public List<CursoDTO> getAll() {
        return cursoMapper.toCursoDTOs(cursoRepository.findAll());
    }

    @Override
    public Optional<CursoDTO> getById(Integer id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toCursoDTO);
    }

    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
        Curso curso = cursoMapper.toCurso(cursoDTO);
        return cursoMapper.toCursoDTO(cursoRepository.save(curso));
    }

    @Override
    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public List<CursoDTO> getByStatus(String status) {
        EstadoCurso estado = EstadoCurso.valueOf(status.toUpperCase());
        return cursoMapper.toCursoDTOs(cursoRepository.findByEstado(estado));
    }

    @Override
    public List<CursoDTO> getByProfessorId(Integer professorId) {
        return cursoMapper.toCursoDTOs(cursoRepository.findByIdprofesor(professorId));
    }

    @Override
    public CursoDTO changeStatus(Integer id, String newStatus) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        if (cursoOpt.isEmpty())
            return null;
        Curso curso = cursoOpt.get();
        curso.setEstado(EstadoCurso.valueOf(newStatus.toUpperCase()));
        return cursoMapper.toCursoDTO(cursoRepository.save(curso));
    }

}
