package pe.com.dashboard.dashboard.domain.service;

import java.util.List;
import java.util.Optional;

import pe.com.dashboard.dashboard.domain.dto.AvanceTesisDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity.EstadoRevision;

public interface AvanceTesisService {
    List<AvanceTesisDTO> findAllAdvances();

    Optional<AvanceTesisDTO> findAdvanceById(int advanceId);

    List<AvanceTesisDTO> findAdvancesByStudentId(Integer studentId);

    List<AvanceTesisDTO> findAdvancesByStatus(EstadoRevision status);

    List<AvanceTesisDTO> findAdvancesByTitle(String title);

    AvanceTesisDTO createAdvance(AvanceTesisDTO advance);

    void updateAdvance(int advanceId, AvanceTesisDTO advance);
    
    void deleteAdvance(int advanceId);
} 