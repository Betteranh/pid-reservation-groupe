package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import be.iccbxl.pid.reservationsspringboot.model.Type;
import be.iccbxl.pid.reservationsspringboot.repository.ArtistTypeRepository;
import be.iccbxl.pid.reservationsspringboot.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistTypeService {
    @Autowired
    private ArtistTypeRepository artistTypeRepository;

    @Autowired
    private TypeRepository typeRepository;

    /**
     * Pour alimenter le formulaire “type d’artiste” (Auteur, Comédien, …).
     */
    public List<Type> getAllTypes() {
        return (List<Type>) typeRepository.findAll();
    }

    /**
     * Récupère la liaison ArtistType existante pour cet artist+type
     * ou la crée si elle n’existe pas encore.
     */
    public ArtistType getOrCreate(Artist artist, Long typeId) {
        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Type d’artiste introuvable : " + typeId));
        return artistTypeRepository
                .findByArtistAndType(artist, type)
                .orElseGet(() -> {
                    ArtistType at = new ArtistType();
                    at.setArtist(artist);
                    at.setType(type);
                    return artistTypeRepository.save(at);
                });
    }
}