package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.*;
import be.iccbxl.pid.reservationsspringboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShowService showService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public String submitReview(@RequestParam Long showId,
                               @RequestParam String review,
                               @RequestParam int stars,
                               @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null) {
            return "redirect:/login";
        }

        // Chercher le User à partir du login
        User user = userService.findByLogin(currentUser.getUsername()); // méthode que tu dois déjà avoir
        if (user == null) {
            return "redirect:/login"; // sécurité
        }

        // Chercher le Show
        Show show = showService.get(showId.toString());
        if (show == null) {
            return "redirect:/shows"; // show introuvable
        }

        // Créer l’objet Review
        Review r = new Review();
        r.setUser(user);
        r.setShow(show);
        r.setReview(review);
        r.setStars(stars);
        r.setCreatedAt(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());

        // Enregistrer
        reviewService.addReview(r);

        return "redirect:/shows/" + showId;
    }
    @PostMapping("/reviews/update")
    public String updateReview(@RequestParam("reviewId") Long id,
                               @RequestParam("review") String reviewText,
                               @RequestParam("stars") int stars,
                               Principal principal) {

        Review review = reviewService.getReview(id);
        if (review != null && review.getUser().getLogin().equals(principal.getName())) {
            review.setReview(reviewText);
            review.setStars(stars);
            review.setUpdatedAt(LocalDateTime.now());
            reviewService.addReview(review); // `addReview` fait un save()
        }

        return "redirect:/shows/" + review.getShow().getId(); // ou slug si besoin
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam Long reviewId, Principal principal) {
        Review review = reviewService.getReview(reviewId);

        if (review != null) {
            String currentUsername = principal.getName();
            User currentUser = userService.findByLogin(currentUsername);

            boolean isOwner = review.getUser().getLogin().equals(currentUsername);
            boolean isAdmin = currentUser != null && currentUser.hasRole(UserRole.ADMIN);

            if (isOwner || isAdmin) {
                reviewService.deleteReview(reviewId);
            }
        }

        return "redirect:/shows/" + (review != null ? review.getShow().getId() : "");
    }

}