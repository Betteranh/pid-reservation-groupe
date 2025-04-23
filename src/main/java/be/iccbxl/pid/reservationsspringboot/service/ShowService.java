package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Location;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TagService tagService;

    public List<Show> getAll() {
        return (List<Show>) showRepository.findAll();
    }

    public Show get(String id) {
        Long indice = Long.parseLong(id);
        return showRepository.findById(indice).orElse(null);
    }

    public void add(Show show) {
        showRepository.save(show);
    }

    public void update(String id, Show show) {
        showRepository.save(show);
    }

    public void delete(String id) {
        Long indice = Long.parseLong(id);
        showRepository.deleteById(indice);
    }

    public void save(Show show) {
        showRepository.save(show);
    }

    public List<Show> getFromLocation(Location location) {
        return showRepository.findByLocation(location);
    }

    public List<Show> getByTag(Tag tag) {
        return showRepository.findByTagsContaining(tag);
    }

    public List<Show> getWithoutTag(Tag tag) {
        return showRepository.findByTagsNotContaining(tag);
    }

    public Show getWithAssociations(String id) {
        Long indice = Long.parseLong(id);
        return showRepository.findByIdWithAssociations(indice).orElse(null);
    }

    public boolean addTagToShow(Show show, String tagLabel) {
        Optional<Tag> tag = tagService.findByTag(tagLabel);
        if (tag.isPresent()) {
            Set<Tag> tags = show.getTags();
            if (!tags.contains(tag.get())) {
                tags.add(tag.get());
                show.setTags(tags);
                showRepository.save(show);
                return true;
            }
        }
        return false;
    }

    public boolean removeTagFromShow(Show show, String tagLabel) {
        Optional<Tag> tag = tagService.findByTag(tagLabel);
        if (tag.isPresent()) {
            Set<Tag> tags = show.getTags();
            if (tags.contains(tag.get())) {
                tags.remove(tag.get());
                show.setTags(tags);
                showRepository.save(show);
                return true;
            }
        }
        return false;
    }
}
