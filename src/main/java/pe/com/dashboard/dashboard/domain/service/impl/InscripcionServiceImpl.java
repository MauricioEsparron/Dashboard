package pe.com.dashboard.dashboard.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.InscripcionDTO;
import pe.com.dashboard.dashboard.domain.service.InscripcionService;
import pe.com.dashboard.dashboard.persistence.mapper.InscriptionMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.CursoRepository;
import pe.com.dashboard.dashboard.persistence.repository.InscripcionRepository;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscriptionMapper mapper;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<InscripcionDTO> findAllInscriptions() {
        return mapper.toInscripciones(inscripcionRepository.findAll());
    }

    @Override
    public Optional<InscripcionDTO> findInscriptionById(Integer inscriptionId) {
        return inscripcionRepository.findById(inscriptionId).map(mapper::toInscripcion);
    }

    @Override
    public List<InscripcionDTO> findInscriptionsByStudentId(Integer studentId) {
        return mapper.toInscripciones(inscripcionRepository.findByEstudianteIdUsuario(studentId));
    }

    @Override
    public List<InscripcionDTO> findInscriptionsByCourseId(Integer courseId) {
        return mapper.toInscripciones(inscripcionRepository.findByCursoIdCurso(courseId));
    }

    @Override
    public void enrollStudentToCourse(Integer cursoId, Integer usuarioId) {
        CursoEntity curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("No existe el curso con ID: " + cursoId));

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con ID: " + usuarioId));

        if (usuario.getTipoUsuario() == null ||
                !"ESTUDIANTE".equalsIgnoreCase(usuario.getTipoUsuario().getDescripcion())) {
            throw new SecurityException("El usuario con ID " + usuarioId + " no tiene rol de ESTUDIANTE");
        }

        if (inscripcionRepository.existsByCurso_IdCursoAndEstudiante_IdUsuario(cursoId, usuarioId)) {
            throw new IllegalStateException("El usuario ya está inscrito en este curso");
        }

        InscripcionEntity inscripcion = new InscripcionEntity();
        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(usuario);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setAccesoPermitido(true);

        inscripcionRepository.save(inscripcion);
    }

    @Override
    public InscripcionDTO createInscription(InscripcionDTO inscripcionDTO) {
        InscripcionEntity entity = mapper.toInscripcion(inscripcionDTO);

        // Obtener el curso y el estudiante de los repositorios correspondientes
        CursoEntity curso = cursoRepository.findById(inscripcionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + inscripcionDTO.getCourseId()));
        UsuarioEntity estudiante = usuarioRepository.findById(inscripcionDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException(
                        "Estudiante no encontrado con ID: " + inscripcionDTO.getStudentId()));

        // Asignar manualmente el curso y el estudiante a la entidad
        entity.setCurso(curso);
        entity.setEstudiante(estudiante);

        InscripcionEntity savedEntity = inscripcionRepository.save(entity);
        return mapper.toInscripcion(savedEntity);
    }

    @Override
    public void updateInscription(int inscripcionId, InscripcionDTO inscripcionDTO) {
        InscripcionEntity existingInscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + inscripcionId));

        // Obtener el curso y el estudiante de los repositorios correspondientes
        CursoEntity curso = cursoRepository.findById(inscripcionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + inscripcionDTO.getCourseId()));
        UsuarioEntity estudiante = usuarioRepository.findById(inscripcionDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException(
                        "Estudiante no encontrado con ID: " + inscripcionDTO.getStudentId()));

        // Asignar manualmente el curso y el estudiante a la entidad existente
        existingInscripcion.setCurso(curso);
        existingInscripcion.setEstudiante(estudiante);

        // Actualizar los demás campos manualmente
        existingInscripcion.setAccesoPermitido(inscripcionDTO.getAccessGranted());
        existingInscripcion.setFechaInscripcion(inscripcionDTO.getRegistrationDate());

        inscripcionRepository.save(existingInscripcion);
    }

    @Override
    public void deleteInscription(int inscripcionId) {
        if (inscripcionRepository.existsById(inscripcionId)) {
            inscripcionRepository.deleteById(inscripcionId);
        } else {
            throw new RuntimeException("No se puede eliminar, inscripción no encontrada con ID: " + inscripcionId);
        }
    }
}
