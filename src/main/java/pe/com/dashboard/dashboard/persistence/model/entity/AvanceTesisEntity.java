package pe.com.dashboard.dashboard.persistence.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "avance_tesis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvanceTesisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avance_tesis")
    private Integer idAvanceTesis;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", insertable = false, updatable = false)
    private UsuarioEntity estudiante;

    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "archivo_url")
    private String archivoUrl;

    @Column(name = "fecha_subida",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaSubida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_avance_tesis")
    private EstadoAvanceTesisEntity estadoAvanceTesis;

public Integer getIdEstadoAvanceTesis() {
    return estadoAvanceTesis != null ? estadoAvanceTesis.getIdEstadoAvanceTesis() : null;
}

    
} 