// package pe.com.dashboard.dashboard.persistence.initializer;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import pe.com.dashboard.dashboard.persistence.model.entity.CursoEntity;
// import
// pe.com.dashboard.dashboard.persistence.model.entity.EstadoAvanceTesisEntity;
// import
// pe.com.dashboard.dashboard.persistence.model.entity.EstadoPersonaEntity;
// import
// pe.com.dashboard.dashboard.persistence.model.entity.EstadoUsuarioEntity;
// import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;
// import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
// import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
// import pe.com.dashboard.dashboard.persistence.repository.CursoRepository;
// import
// pe.com.dashboard.dashboard.persistence.repository.EstadoAvanceTesisRepository;
// import
// pe.com.dashboard.dashboard.persistence.repository.EstadoPersonaRepository;
// import
// pe.com.dashboard.dashboard.persistence.repository.EstadoUsuarioRepository;
// import pe.com.dashboard.dashboard.persistence.repository.PersonaRepository;
// import
// pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
// import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

// @Component
// public class DataInitializer implements CommandLineRunner {

// @Autowired
// private PasswordEncoder passwordEncoder;

// @Autowired
// private PersonaRepository personaRepository;

// @Autowired
// private TipoUsuarioRepository tipoUsuarioRepository;

// @Autowired
// private EstadoUsuarioRepository estadoUsuarioRepository;

// @Autowired
// private EstadoPersonaRepository estadoPersonaRepository;

// @Autowired
// private UsuarioRepository usuarioRepository;

// @Autowired
// private EstadoAvanceTesisRepository estadoAvanceTesisRepository;

// @Autowired
// private CursoRepository cursoRepository;

// @Override
// public void run(String... args) throws Exception {
// insertarDatosIniciales();
// }

// private void insertarDatosIniciales() {

// EstadoPersonaEntity estadoPersona1 = new EstadoPersonaEntity();
// estadoPersona1.setDescripcion("Activo");

// EstadoPersonaEntity estadoPersona2 = new EstadoPersonaEntity();
// estadoPersona2.setDescripcion("Inactivo");

// EstadoPersonaEntity estadoPersona3 = new EstadoPersonaEntity();
// estadoPersona3.setDescripcion("Mantenimiento");

// estadoPersonaRepository.saveAll(List.of(estadoPersona1, estadoPersona2,
// estadoPersona3));

// PersonaEntity persona1 = new PersonaEntity();
// persona1.setNombre("Mauricio");
// persona1.setApellido("Ramirez");
// persona1.setTelefono("983526939");
// persona1.setEdad(21);
// persona1.setDni("72899137");
// persona1.setCorreo("mau@gmail.com");
// persona1.setDireccion("av123");
// persona1.setEstadoPersona(estadoPersona1);

// PersonaEntity persona2 = new PersonaEntity();
// persona2.setNombre("Sofia");
// persona2.setApellido("Cordova");
// persona2.setTelefono("123123123");
// persona2.setEdad(21);
// persona2.setDni("17899137");
// persona2.setCorreo("sofi@gmail.com");
// persona2.setDireccion("av123");
// persona2.setEstadoPersona(estadoPersona2);

// PersonaEntity persona3 = new PersonaEntity();
// persona3.setNombre("Fernanda");
// persona3.setApellido("Cordova");
// persona3.setTelefono("983526939");
// persona3.setEdad(21);
// persona3.setDni("17289137");
// persona3.setCorreo("fer@gmail.com");
// persona3.setDireccion("av123");
// persona3.setEstadoPersona(estadoPersona3);

// PersonaEntity persona4 = new PersonaEntity();
// persona4.setNombre("Juan");
// persona4.setApellido("Gomez");
// persona4.setTelefono("983526939");
// persona4.setEdad(21);
// persona4.setDni("17289917");
// persona4.setCorreo("juan@gmail.com");
// persona4.setDireccion("av123");
// persona4.setEstadoPersona(estadoPersona1);

