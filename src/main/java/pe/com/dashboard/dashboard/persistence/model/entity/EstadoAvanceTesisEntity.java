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
@Table(name = "estado_avance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoAvanceTesisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idEstadoAvanceTesis;

    private String descripcion;

    @OneToMany(mappedBy = "estadoAvanceTesis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvanceTesisEntity> avancesTesis;
}
