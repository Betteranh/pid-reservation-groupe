package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.ArtistType;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import be.iccbxl.pid.reservationsspringboot.service.TagService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class ShowController {
    @Autowired
    ShowService service;

    @Autowired
    TagService tagService;

    @Autowired
    private ShowService showService;

    @GetMapping("/shows")
    public String index(@RequestParam(value = "tag", required = false) String tagLabel, Model model) {
        List<Show> shows;
        String title = "Liste des spectacles";

        if (tagLabel != null && !tagLabel.isBlank()) {
            Tag tag = tagService.findByTag(tagLabel).orElse(null);
            if (tag != null) {
                shows = showService.getByTag(tag);
                model.addAttribute("resultCount", shows.size());
                title += " – Mots-clés : " + tagLabel;
            } else {
                shows = new ArrayList<>();
                model.addAttribute("errorMessage", "Mot-clé introuvable");
            }
        } else {
            shows = showService.getAll();
        }

        model.addAttribute("shows", shows);
        model.addAttribute("title", title);
        model.addAttribute("availableTags", tagService.findAll());

        return "show/index";
    }

    @GetMapping("/shows/{id}")
    @Transactional
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.getWithAssociations(id);
        if (show == null) {
            model.addAttribute("errorMessage", "Spectacle introuvable.");
            return "error/404";
        }

        // Forcer le chargement de la collection tags et la détacher
        Hibernate.initialize(show.getTags());
        show.setTags(new HashSet<>(show.getTags()));

        // Grouper les artistes par type en éliminant les doublons
        Set<ArtistType> uniqueArtistTypes = new HashSet<>(show.getArtistTypes());
        Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();
        for (ArtistType at : uniqueArtistTypes) {
            String type = at.getType().getType();
            ArrayList<Artist> artistes = collaborateurs.computeIfAbsent(type, k -> new ArrayList<>());
            if (!artistes.contains(at.getArtist())) {  // évite le doublon
                artistes.add(at.getArtist());
            }
        }

        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("availableTags", tagService.findAll());
        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");

        return "show/show";
    }

    @PostMapping("/shows/{id}/tags")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String addTagToShow(@PathVariable("id") String id,
                               @RequestParam("tagId") Long tagId,
                               RedirectAttributes redirectAttributes) {
        // Utilisez ici getWithAssociations pour obtenir un objet complètement initialisé
        Show show = showService.getWithAssociations(id);
        Tag tag = tagService.find(tagId).orElse(null);

        if (show != null && tag != null) {
            // Forcer l'initialisation de la collection, au cas où
            Hibernate.initialize(show.getTags());

            // Créer une copie stable de la collection en passant par un tableau pour éviter l'itération directe
            Tag[] tagsArray = show.getTags().toArray(new Tag[0]);
            Set<Tag> updatedTags = new HashSet<>();
            for (Tag t : tagsArray) {
                updatedTags.add(t);
            }

            // Vérifier si le tag est déjà présent dans la copie stable
            if (!updatedTags.contains(tag)) {
                updatedTags.add(tag);
                show.setTags(updatedTags);
                showService.save(show);
                redirectAttributes.addFlashAttribute("successMessage", "Mot-clé ajouté !");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Ce mot-clé est déjà associé à ce spectacle.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Spectacle ou mot-clé introuvable.");
        }

        return "redirect:/shows/" + id;
    }

    @GetMapping("/shows/exclude-tag/{tag}")
    public String showsWithoutTag(@PathVariable("tag") String tagLabel, Model model) {
        Tag tag = tagService.findByTag(tagLabel).orElse(null);
        if (tag == null) {
            model.addAttribute("errorMessage", "Mot-clé non trouvé.");
            return "redirect:/shows";
        }

        List<Show> shows = showService.getWithoutTag(tag);
        model.addAttribute("shows", shows);
        model.addAttribute("title", "Spectacles sans le mot-clé : " + tagLabel);

        return "show/index";
    }
}
