package pe.com.dashboard.dashboard.persistence.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_persona")
    private Integer idPersona;
    
    @Column (name = "nombres")
    private String nombre;

    @Column (name = "apellidos")
    private String apellido;
    
    private String telefono;
    
    private Integer edad;
    
    @Column(nullable = false, length = 8)
    private String dni;
    
    @Column(nullable = false, length = 100)
    private String correo;
    
    @Column(nullable = false, length = 150)
    private String direccion;

    private Integer estado;

    @OneToMany (mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true )
    private List <UsuarioEntity> usuarios;
}
