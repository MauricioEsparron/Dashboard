package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    List<UsuarioEntity> findByEstado(Integer estado);

    Optional<UsuarioEntity> findByUsername(String username);

    List<UsuarioEntity> findByTipoUsuarioIdTipoUsuario(Integer idTipoUsuario);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM UsuarioEntity u JOIN FETCH u.persona WHERE u.idUsuario = :id")
    Optional<UsuarioEntity> findByIdWithPersona(@Param("id") Integer id);

        @Query("SELECT u FROM UsuarioEntity u JOIN FETCH u.tipoUsuario WHERE u.idUsuario = :id")
    Optional<UsuarioEntity> findByIdWithTipoUsuario(@Param("id") Integer id);
}
