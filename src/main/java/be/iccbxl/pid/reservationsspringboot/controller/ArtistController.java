package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.Troupe;
import be.iccbxl.pid.reservationsspringboot.service.ArtistService;
import be.iccbxl.pid.reservationsspringboot.service.TroupeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ArtistController {
    @Autowired
    ArtistService service;

    @Autowired
    private TroupeService troupeService;

    @GetMapping("/artists")
    public String index(Model model) {
        List<Artist> artists = service.getAllArtists();
        List<Troupe> troupes = troupeService.findAll();


        model.addAttribute("artists", artists);
        model.addAttribute("troupes", troupes);
        model.addAttribute("title", "Liste des artistes");

        return "artist/index";
    }

    @GetMapping("/artists/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        Artist artist = service.getArtist(id);

        model.addAttribute("artist", artist);
        model.addAttribute("title", "Fiche d'un artiste");
        model.addAttribute("troupes", troupeService.findAll());

        return "artist/show";
    }

    @GetMapping("/artists/{id}/edit")
    public String edit(Model model, @PathVariable long id, HttpServletRequest request) {
        Artist artist = service.getArtist(id);

        model.addAttribute("artist", artist);

        //Générer le lien retour pour l'annulation
        String referrer = request.getHeader("Referer");

        if (referrer != null && !referrer.equals("")) {
            model.addAttribute("back", referrer);
        } else {
            model.addAttribute("back", "/artists/" + artist.getId());
        }

        return "artist/edit";
    }

    @PutMapping("/artists/{id}/edit")
    public String update(@Valid @ModelAttribute Artist artist, BindingResult bindingResult,
                         @PathVariable long id, Model model, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Échec de la modification de l'artiste !");

            return "artist/edit";
        }

        Artist existing = service.getArtist(id);

        if (existing == null) {
            return "artist/index";
        }

        service.updateArtist(id, artist);
        redirAttrs.addFlashAttribute("successMessage", "Artiste modifié avec succès.");

        return "redirect:/artists/" + artist.getId();
    }

    @GetMapping("/artists/create")
    public String create(Model model) {
        if (!model.containsAttribute("artist")) {
            model.addAttribute("artist", new Artist());
        }

        return "artist/create";
    }

    @PostMapping("/artists/create")
    public String store(@Valid @ModelAttribute Artist artist, BindingResult bindingResult,
                        Model model, RedirectAttributes redirAttrs) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Échec de la création de l'artiste !");

            return "artist/create";
        }

        service.addArtist(artist);
        redirAttrs.addFlashAttribute("successMessage", "Artiste créé avec succès.");

        return "redirect:/artists/" + artist.getId();
    }

    @DeleteMapping("/artists/{id}")
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirAttrs) {
        Artist existing = service.getArtist(id);

        if (existing != null) {
            service.deleteArtist(id);

            redirAttrs.addFlashAttribute("successMessage", "Artiste supprimé avec succès.");
        } else {
            redirAttrs.addFlashAttribute("errorMessage", "Échec de la suppression de l'artiste !");
        }

        return "redirect:/artists";
    }

    @PostMapping("/artists/{id}/affect-troupe")
    @PreAuthorize("hasRole('ADMIN')")
    public String affectTroupe(
            @PathVariable("id") long id,
            @RequestParam(value = "troupeId", required = false) Long troupeId,
            RedirectAttributes redir) {

        Artist artist = service.getArtist(id);
        if (artist == null) {
            redir.addFlashAttribute("errorMessage", "Artiste introuvable.");
            return "redirect:/artists";
        }

        if (troupeId == null) {
            artist.setTroupe(null);
        } else {
            Troupe troupe = troupeService.findById(troupeId).orElse(null);
            if (troupe == null) {
                redir.addFlashAttribute("errorMessage", "Troupe introuvable.");
                return "redirect:/artists";
            }
            artist.setTroupe(troupe);
        }

        service.updateArtist(id, artist);
        redir.addFlashAttribute("successMessage", "Affiliation mise à jour avec succès.");
        return "redirect:/artists";
    }

}