package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.*;
import be.iccbxl.pid.reservationsspringboot.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import be.iccbxl.pid.reservationsspringboot.repository.RepresentationRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@SessionAttributes("cart")
public class ShowController {
    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @Autowired
    ShowService service;

    @Autowired
    private RepresentationRepository representationRepo;

    @Autowired
    TagService tagService;

    @Autowired
    private ReviewService reviewService; // 🔧 Ajouté pour les commentaires

    @GetMapping("/dev/shows")
    public String index(@RequestParam(value = "tag", required = false) String tagLabel, Model model) {
        List<Show> shows;
        String title = "Liste des spectacles";

        if (tagLabel != null && !tagLabel.isBlank()) {
            Tag tag = tagService.findByTag(tagLabel).orElse(null);
            if (tag != null) {
                shows = service.getByTag(tag);
                model.addAttribute("resultCount", shows.size());
                title += " – Mots-clés : " + tagLabel;
            } else {
                shows = new ArrayList<>();
                model.addAttribute("errorMessage", "Mot-clé introuvable");
            }
        } else {
            shows = service.getAll();
        }

        model.addAttribute("shows", shows);
        model.addAttribute("title", title);
        model.addAttribute("availableTags", tagService.findAll());

        return "show/index";
    }

    @GetMapping("/dev/shows/{id}")
    @Transactional
    public String show(Model model, @PathVariable("id") String id) {
        Show show = service.getWithAssociations(id);
        if (show == null) {
            model.addAttribute("errorMessage", "Spectacle introuvable.");
            return "error/404";
        }

        Hibernate.initialize(show.getTags());
        show.setTags(new HashSet<>(show.getTags()));

        Set<ArtistType> uniqueArtistTypes = new HashSet<>(show.getArtistTypes());
        Map<String, ArrayList<Artist>> collaborateurs = new TreeMap<>();
        for (ArtistType at : uniqueArtistTypes) {
            String type = at.getType().getType();
            ArrayList<Artist> artistes = collaborateurs.computeIfAbsent(type, k -> new ArrayList<>());
            if (!artistes.contains(at.getArtist())) {
                artistes.add(at.getArtist());
            }
        }

        show.getRepresentations().forEach(rep -> Hibernate.initialize(rep.getItems()));

        boolean canBook = show.getRepresentations().stream()
                .anyMatch(r -> r.getAvailableSeats() > 0);

        model.addAttribute("canBook", canBook);
        model.addAttribute("collaborateurs", collaborateurs);
        model.addAttribute("availableTags", tagService.findAll());
        model.addAttribute("show", show);
        model.addAttribute("title", "Fiche d'un spectacle");

        // 🔧 Ajouter les reviews au modèle sans toucher le reste du contrôleur
        model.addAttribute("reviews", reviewService.getReviewsByShowId(show.getId()));

        model.addAttribute("videos", show.getVideos());
        model.addAttribute("newVideo", new Video());

        model.addAttribute("availableTroupes", troupeService.getAllTroupes());
        return "show/show";
    }

    @PostMapping("/shows/{id}/tags")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String addTagToShow(@PathVariable("id") String id,
                               @RequestParam("tagId") Long tagId,
                               RedirectAttributes redirectAttributes) {
        Show show = service.getWithAssociations(id);
        Tag tag = tagService.find(tagId).orElse(null);

        if (show != null && tag != null) {
            Hibernate.initialize(show.getTags());
            Tag[] tagsArray = show.getTags().toArray(new Tag[0]);
            Set<Tag> updatedTags = new HashSet<>(Arrays.asList(tagsArray));

            if (!updatedTags.contains(tag)) {
                updatedTags.add(tag);
                show.setTags(updatedTags);
                service.save(show);
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

        List<Show> shows = service.getWithoutTag(tag);
        model.addAttribute("shows", shows);
        model.addAttribute("title", "Spectacles sans le mot-clé : " + tagLabel);

        return "show/index";
    }

    @PostMapping("/shows/{id}/reserve")
    public String reserveToCart(@PathVariable("id") String id,
                                @RequestParam Long representationId,
                                @RequestParam Long priceId,
                                @RequestParam int quantity,
                                @ModelAttribute("cart") Cart cart) {

        Representation rep = representationRepo.findById(representationId).orElse(null); // ✅
        Price price = rep.getShow().getPrices().stream()
                .filter(p -> p.getId().equals(priceId))
                .findFirst()
                .orElse(null);

        if (rep == null || price == null) {
            return "redirect:/shows/" + id + "?error";
        }

        CartItem item = new CartItem();
        item.setRepresentationId(rep.getId());
        item.setPriceId(price.getId());
        item.setQuantity(quantity);
        item.setLabel(rep.getScheduledAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        item.setUnitPrice(price.getPrice());

        cart.addItem(item);
        return "redirect:/cart/view";
    }

    @Autowired
    private be.iccbxl.pid.reservationsspringboot.repository.VideoRepository videoRepository;


    @PostMapping("/dev/shows/{id}/videos")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String addVideoToShow(@PathVariable("id") String id,
                                 @ModelAttribute("newVideo") Video video,
                                 RedirectAttributes redirectAttributes) {

        Show show = service.getWithAssociations(id);
        if (show == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Spectacle introuvable.");
            return "redirect:/dev/shows/" + id;
        }

        // Forcer id à 0 pour éviter le conflit d’objet détaché
        video.setId(0);

        // Convertir automatiquement l’URL YouTube
        String url = video.getVideoUrl();
        if (url.contains("watch?v=")) {
            url = url.replace("watch?v=", "embed/");
            video.setVideoUrl(url);
        }

        video.setShow(show);
        videoRepository.save(video);

        redirectAttributes.addFlashAttribute("successMessage", "Vidéo ajoutée !");
        return "redirect:/dev/shows/" + id;
    }




    @PostMapping("/dev/shows/{showId}/videos/{videoId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String deleteVideo(@PathVariable("showId") Long showId,
                              @PathVariable("videoId") Long videoId,
                              RedirectAttributes redirectAttributes) {

        Optional<Video> videoOpt = videoRepository.findById(videoId);
        if (videoOpt.isPresent()) {
            videoRepository.delete(videoOpt.get());
            redirectAttributes.addFlashAttribute("successMessage", "Vidéo supprimée !");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Vidéo introuvable.");
        }

        return "redirect:/dev/shows/" + showId;
    }
    @Autowired
    private TroupeService troupeService;

    @Autowired
    private ArtistService artistService;
    @PostMapping("/dev/artists/{id}/troupe")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateArtistTroupe(@PathVariable Long id,
                                     @RequestParam(required = false) Long troupeId,
                                     RedirectAttributes redirectAttributes,
                                     HttpServletRequest request) {

        Artist artist = artistService.getArtist(id);
        if (artist == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Artiste introuvable.");
            return "redirect:/shows";
        }

        Troupe troupe = null;
        if (troupeId != null) {
            troupe = troupeService.getTroupeById(troupeId).orElse(null);
        }
        artist.setTroupe(troupe);

        artistService.updateArtist(id, artist);

        redirectAttributes.addFlashAttribute("successMessage",
                "Troupe modifiée pour " + artist.getFirstname() + " " + artist.getLastname());

        String referer = request.getHeader("Referer");

        return "redirect:" + (referer != null ? referer : "/shows");
    }






}
