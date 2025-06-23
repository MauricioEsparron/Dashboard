package pe.com.dashboard.dashboard.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.AlertaDTO;

public interface AlertService {

    List<AlertaDTO> getAll();

    AlertaDTO getById(Integer id);

    AlertaDTO save(AlertaDTO alertaDTO);

    Optional<AlertaDTO> update(Integer id, AlertaDTO alertaDTO);

    boolean delete(Integer id);
 
    List<AlertaDTO> getByUserId(Integer userId);

    List<AlertaDTO> getByDateRange(LocalDateTime startDate, LocalDateTime endDate);

}
