package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.*;
import be.iccbxl.pid.reservationsspringboot.service.ReviewService;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/shows")
    public String index(Model model) {
        List<Show> shows = showService.getAll();
        model.addAttribute("shows", shows);
        model.addAttribute("title", "Liste des spectacles");
        return "show/index";
    }

    @GetMapping("/shows/{id}")
    public String show(@PathVariable("id") String id, Model model) {
        Show show = showService.get(id);

        if (show == null) {
            return "redirect:/shows"; // ou une page d’erreur 404 personnalisée
        }

        // Grouper les artistes par type (ex: Auteur, Comédien...)
        Map<String, List<Artist>> collaborateurs = new TreeMap<>();
        for (ArtistType at : show.getArtistTypes()) {
            String type = at.getType().getType();
            collaborateurs.computeIfAbsent(type, k -> new ArrayList<>()).add(at.getArtist());
        }

        // Charger les commentaires liés au spectacle
        List<Review> reviews = reviewService.getAllReviews();

        model.addAttribute("show", show);
        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("reviews", reviews);
        model.addAttribute("title", "Fiche du spectacle");

        return "show/show";
    }
}
