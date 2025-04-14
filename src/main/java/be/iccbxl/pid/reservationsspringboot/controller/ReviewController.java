package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.*;
import be.iccbxl.pid.reservationsspringboot.repository.ShowRepository;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;
import be.iccbxl.pid.reservationsspringboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submit")
    public String submitReview(@RequestParam Long showId,
                               @RequestParam String review,
                               @RequestParam int stars,
                               Principal principal) {

        if (principal == null) return "redirect:/login";

        User user = userRepository.findByLogin(principal.getName());
        Show show = showRepository.findById(showId).orElse(null);

        if (user != null && show != null) {
            Review newReview = new Review();
            newReview.setUser(user);
            newReview.setShow(show);
            newReview.setReview(review);
            newReview.setStars(stars);
            reviewService.addReview(newReview);
        }

        return "redirect:/show/" + showId;
    }
}