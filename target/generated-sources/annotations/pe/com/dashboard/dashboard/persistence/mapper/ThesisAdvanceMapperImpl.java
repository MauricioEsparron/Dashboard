package pe.com.dashboard.dashboard.persistence.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.dashboard.dashboard.domain.dto.AvanceTesisDTO;
import pe.com.dashboard.dashboard.persistence.model.entity.AvanceTesisEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-11T23:59:09-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ThesisAdvanceMapperImpl implements ThesisAdvanceMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public AvanceTesisDTO toThesisAdvance(AvanceTesisEntity avanceTesis) {
        if ( avanceTesis == null ) {
            return null;
        }

        AvanceTesisDTO avanceTesisDTO = new AvanceTesisDTO();

        avanceTesisDTO.setAdvanceId( avanceTesis.getIdAvanceTesis() );
        avanceTesisDTO.setStudent( userMapper.toUser( avanceTesis.getEstudiante() ) );
        avanceTesisDTO.setStudentId( avanceTesis.getIdEstudiante() );
        avanceTesisDTO.setTitle( avanceTesis.getTitulo() );
        avanceTesisDTO.setDescription( avanceTesis.getDescripcion() );
        avanceTesisDTO.setFileUrl( avanceTesis.getArchivoUrl() );
        avanceTesisDTO.setUploadDate( avanceTesis.getFechaSubida() );
        avanceTesisDTO.setRevisionStatus( avanceTesis.getEstadoRevision() );

        return avanceTesisDTO;
    }

    @Override
    public List<AvanceTesisDTO> toThesisAdvances(List<AvanceTesisEntity> avancesTesis) {
        if ( avancesTesis == null ) {
            return null;
        }

        List<AvanceTesisDTO> list = new ArrayList<AvanceTesisDTO>( avancesTesis.size() );
        for ( AvanceTesisEntity avanceTesisEntity : avancesTesis ) {
            list.add( toThesisAdvance( avanceTesisEntity ) );
        }

        return list;
    }

    @Override
    public AvanceTesisEntity toAvanceTesis(AvanceTesisDTO thesisAdvance) {
        if ( thesisAdvance == null ) {
            return null;
        }

        AvanceTesisEntity avanceTesisEntity = new AvanceTesisEntity();

        avanceTesisEntity.setIdAvanceTesis( thesisAdvance.getAdvanceId() );
        avanceTesisEntity.setEstudiante( userMapper.toUser( thesisAdvance.getStudent() ) );
        avanceTesisEntity.setIdEstudiante( thesisAdvance.getStudentId() );
        avanceTesisEntity.setTitulo( thesisAdvance.getTitle() );
        avanceTesisEntity.setDescripcion( thesisAdvance.getDescription() );
        avanceTesisEntity.setArchivoUrl( thesisAdvance.getFileUrl() );
        avanceTesisEntity.setFechaSubida( thesisAdvance.getUploadDate() );
        avanceTesisEntity.setEstadoRevision( thesisAdvance.getRevisionStatus() );

        return avanceTesisEntity;
    }
}