// PersonaEntity persona5 = new PersonaEntity();
// persona5.setNombre("Carlos");
// persona5.setApellido("Cruz");
// persona5.setTelefono("983526939");
// persona5.setEdad(21);
// persona5.setDni("17289913");
// persona5.setCorreo("carlos@gmail.com");
// persona5.setDireccion("av123");
// persona5.setEstadoPersona(estadoPersona1);

// personaRepository.saveAll(List.of(persona1, persona2, persona3, persona4,
// persona5));

// TipoUsuarioEntity tipoUsuario1 = new TipoUsuarioEntity();
// tipoUsuario1.setDescripcion("Administrador");

// TipoUsuarioEntity tipoUsuario2 = new TipoUsuarioEntity();
// tipoUsuario2.setDescripcion("Estudiante");

// TipoUsuarioEntity tipoUsuario3 = new TipoUsuarioEntity();
// tipoUsuario3.setDescripcion("Asesor de Experiencia");

// TipoUsuarioEntity tipoUsuario4 = new TipoUsuarioEntity();
// tipoUsuario4.setDescripcion("Docente Asesor");

// TipoUsuarioEntity tipoUsuario5 = new TipoUsuarioEntity();
// tipoUsuario5.setDescripcion("Jefe Académico");

// tipoUsuarioRepository.saveAll(List.of(tipoUsuario1, tipoUsuario2,
// tipoUsuario3, tipoUsuario4, tipoUsuario5));

// EstadoUsuarioEntity estadousuario1 = new EstadoUsuarioEntity();
// estadousuario1.setDescripcion("Activo");

// EstadoUsuarioEntity estadousuario2 = new EstadoUsuarioEntity();
// estadousuario2.setDescripcion("Inactivo");

// EstadoUsuarioEntity estadousuario3 = new EstadoUsuarioEntity();
// estadousuario3.setDescripcion("Mantenimiento");

// estadoUsuarioRepository.saveAll(List.of(estadousuario1, estadousuario2,
// estadousuario3));

// UsuarioEntity user1 = new UsuarioEntity();
// user1.setContrasenia(passwordEncoder.encode("admin123"));
// user1.setEstadoUsuario(estadousuario1);
// user1.setPersona(persona1);
// user1.setTipoUsuario(tipoUsuario1);
// user1.setUsername("admin");

// UsuarioEntity user2 = new UsuarioEntity();
// user2.setContrasenia(passwordEncoder.encode("estudiante123"));
// user2.setEstadoUsuario(estadousuario1);
// user2.setPersona(persona2);
// user2.setTipoUsuario(tipoUsuario2);
// user2.setUsername("estudiante");

// UsuarioEntity user3 = new UsuarioEntity();
// user3.setContrasenia(passwordEncoder.encode("asesor123"));
// user3.setEstadoUsuario(estadousuario1);
// user3.setPersona(persona3);
// user3.setTipoUsuario(tipoUsuario3);
// user3.setUsername("asesor");

// UsuarioEntity user4 = new UsuarioEntity();
// user4.setContrasenia(passwordEncoder.encode("docente123"));
// user4.setEstadoUsuario(estadousuario2);
// user4.setPersona(persona4);
// user4.setTipoUsuario(tipoUsuario4);
// user4.setUsername("docente");

// UsuarioEntity user5 = new UsuarioEntity();
// user5.setContrasenia(passwordEncoder.encode("jefe123"));
// user5.setEstadoUsuario(estadousuario3);
// user5.setPersona(persona5);
// user5.setTipoUsuario(tipoUsuario5);
// user5.setUsername("jefe");

// usuarioRepository.saveAll(List.of(user1, user2, user3, user4, user5));

// EstadoAvanceTesisEntity estadoTesis1 = new EstadoAvanceTesisEntity();
// estadoTesis1.setDescripcion("En Proceso");

// EstadoAvanceTesisEntity estadoTesis2 = new EstadoAvanceTesisEntity();
// estadoTesis2.setDescripcion("En Revision");

// EstadoAvanceTesisEntity estadoTesis3 = new EstadoAvanceTesisEntity();
// estadoTesis3.setDescripcion("Corregido");

// EstadoAvanceTesisEntity estadoTesis4 = new EstadoAvanceTesisEntity();
// estadoTesis4.setDescripcion("En Proceso");

