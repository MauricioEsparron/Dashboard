package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.EstadoUsuarioDTO;

public interface EstadoUsuarioService {

    List<EstadoUsuarioDTO> findAllStateUser();

    Optional<EstadoUsuarioDTO> findById(int userStateId);

    EstadoUsuarioDTO createUserState(EstadoUsuarioDTO userStateId);

    void updateUserState(int userStateId, EstadoUsuarioDTO userState);
    
    void deleteUserState(int userStateId);
    
    
}
