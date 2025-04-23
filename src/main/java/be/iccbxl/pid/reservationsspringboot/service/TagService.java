package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // Récupérer tous les tags
    public List<Tag> findAll() {
        return (List<Tag>) tagRepository.findAll();
    }

    // Trouver un tag par son ID
    public Optional<Tag> find(Long id) {
        return tagRepository.findById(id);
    }

    // Trouver un tag par son label
    public Optional<Tag> findByTag(String tagLabel) {
        return tagRepository.findByTag(tagLabel);
    }

    // Sauvegarder un tag
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    // Supprimer un tag par son ID
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
