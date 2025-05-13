package be.iccbxl.pid.reservationsspringboot.mapper;


import be.iccbxl.pid.reservationsspringboot.dto.LocalityDTO;
import be.iccbxl.pid.reservationsspringboot.dto.RepresentationDTO;
import be.iccbxl.pid.reservationsspringboot.dto.ShowDTO;
import be.iccbxl.pid.reservationsspringboot.model.Locality;
import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//supprimer note perso
// le controller prendra les info d'ici, si jamais on veut changer quelque chose plus tard
// c'est centralisé, plus besoins de passer par les models. limiter les données envoyé aussi

@Component
public class LocationShowMapper {


    public LocalityDTO mapLocalityToLocalityDTO(Locality locality) {
        if (locality == null) {
            return null;
        }
        LocalityDTO localityDTO = new LocalityDTO();
        localityDTO.setId(locality.getId());
        localityDTO.setPostalCode(locality.getPostalCode());
        localityDTO.setLocality( locality.getLocality() );

        if (locality.getLocations() != null) {
            List<String> placeNames = locality.getLocations()
                    .stream()
                    .map(Location::getDesignation)
                    .collect(Collectors.toList());
            localityDTO.setPlaces(placeNames);
        }

        return localityDTO;
    }






}
