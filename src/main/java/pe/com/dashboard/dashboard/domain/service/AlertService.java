package pe.com.dashboard.dashboard.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import pe.com.dashboard.dashboard.domain.dto.AlertaDTO;

public interface AlertService {

    List<AlertaDTO> getAll();

    AlertaDTO getById(Integer id);

    AlertaDTO save(AlertaDTO alertaDTO);

    AlertaDTO update(Integer id, AlertaDTO alertaDTO);

    void delete(Integer id);

    List<AlertaDTO> getByUserId(Integer userId);

    List<AlertaDTO> getByDateRange(LocalDateTime startDate, LocalDateTime endDate);

}
