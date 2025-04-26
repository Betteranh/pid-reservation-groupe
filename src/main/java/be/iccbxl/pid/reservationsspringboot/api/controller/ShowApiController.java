package be.iccbxl.pid.reservationsspringboot.api.controller;

import be.iccbxl.pid.reservationsspringboot.dto.ShowDTO;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8081")

public class ShowApiController {

    @Autowired
    private ShowService showService;

    // Endpoint pour récupérer tous les spectacles
    @GetMapping("/spectacles")
    public List<ShowDTO> getAllShows() {
        // Récupère tous les spectacles disponibles
        List<Show> shows = showService.getAll();
        // Mapper les spectacles en DTO
        return shows.stream()
                .map(show -> new ShowDTO(show.getTitle(), show.getPosterUrl(), show.getCreatedAt()))
                .collect(Collectors.toList());
    }
}