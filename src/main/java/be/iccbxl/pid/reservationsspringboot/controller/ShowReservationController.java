package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.*;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;
import be.iccbxl.pid.reservationsspringboot.service.PriceService;
import be.iccbxl.pid.reservationsspringboot.service.RepresentationService;
import be.iccbxl.pid.reservationsspringboot.service.ReservationService;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shows/{showId}/reserve")
public class ShowReservationController {
    @Autowired
    private ShowService showService;
    @Autowired
    private RepresentationService repService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserRepository userRepo;

    /**
     * Affiche le formulaire de réservation : choix de la représentation, du type de tarif, du nombre de places.
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String showForm(@PathVariable Long showId, Model model) {
        Show show = showService.get(showId.toString());
        if (show == null || !show.isBookable()) {
            model.addAttribute("errorMessage", "Spectacle non trouvable ou non réservable.");
            return "error/404";
        }
        model.addAttribute("show", show);

        List<Representation> reps = repService.getFromShow(show);
        model.addAttribute("representations", reps);

        // On suppose qu’un spectacle peut avoir plusieurs tarifs associés
        model.addAttribute("prices", show.getPrices());

        model.addAttribute("form", new ReservationForm());
        return "reservation/form";
    }

    /**
     * Traite la soumission du formulaire, crée Reservation + RepresentationReservation(s).
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public String processForm(
            @PathVariable Long showId,
            @RequestParam("representationId") Long representationId,
            @RequestParam("priceId") Long priceId,
            @RequestParam("quantity") Integer quantity,
            Principal principal,
            Model model
    ) {
        // 1) Récupère l’utilisateur
        User user = userRepo.findByLogin(principal.getName());
        if (user == null) {
            model.addAttribute("errorMessage", "Utilisateur non trouvé.");
            return "error/403";
        }

        // 2) Crée la réservation
        Reservation res = new Reservation();
        res.setUser(user);
        res.setBookingDate(LocalDateTime.now());
        res.setStatus("PENDING");
        reservationService.save(res);

        // 3) Crée et rattache l’item
        Representation rep = repService.get(representationId.toString());
        Price price = priceService.getPrice(priceId);
        RepresentationReservation item = new RepresentationReservation();
        item.setRepresentation(rep);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setReservation(res);
        res.addItem(item);

        // 4) Sauvegarde finale
        reservationService.save(res);

        return "redirect:/shows/" + showId + "/reserve/confirmation/" + res.getId();

    }

    /**
     * Page de confirmation
     */
    @GetMapping("/confirmation/{resId}")
    public String confirm(@PathVariable Long resId, Model model) {
        Reservation res = reservationService.getReservation(resId);
        if (res == null) {
            model.addAttribute("errorMessage", "Réservation introuvable.");
            return "error/404";
        }
        model.addAttribute("reservation", res);
        return "reservation/confirmation";
    }

    /**
     * Petit DTO pour lier le formulaire Thymeleaf
     */
    public static class ReservationForm {
        private Long representationId;
        private Long priceId;
        private Integer quantity;
        // getters & setters…
    }
}
