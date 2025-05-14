package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import be.iccbxl.pid.reservationsspringboot.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtistTypeRepository extends CrudRepository<ArtistType, Long> {
    Optional<ArtistType> findByArtistAndType(Artist artist, Type type);
}