package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.AvanceTesisDTO;
import pe.com.dashboard.dashboard.domain.service.AvanceTesisService;
import pe.com.dashboard.dashboard.persistence.mapper.ThesisAdvanceMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity;
import pe.com.dashboard.dashboard.persistence.model.entity.EstadoAvanceTesisEntity;
import pe.com.dashboard.dashboard.persistence.repository.AvanceTesisRepository;
import pe.com.dashboard.dashboard.persistence.repository.EstadoAvanceTesisRepository;

@Service
@RequiredArgsConstructor
public class AvanceTesisServiceImpl implements AvanceTesisService {
    
    @Autowired
    private final ThesisAdvanceMapper mapper;
    
    @Autowired
    private AvanceTesisRepository avanceTesisRepository;

    @Autowired
    private EstadoAvanceTesisRepository estadoAvanceTesisRepository;

    @Override
    public List<AvanceTesisDTO> findAllAdvances() {
        List<AvanceTesisEntity> avances = avanceTesisRepository.findAll();
        return mapper.toThesisAdvance(avances);
    }

    @Override
    public Optional<AvanceTesisDTO> findAdvanceById(int advanceId) {
        return avanceTesisRepository.findById(advanceId).map(avance -> mapper.toThesisAdvance(avance));
    }

    @Override
    public List<AvanceTesisDTO> findAdvancesByStudentId(Integer studentId) {
        return mapper.toThesisAdvance(avanceTesisRepository.findByIdEstudiante(studentId));
    }

    @Override
    public List<AvanceTesisDTO> findAdvancesByStatus(Integer status) {
        // Fetch the EstadoAvanceTesisEntity from the repository using the status
        EstadoAvanceTesisEntity estadoAvanceTesis = estadoAvanceTesisRepository.findById(status)
            .orElseThrow(() -> new RuntimeException("Estado de avance de tesis no encontrado"));
        
        // Now, call the repository method with the EstadoAvanceTesisEntity object
        return mapper.toThesisAdvance(avanceTesisRepository.findByEstadoAvanceTesis(estadoAvanceTesis));
    }
    

    @Override
    public List<AvanceTesisDTO> findAdvancesByTitle(String title) {
        return mapper.toThesisAdvance(avanceTesisRepository.findByTituloContaining(title));
    }

    @Override
    public AvanceTesisDTO createAdvance(AvanceTesisDTO advance) {
        AvanceTesisEntity avanceTesis = mapper.toAvanceTesis(advance);
        avanceTesis.setIdAvanceTesis(null);
        return mapper.toThesisAdvance(avanceTesisRepository.save(avanceTesis));
    }

    @Override
    public void updateAdvance(int advanceId, AvanceTesisDTO advance) {
        AvanceTesisEntity avanceEncontrado = avanceTesisRepository.findById(advanceId)
            .orElseThrow(() -> new RuntimeException("Avance de tesis no encontrado"));
        
        avanceEncontrado.setTitulo(advance.getTitle());
        avanceEncontrado.setDescripcion(advance.getDescription());
        avanceEncontrado.setArchivoUrl(advance.getFileUrl());
        avanceEncontrado.setFechaSubida(advance.getUploadDate());
        
        // Aquí extraemos el ID del estado de avance de tesis
        Integer estadoId = advance.getStatusProgressThesis().getStatusProgressThesisId();
        EstadoAvanceTesisEntity estadoAvanceTesis = estadoAvanceTesisRepository.findById(estadoId)
            .orElseThrow(() -> new RuntimeException("Estado de avance de tesis no encontrado"));
        
        avanceEncontrado.setEstadoAvanceTesis(estadoAvanceTesis);
        avanceEncontrado.setIdEstudiante(advance.getStudentId());
    
        avanceTesisRepository.save(avanceEncontrado);
    }
    

    @Override
    public void deleteAdvance(int advanceId) {
        AvanceTesisEntity avanceEncontrado = avanceTesisRepository.findById(advanceId)
            .orElseThrow(() -> new RuntimeException("Avance de tesis no encontrado"));
        avanceTesisRepository.delete(avanceEncontrado);
    }
} 