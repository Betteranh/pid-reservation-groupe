package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTag(String tag); // pour rechercher un tag par son texte
}
