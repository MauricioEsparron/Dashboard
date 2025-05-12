package pe.com.dashboard.dashboard.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.EstadoUsuarioEntity;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuarioEntity, Integer>{
    
}
