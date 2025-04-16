package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    List<UsuarioEntity> findByEstado(Integer estado);

    Optional<UsuarioEntity> findByUsername(String username);

    List<UsuarioEntity> findByTipoUsuarioIdTipoUsuario(Integer idTipoUsuario);
}
