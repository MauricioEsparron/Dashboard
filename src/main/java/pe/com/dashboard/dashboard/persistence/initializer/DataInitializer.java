//  package pe.com.dashboard.dashboard.persistence.initializer;

//  import org.springframework.beans.factory.annotation.Autowired;
//  import org.springframework.boot.CommandLineRunner;
//  import org.springframework.security.crypto.password.PasswordEncoder;
//  import org.springframework.stereotype.Component;

//  import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;
//  import pe.com.dashboard.dashboard.persistence.model.entity.TipoUsuarioEntity;
//  import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
//  import pe.com.dashboard.dashboard.persistence.repository.PersonaRepository;
//  import pe.com.dashboard.dashboard.persistence.repository.TipoUsuarioRepository;
//  import pe.com.dashboard.dashboard.persistence.repository.UsuarioRepository;

//  @Component
//  public class DataInitializer implements CommandLineRunner {

//      @Autowired
//      private PasswordEncoder passwordEncoder;

//      @Autowired
//      private PersonaRepository personaRepository;

//      @Autowired
//      private TipoUsuarioRepository tipoUsuarioRepository;

//      @Autowired
//      private UsuarioRepository usuarioRepository;

//      @Override
//      public void run(String... args) throws Exception {
//          insertarDatosIniciales();
//      }

//      private void insertarDatosIniciales() {


//          PersonaEntity persona1 = new PersonaEntity();
//          persona1.setNombre("Mauricio");
//          persona1.setApellido("Ramirez");
//          persona1.setTelefono("983526939");
//          persona1.setEdad(21);
//          persona1.setDni("72899137");
//          persona1.setCorreo("mau@gmail.com");
//          persona1.setDireccion("av123");
//          persona1.setEstado(1);
       
//          PersonaEntity persona2 = new PersonaEntity();
//          persona2.setNombre("Sofia");
//          persona2.setApellido("Cordova");
//          persona2.setTelefono("123123123");
//          persona2.setEdad(21);
//          persona2.setDni("17899137");
//          persona2.setCorreo("sofi@gmail.com");
//          persona2.setDireccion("av123");
//          persona2.setEstado(2);

//          PersonaEntity persona3 = new PersonaEntity();
//          persona3.setNombre("Fernanda");
//          persona3.setApellido("Cordova");
//          persona3.setTelefono("983526939");
//          persona3.setEdad(21);
//          persona3.setDni("17289137");
//          persona3.setCorreo("fer@gmail.com");
//          persona3.setDireccion("av123");
//          persona3.setEstado(3);

//          PersonaEntity persona4 = new PersonaEntity();
//          persona4.setNombre("Juan");
//          persona4.setApellido("Gomez");
//          persona4.setTelefono("983526939");
//          persona4.setEdad(21);
//          persona4.setDni("17289917");
//          persona4.setCorreo("juan@gmail.com");
//          persona4.setDireccion("av123");
//          persona4.setEstado(4);

//          PersonaEntity persona5 = new PersonaEntity();
//          persona5.setNombre("Carlos");
//          persona5.setApellido("Cruz");
//          persona5.setTelefono("983526939");
//          persona5.setEdad(21);
//          persona5.setDni("17289913");
//          persona5.setCorreo("carlos@gmail.com");
//          persona5.setDireccion("av123");
//          persona5.setEstado(5);

//          personaRepository.save(persona1);
//          personaRepository.save(persona2);
//          personaRepository.save(persona3);
//          personaRepository.save(persona4);
//          personaRepository.save(persona5);

//          TipoUsuarioEntity tipoUsuario1 = new TipoUsuarioEntity();
//          tipoUsuario1.setDescripcion("Administrador");

//          TipoUsuarioEntity tipoUsuario2 = new TipoUsuarioEntity();
//          tipoUsuario2.setDescripcion("Estudiante");

//          TipoUsuarioEntity tipoUsuario3 = new TipoUsuarioEntity();
//          tipoUsuario3.setDescripcion("Asesor de Experiencia");

//          TipoUsuarioEntity tipoUsuario4 = new TipoUsuarioEntity();
//          tipoUsuario4.setDescripcion("Docente Asesor");

//          TipoUsuarioEntity tipoUsuario5 = new TipoUsuarioEntity();
//          tipoUsuario5.setDescripcion("Jefe Académico");

//          tipoUsuarioRepository.save(tipoUsuario1);
//          tipoUsuarioRepository.save(tipoUsuario2);
//          tipoUsuarioRepository.save(tipoUsuario3);
//          tipoUsuarioRepository.save(tipoUsuario4);
//          tipoUsuarioRepository.save(tipoUsuario5);

//          UsuarioEntity user1 = new UsuarioEntity();
//          user1.setContrasenia(passwordEncoder.encode("admin123"));
//          user1.setEstado(1);
//          user1.setPersona(persona1);;
//          user1.setTipoUsuario(tipoUsuario1);
//          user1.setUsername("admin");

//          UsuarioEntity user2 = new UsuarioEntity();
//          user2.setContrasenia(passwordEncoder.encode("estudiante123"));
//          user2.setEstado(1);
//          user2.setPersona(persona2);
//          user2.setTipoUsuario(tipoUsuario2);
//          user2.setUsername("estudiante");

//          UsuarioEntity user3 = new UsuarioEntity();
//          user3.setContrasenia(passwordEncoder.encode("asesor123"));
//          user3.setEstado(1);        
//          user3.setPersona(persona3);
//          user3.setTipoUsuario(tipoUsuario3);
//          user3.setUsername("asesor");

//          UsuarioEntity user4 = new UsuarioEntity();
//          user4.setContrasenia(passwordEncoder.encode("docente123"));
//          user4.setEstado(1);
//          user4.setPersona(persona4);
//          user4.setTipoUsuario(tipoUsuario4);
//          user4.setUsername("docente");

//          UsuarioEntity user5 = new UsuarioEntity();
//          user5.setContrasenia(passwordEncoder.encode("jefe123"));
//          user5.setEstado(1);
//          user5.setPersona(persona5);
//          user5.setTipoUsuario(tipoUsuario5);
//          user5.setUsername("jefe");

//          usuarioRepository.save(user1);
//          usuarioRepository.save(user2);
//          usuarioRepository.save(user3);
//          usuarioRepository.save(user4);
//          usuarioRepository.save(user5);
//      }

//  }
