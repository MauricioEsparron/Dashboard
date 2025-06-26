package pe.com.dashboard.dashboard.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import pe.com.dashboard.dashboard.domain.dto.CursoDTO;
import pe.com.dashboard.dashboard.domain.service.CursoService;
import pe.com.dashboard.dashboard.persistence.mapper.CursoMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoCursoEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.CursoRepository;
import pe.com.dashboard.dashboard.persistence.repository.EstadoCursoRepository;
import pe.com.dashboard.dashboard.persistence.repository.InscripcionRepository;
import pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    @Value("${app.upload.curso.dir}")
    private String uploadDir;

    private final CursoMapper mapper;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    private EstadoCursoRepository estadoCursoRepository;

    @Override
    public List<CursoDTO> findAllCourses() {
        return mapper.toCursos(cursoRepository.findAll());
    }

    @Override
    public Optional<CursoDTO> findCourseById(Integer courseId) {
        return cursoRepository.findById(courseId).map(curso -> mapper.toCurso(curso));
    }

    @Override
    public List<CursoDTO> findCourseByState(int state) {
        return mapper.toCursos(cursoRepository.findByEstadoCursoIdEstadoCurso(state));
    }

    @Override
    public List<CursoDTO> findCourseByProfessorId(Integer professorId) {
        return mapper.toCursos(cursoRepository.findByProfesor_IdUsuario(professorId));
    }

    @Override
    public CursoDTO createCourse(CursoDTO course) {
        if (course.getStartDate().isAfter(course.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de inicio debe ser anterior a la fecha fin");
        }

        UsuarioEntity profesor = usuarioRepository.findByIdWithPersona(course.getProfessorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesor no encontrado"));

        TipoUsuarioEntity tipoUsuario = tipoUsuarioRepository.findById(course.getTypeUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de usuario no encontrado"));

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
        cursoEncontrado.setTipoUsuarioEntity(tipoUsuario);

        EstadoCursoEntity estadoCurso = estadoCursoRepository.findById(course.getCourseStateId())
                .orElseThrow(() -> new RuntimeException("Estado curso no encontrado"));
        cursoEncontrado.setEstadoCurso(estadoCurso);

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
    public CursoDTO saveCursoWithImage(CursoDTO cursoDTO, MultipartFile imagen) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String extension = obtenerExtension(imagen.getOriginalFilename());
        String nombreSanitizado = cursoDTO.getDescription().trim().toLowerCase().replaceAll("[^a-z0-9]+", "-");
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nombreArchivo = nombreSanitizado + "-" + timestamp + "." + extension;

        Path filePath = uploadPath.resolve(nombreArchivo);
        Files.copy(imagen.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        cursoDTO.setFileName(nombreArchivo);
        cursoDTO.setTypeMime(imagen.getContentType());
        cursoDTO.setSize(imagen.getSize());
        cursoDTO.setFilePath(filePath.toString());
        cursoDTO.setPublicUrl("/uploads/cursos/" + nombreArchivo);

        return createCourse(cursoDTO);
    }

    @Override
    public Resource getImageResource(String nombreArchivo) throws IOException {
        try {
            Path filePath = Paths.get(uploadDir).resolve(nombreArchivo).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new IOException("No se pudo leer el archivo: " + nombreArchivo);
            }
        } catch (MalformedURLException ex) {
            throw new IOException("Error al acceder al archivo: " + nombreArchivo, ex);
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        if (nombreArchivo == null)
            return "";
        int lastDot = nombreArchivo.lastIndexOf('.');
        return lastDot == -1 ? "" : nombreArchivo.substring(lastDot + 1);
    }
}
