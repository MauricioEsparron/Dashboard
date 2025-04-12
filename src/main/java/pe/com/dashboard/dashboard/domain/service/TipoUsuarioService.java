package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.TipoUsuarioDTO;

public interface TipoUsuarioService {
 
List <TipoUsuarioDTO> findAllTypeUser();

Optional <TipoUsuarioDTO> findById(int userTypeId);

TipoUsuarioDTO createUserType(TipoUsuarioDTO userTypeId);

void updateUserType(int userTypeId, TipoUsuarioDTO userType);

void deleteUserTyper(int userTypeId);

}