// estadoAvanceTesisRepository.saveAll(List.of(estadoTesis1, estadoTesis2,
// estadoTesis3, estadoTesis4));

// try {
// Path uploadsDir =
// Paths.get("src/main/resources/static/uploads/curso").toAbsolutePath().normalize();

// if (!Files.exists(uploadsDir)) {
// Files.createDirectories(uploadsDir);
// System.out.println("Directorio de cursos creado: " + uploadsDir);
// }

// Path imagePath1 = uploadsDir.resolve("curso1.jpg");
// if (!Files.exists(imagePath1)) {
// System.err.println("Advertencia: No se encontró la imagen curso1.jpg en " +
// imagePath1);
// } else {
// CursoEntity curso1 = new CursoEntity();
// curso1.setNombre("Curso 1");
// curso1.setDescripcion("Descripción del Curso 1");
// curso1.setNombreArchivo("curso1.jpg");
// curso1.setTipoMime("image/jpg");
// curso1.setTamanio(Files.size(imagePath1));
// curso1.setRutaArchivo(imagePath1.toString());
// curso1.setUrlPublica("/uploads/curso/curso1.jpg");
// curso1.setAccesoRestringido(1);
// curso1.setEstado(1);
// curso1.setFechaInicio(LocalDateTime.now());
// curso1.setFechaFin(LocalDateTime.now().plusMonths(1));
// curso1.setProfesor(user4);
// curso1.setTipoUsuarioEntity(tipoUsuario1);

// cursoRepository.save(curso1);
// System.out.println("Curso 1 registrado con tamaño: " + Files.size(imagePath1)
// + " bytes");
// }

// Path imagePath2 = uploadsDir.resolve("curso2.jpg");
// if (!Files.exists(imagePath2)) {
// System.err.println("Advertencia: No se encontró la imagen curso2.jpg en " +
// imagePath2);
// } else {
// CursoEntity curso2 = new CursoEntity();
// curso2.setNombre("Curso 2");
// curso2.setDescripcion("Descripción del Curso 2");
// curso2.setNombreArchivo("curso2.jpg");
// curso2.setTipoMime("image/jpg");
// curso2.setTamanio(Files.size(imagePath2));
// curso2.setRutaArchivo(imagePath2.toString());
// curso2.setUrlPublica("/uploads/curso/curso2.jpg");
// curso2.setAccesoRestringido(1);
// curso2.setEstado(1);
// curso2.setFechaInicio(LocalDateTime.now());
// curso2.setFechaFin(LocalDateTime.now().plusMonths(1));
// curso2.setProfesor(user4);
// curso2.setTipoUsuarioEntity(tipoUsuario2);

// cursoRepository.save(curso2);
// System.out.println("Curso 2 registrado con tamaño: " + Files.size(imagePath2)
// + " bytes");
// }

// Path imagePath3 = uploadsDir.resolve("curso3.jpg");
// if (!Files.exists(imagePath3)) {
// System.err.println("Advertencia: No se encontró la imagen curso3.jpg en " +
// imagePath3);
// } else {
// CursoEntity curso3 = new CursoEntity();
// curso3.setNombre("Curso 3");
// curso3.setDescripcion("Descripción del Curso 3");
// curso3.setNombreArchivo("curso3.jpg");
// curso3.setTipoMime("image/jpg");
// curso3.setTamanio(Files.size(imagePath3));
// curso3.setRutaArchivo(imagePath3.toString());
// curso3.setUrlPublica("/uploads/curso/curso3.jpg");
// curso3.setAccesoRestringido(1);
// curso3.setEstado(1);
// curso3.setFechaInicio(LocalDateTime.now());
// curso3.setFechaFin(LocalDateTime.now().plusMonths(1));
// curso3.setProfesor(user4);
// curso3.setTipoUsuarioEntity(tipoUsuario3);

// cursoRepository.save(curso3);
// System.out.println("Curso 3 registrado con tamaño: " + Files.size(imagePath3)
// + " bytes");
// }

// } catch (IOException e) {
// System.err.println("Error al inicializar datos de cursos: " +
// e.getMessage());
// e.printStackTrace();
// }
// }
// }
