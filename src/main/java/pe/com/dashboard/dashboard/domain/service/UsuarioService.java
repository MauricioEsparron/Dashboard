package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;

public interface UsuarioService {

List<UsuarioDTO> findAllUsers();

Optional<UsuarioEntity> findUserById(int userId);

List<UsuarioDTO> findByState(boolean estado);

List<UsuarioDTO> findTypeUser(int userType);

UsuarioDTO createUser(UsuarioDTO user);

void updateUser(int userId, UsuarioDTO user);

void deleteUser(int userId);

}
