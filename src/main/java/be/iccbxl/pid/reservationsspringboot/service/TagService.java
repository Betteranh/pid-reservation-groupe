package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TagService {
    @Autowired
    private TagRepository repository;

    public List<Tag> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Tag> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Tag> findByTag(String tagLabel) {
        return repository.findByTag(tagLabel);
    }

    public void save(Tag tag) {
        repository.save(tag);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
