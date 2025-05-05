package be.iccbxl.pid.reservationsspringboot.api.controller;

import be.iccbxl.pid.reservationsspringboot.dto.ShowDTO;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*")
public class ShowApiController {

    @Autowired
    private ShowService showService;

    // Endpoint pour récupérer tous les spectacles
    @GetMapping("/spectacles")
    public List<ShowDTO> getAllShows() {

        List<Show> shows = showService.getAll();

        return shows.stream()
                .map(show -> new ShowDTO(show.getId(),
                                         show.getTitle(),
                                         show.getPosterUrl(),
                                         show.getCreatedAt(),
                                         show.isBookable(),
                                         show.getPrices(),
                                         show.getRepresentations()
                ))
                .collect(Collectors.toList());
    }
    @GetMapping("/spectacles/{id}")
    public ResponseEntity<ShowDTO> getShowById(@PathVariable("id") Long id) {
        Show show = showService.get(String.valueOf(id));
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        ShowDTO dto = new ShowDTO(
                show.getId(),
                show.getTitle(),
                show.getPosterUrl(),
                show.getCreatedAt(),
                show.isBookable(),
                show.getPrices(),
                show.getRepresentations()
        );
        return ResponseEntity.ok(dto);
    }




}