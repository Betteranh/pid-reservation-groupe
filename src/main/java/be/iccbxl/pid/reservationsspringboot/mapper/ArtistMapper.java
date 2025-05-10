package be.iccbxl.pid.reservationsspringboot.mapper;


import be.iccbxl.pid.reservationsspringboot.dto.ArtistDTO;
import be.iccbxl.pid.reservationsspringboot.dto.ArtistTypeDTO;
import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistMapper {
    public ArtistDTO toDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setFirstname(artist.getFirstname());
        dto.setLastname(artist.getLastname());
        return dto;
    }

    public Artist toEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setFirstname(dto.getFirstname());
        artist.setLastname(dto.getLastname());
        return artist;
    }

    public ArtistTypeDTO toArtistTypeDTO(Artist artist) {
        if (artist == null) return null;

        List<String> types = artist.getTypes()
                                    .stream()
                                    .map( Type::getType )
                                    .collect(Collectors.toList());

        return new ArtistTypeDTO(
                artist.getId(),
                artist.getFirstname(),
                artist.getLastname(),
                types
        );
    }

}
