package pe.com.dashboard.dashboard.persistence.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean estado;

}
