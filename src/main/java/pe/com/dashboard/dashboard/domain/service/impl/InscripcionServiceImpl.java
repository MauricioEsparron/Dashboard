package pe.com.dashboard.dashboard.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.com.dashboard.dashboard.domain.dto.InscripcionDTO;
import pe.com.dashboard.dashboard.domain.service.InscripcionService;
import pe.com.dashboard.dashboard.persistence.mapper.InscriptionMapper;
import pe.com.dashboard.dashboard.persistence.model.entity.InscripcionEntity;
import pe.com.dashboard.dashboard.persistence.repository.InscripcionRepository;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscriptionMapper mapper;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<InscripcionDTO> findAllInscriptions() {
        return mapper.toInscripciones(inscripcionRepository.findAll());
    }

    @Override
    public Optional<InscripcionDTO> findInscriptionById(Integer inscriptionId) {
        return inscripcionRepository.findById(inscriptionId).map(inscripcion -> mapper.toInscripcion(inscripcion));
    }
    
    @Override
    public InscripcionDTO createInscription(InscripcionDTO inscripcion) {
        InscripcionEntity entity = mapper.toInscripcion(inscripcion);
        InscripcionEntity savedEntity = inscripcionRepository.save(entity);
        return mapper.toInscripcion(savedEntity);
    }

    @Override
    public void updateInscription(int inscripcionId, InscripcionDTO inscripcion) {
        Optional<InscripcionEntity> existingInscripcion = inscripcionRepository.findById(inscripcionId);
        if (existingInscripcion.isPresent()) {
            InscripcionEntity entity = mapper.toInscripcion(inscripcion);
            entity.setIdInscripcion(inscripcionId); // Corregido: se elimina el "null;" que causaba el error
            inscripcionRepository.save(entity);
        } else {
            throw new RuntimeException("Inscripción no encontrada con ID: " + inscripcionId);
        }
    }
    

    @Override
    public void deleteInscription(int inscripcionId) {
        if (inscripcionRepository.existsById(inscripcionId)) {
            inscripcionRepository.deleteById(inscripcionId);
        } else {
            throw new RuntimeException("No se puede eliminar, inscripción no encontrada con ID: " + inscripcionId);
        }
    }
}