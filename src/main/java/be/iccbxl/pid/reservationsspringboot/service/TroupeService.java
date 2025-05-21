package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Troupe;
import be.iccbxl.pid.reservationsspringboot.repository.TroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TroupeService {
    private final TroupeRepository troupeRepo;

    @Autowired
    public TroupeService(TroupeRepository troupeRepo) {
        this.troupeRepo = troupeRepo;
    }

    /** Renvoie la liste de toutes les troupes **/
    public List<Troupe> findAll() {
        return StreamSupport
                .stream(troupeRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /** Renvoie une troupe par son id, ou Optional.empty() **/
    public Optional<Troupe> findById(Long id) {
        return troupeRepo.findById(id);
    }

    /** Créer ou met à jour une troupe **/
    public Troupe save(Troupe troupe) {
        return troupeRepo.save(troupe);
    }

    /** Supprime une troupe (rarement utile si référencée par des artistes) **/
    public void delete(Long id) {
        troupeRepo.deleteById(id);
    }
}


