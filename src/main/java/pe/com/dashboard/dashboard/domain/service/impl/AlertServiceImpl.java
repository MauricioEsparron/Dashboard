package pe.com.dashboard.dashboard.domain.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.dashboard.dashboard.domain.dto.AlertaDTO;
import pe.com.dashboard.dashboard.domain.dto.UsuarioDTO;
import pe.com.dashboard.dashboard.domain.service.AlertService;
import pe.com.dashboard.dashboard.domain.service.UsuarioService;
import pe.com.dashboard.dashboard.persistence.mapper.AlertMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.AlertaEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.UsuarioEntity;
import pe.com.dashboard.dashboard.persistence.repository.AlertRepository;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private AlertMapper alertMapper;
    @Autowired
    private UsuarioService userService;

    @Override
    public List<AlertaDTO> getAll() {
        return alertMapper.toAlerts(alertRepository.findAll());
    }

    @Override
    public AlertaDTO getById(Integer id) {
        Optional<AlertaEntity> alerta = alertRepository.findById(id);
        return alerta.map(alertMapper::toAlert).orElse(null);
    }

    @Override
    public AlertaDTO save(AlertaDTO alertaDTO) {

        AlertaEntity alertaEntity = alertMapper.toAlertEntity(alertaDTO);
        
        if (alertaEntity.getDate() == null) {
            alertaEntity.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)); // Fecha sin milisegundos
        }
        if (alertaEntity.getIdUsuario() != null) {
           UsuarioEntity usuario = userService.findUserById(alertaEntity.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

 
            alertaEntity.setUsuario(usuario);
        }

        AlertaEntity savedAlert = alertRepository.save(alertaEntity);


        return alertMapper.toAlert(savedAlert);
    }

    @Override
    public void delete(Integer id) {
        alertRepository.deleteById(id);
    }

    @Override
    public List<AlertaDTO> getByUserId(Integer userId) {
        return alertMapper.toAlerts(alertRepository.findByIdUsuario(userId));
    }

    @Override
    public List<AlertaDTO> getByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return alertMapper.toAlerts(alertRepository.findByDateBetween(startDate, endDate));
    }

    @Override
    public AlertaDTO update(Integer id, AlertaDTO alertaDTO) {
        Optional<AlertaEntity> existingAlerta = alertRepository.findById(id);

        if (existingAlerta.isPresent()) {
            AlertaEntity entity = existingAlerta.get();
            // Actualizamos los campos
            entity.setDescripcion(alertaDTO.getDescription());
            entity.setSuscripcion(alertaDTO.getSubscription());

            AlertaEntity updated = alertRepository.save(entity);
            return alertMapper.toAlert(updated);
        }

        return null;
    }
}
