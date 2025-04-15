package pe.com.dashboard.dashboard.persistence.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha", columnDefinition = "DATETIME(0)")
    private LocalDateTime date;

    @Column(name = "suscripcion")
    private Integer suscripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @Column(name = "id_usuario")
    private Integer idUsuario;
}
