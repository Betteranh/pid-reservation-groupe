package be.iccbxl.pid.reservationsspringboot.mapper;


import be.iccbxl.pid.reservationsspringboot.dto.RepresentationDTO;
import be.iccbxl.pid.reservationsspringboot.dto.RepresentationDetailsDTO;
import be.iccbxl.pid.reservationsspringboot.model.Representation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepresentationMapper {
    public RepresentationDTO toDTO(Representation representation) {
        return new RepresentationDTO(
                representation.getId(),
                representation.getShow().getTitle(),
                representation.getScheduledAt(),
                representation.getLocation() != null ? representation.getLocation().getDesignation() : null,
                representation.getAvailableSeats()
        );
    }

    public List<RepresentationDTO> toDTOList(List<Representation> representations) {
        return representations.stream()
                .map(this::toDTO)
                .collect( Collectors.toList() );
    }

}


