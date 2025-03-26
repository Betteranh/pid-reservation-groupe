package be.iccbxl.pid.apireservationsshow.service;

import be.iccbxl.pid.apireservationsshow.model.Show;
import be.iccbxl.pid.apireservationsshow.repository.ShowRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public Optional<Show> getShow(final Long id) {
        return showRepository.findById(id);
    }

    public Iterable<Show> getshows() {
        return showRepository.findAll();
    }

    public void deleteShow(final Long id) {
        showRepository.deleteById(id);
    }

    public Show saveShow(Show show) {
        Show savedShow = showRepository.save(show);
        return savedShow;
    }
}
