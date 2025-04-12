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

    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private PersonaEntity persona;

    @Column(name = "id_persona")
    private Integer idPersona;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", insertable = false, updatable = false)
    private TipoUsuarioEntity tipoUsuario;

    @Column(name = "id_tipo_usuario")
    private Integer idTipoUsuario;

    @Column(nullable = false)
    private Integer estado;
}
