package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;
import pe.com.dashboard.dashboard.persistence.mapper.CursoMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.CursoRepository;
import pe.com.dashboard.dashboard.persistence.repository.InscripcionRepository;
import pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoMapper mapper;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<CursoDTO> findAllCourses() {
        return mapper.toCursos(cursoRepository.findAll());
    }

    @Override
    public Optional<CursoDTO> findCourseById(Integer courseId) {
        return cursoRepository.findById(courseId).map(curso -> mapper.toCurso(curso));
    }

    @Override
    public List<CursoDTO> findCourseByState(Integer state) {
        return mapper.toCursos(cursoRepository.findByEstado(state));
    }

    @Override
    public List<CursoDTO> findCourseByProfessorId(Integer professorId) {
        return mapper.toCursos(cursoRepository.findByProfesor_IdUsuario(professorId));
    }

    public List<CursoDTO> findCursosConConteo() {
        List<CursoEntity> cursos = cursoRepository.findAllWithInscripciones();
        return mapper.toCursos(cursos);
    }

@Override

public CursoDTO createCourse(CursoDTO course) {
    // 1. Validación de fechas
    if (course.getStartDate().isAfter(course.getEndDate())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de inicio debe ser anterior a la fecha fin");
    }

    // 2. Cargar entidades completas con fetch JOIN
    UsuarioEntity profesor = usuarioRepository.findByIdWithPersona(course.getProfessorId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));
    
    TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(course.getTypeUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de usuario no encontrado"));

    // 3. Crear y guardar
    CursoEntity curso = mapper.toCurso(course);
    curso.setProfesor(profesor);
    curso.setTipoUsuarioEntity(tipoUsuario);

    CursoEntity savedCurso = cursoRepository.save(curso);
    return mapper.toCurso(savedCurso);
}
    @Override
    public void updateCourse(int courseId, CursoDTO course) {
        if (course.getRestrictedAccess() != null && course.getRestrictedAccess() == 1) {
            System.out.println("Actualización de curso con acceso restringido.");
        }

        CursoEntity cursoEncontrado = cursoRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        cursoEncontrado.setNombre(course.getName());
        cursoEncontrado.setDescripcion(course.getDescription());

        UsuarioEntity profesor = usuarioRepository.findById(course.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        cursoEncontrado.setProfesor(profesor);

        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(course.getTypeUserId())
                .orElseThrow(() -> new RuntimeException("Tipo de usuario no encontrado"));
        cursoEncontrado.setTipoUsuarioEntity(tipoUsuario); // Cambiado de setTipoUsuarioEntity a setTipoUsuario

        cursoEncontrado.setEstado(course.getState());
        cursoEncontrado.setFechaInicio(course.getStartDate());
        cursoEncontrado.setFechaFin(course.getEndDate());

        cursoRepository.save(cursoEncontrado);
    }

    @Override
    public void deleteCourse(int courseId) {
        CursoEntity cursoEncontrado = cursoRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        cursoRepository.delete(cursoEncontrado);
    }

    @Override
    public void inscribirEstudianteEnCurso(Integer cursoId, Integer usuarioId) {
        // Verificar si ya está inscrito
        boolean yaInscrito = inscripcionRepository.existsByCurso_IdCursoAndEstudiante_IdUsuario(cursoId, usuarioId);
        if (yaInscrito) {
            throw new RuntimeException("El estudiante ya está inscrito en este curso");
        }
    
        // Buscar el curso
        CursoEntity curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    
        // Buscar el estudiante
        UsuarioEntity estudiante = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    
        // Crear la inscripción
        InscripcionEntity inscripcion = new InscripcionEntity();
        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(estudiante);
    
        // Guardar la inscripción
        inscripcionRepository.save(inscripcion);
    }
}