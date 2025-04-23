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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class ShowController {

    @Autowired
    private ShowService showService;

    @Autowired
    private TagService tagService;

    // Afficher la liste des spectacles, avec possibilité de filtrer par tag
    @GetMapping("/shows")
    public String index(@RequestParam(value = "tag", required = false) String tagLabel, Model model) {
        List<Show> shows;
        String title = "Liste des spectacles";

        // Filtrage des spectacles par mot-clé
        if (tagLabel != null && !tagLabel.isBlank()) {
            Tag tag = tagService.findByTag(tagLabel).orElse(null);
            if (tag != null) {
                shows = showService.getByTag(tag);
                title += " – Mots-clés : " + tagLabel;
                model.addAttribute("resultCount", shows.size());
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

    // Afficher les détails d'un spectacle
    @GetMapping("/shows/{id}")
    @Transactional
    public String show(@PathVariable("id") String id, Model model) {
        Show show = showService.getWithAssociations(id);
        if (show == null) {
            model.addAttribute("errorMessage", "Spectacle introuvable.");
            return "error/404";
        }

        // Initialisation des tags (pour éviter des problèmes de lazy loading)
        Hibernate.initialize(show.getTags());
        show.setTags(new HashSet<>(show.getTags())); // Assure que la collection est stable

        // Grouper les artistes par type, éviter les doublons
        Map<String, List<Artist>> collaborateurs = groupArtistsByType(show);

        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");
        model.addAttribute("availableTags", tagService.findAll());

        return "show/show";
    }

    // Ajouter un tag à un spectacle (uniquement pour les admins)
    @PostMapping("/shows/{id}/tags")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String addTagToShow(@PathVariable("id") String id,
                               @RequestParam("tagId") Long tagId,
                               RedirectAttributes redirectAttributes) {
        Show show = showService.getWithAssociations(id);
        Tag tag = tagService.find(tagId).orElse(null);

        if (show != null && tag != null) {
            // Vérifier si le tag est déjà associé au spectacle
            if (addTagIfNotExists(show, tag)) {
                redirectAttributes.addFlashAttribute("successMessage", "Mot-clé ajouté !");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Ce mot-clé est déjà associé à ce spectacle.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Spectacle ou mot-clé introuvable.");
        }

        return "redirect:/shows/" + id;
    }

    // Afficher les spectacles qui n'ont pas un certain mot-clé
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

    // Méthode pour grouper les artistes par type
    private Map<String, List<Artist>> groupArtistsByType(Show show) {
        Map<String, List<Artist>> collaborateurs = new TreeMap<>();

        for (ArtistType at : show.getArtistTypes()) {
            String type = at.getType().getType();
            collaborateurs.computeIfAbsent(type, k -> new ArrayList<>()).add(at.getArtist());
        }

        return collaborateurs;
    }

    // Ajouter un tag à un spectacle si ce tag n'est pas déjà présent
    private boolean addTagIfNotExists(Show show, Tag tag) {
        Set<Tag> tags = show.getTags();
        if (!tags.contains(tag)) {
            tags.add(tag);
            show.setTags(tags);
            showService.save(show);
            return true;
        }
        return false;
    }
}
