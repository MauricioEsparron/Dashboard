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
@Table(name = "estado_persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoPersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_persona")
    private Integer idEstadoPersona;

    @Column(nullable = false, length = 50)
    private String descripcion;

    @OneToMany(mappedBy = "estadoPersona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonaEntity> personas;
}
