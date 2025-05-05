package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.dto.*;
import be.iccbxl.pid.reservationsspringboot.model.Price;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Component
public class ShowMapper {
    @Autowired
    private LocationService locationService;
    @Autowired
    private PriceService priceService;

    /**
     * Entité → DTO (extrait les champs que vous souhaitez exposer)
     */
    public ShowDto toDto(Show s) {
        ShowDto dto = new ShowDto();
        dto.setId(s.getId());
        dto.setSlug(s.getSlug());
        dto.setTitle(s.getTitle());
        dto.setDescription(s.getDescription());
        dto.setPosterUrl(s.getPosterUrl());
        dto.setBookable(s.isBookable());
        if (s.getLocation() != null) {
            // on stocke l’ID du lieu au lieu de son nom
            dto.setLocationId(s.getLocation().getId());
        }
        // on remplit la liste “prices” qui existe déjà dans ShowDto
        dto.setPrices(
                s.getPrices().stream()
                        .map(Price::getPrice)
                        .collect(Collectors.toList())
        );

        // représentations
        dto.representations = s.getRepresentations().stream().map(rep -> {
            var rd = new RepresentationDto();
            rd.id = rep.getId();
            rd.when = rep.getWhen();
            rd.capacity = rep.getEffectiveCapacity();
            rd.availableSeats = rep.getAvailableSeats();
            return rd;
        }).toList();

        // collaborateurs (artistTypes)
        dto.collaborators = s.getArtistTypes().stream().map(at -> {
            var atd = new ArtistTypeDto();
            atd.type = at.getType().getType();
            atd.artist = new ArtistDto();
            atd.artist.id = at.getArtist().getId();
            atd.artist.firstname = at.getArtist().getFirstname();
            atd.artist.lastname = at.getArtist().getLastname();
            return atd;
        }).toList();

        // ** mapping des tags **
        dto.setTags(
                s.getTags().stream()
                        .map(Tag::getTag)
                        .collect(Collectors.toSet())
        );
        return dto;
    }


    /**
     * DTO → Entité (pour POST et PUT)
     */
    public Show toEntity(ShowRequest req) {
        Show s = new Show();
        s.setTitle(req.getTitle());
        s.setDescription(req.getDescription());
        s.setPosterUrl(req.getPosterUrl());
        s.setBookable(req.getBookable());

        // Lieu
        if (req.getLocationId() != null) {
            s.setLocation(locationService.get(req.getLocationId().toString()));
        }

        // Tarifs
        if (req.getPriceIds() != null) {
            List<Price> prices = req.getPriceIds()
                    .stream()
                    .map(priceService::getPrice)
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
            s.setPrices(prices);
        }

        // dates de création / modif
        s.setCreatedAt(LocalDateTime.now());
        s.setUpdatedAt(null);
        return s;
    }
}
