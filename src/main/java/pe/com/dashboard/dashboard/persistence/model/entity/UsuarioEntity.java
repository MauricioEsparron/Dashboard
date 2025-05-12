package pe.com.dashboard.dashboard.persistence.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String contrasenia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuarioEntity tipoUsuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_usuario")
    private EstadoUsuarioEntity estadoUsuario;

    // Métodos helper para manejar los IDs
    public Integer getIdPersona() {
        return persona != null ? persona.getIdPersona() : null;
    }

    public Integer getIdTipoUsuario() {
        return tipoUsuario != null ? tipoUsuario.getIdTipoUsuario() : null;
    }

    public Integer getIdEstadoUsuario() {
        return estadoUsuario != null ? estadoUsuario.getIdEstadoUsuario() : null;
    }
}