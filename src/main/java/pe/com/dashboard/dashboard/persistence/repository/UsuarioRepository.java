package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    List<UsuarioEntity> findByEstado(Integer estado);

    List<UsuarioEntity> findByIdTipoUsuario(Integer idTipoUsuario);

}
