package pe.com.dashboard.dashboard.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.dashboard.dashboard.persistence.model.entity.PersonaEntity;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>{

  List<PersonaEntity> findByEdad(int edad);
  List<PersonaEntity> findByDireccion(String direccion);
  List<PersonaEntity> findByEstado(Integer estado);
    
} 