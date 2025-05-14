package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Reservation;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;
import be.iccbxl.pid.reservationsspringboot.repository.ReservationRepository;

import be.iccbxl.pid.reservationsspringboot.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserRepository userRepository;

    // ✅ PAGE DE SUCCÈS après paiement Stripe
    @GetMapping("/payment/success")
    public String paymentSuccess(HttpSession session) {
        Long id = (Long) session.getAttribute("lastReservationId");
        if (id != null) {
            Reservation res = reservationService.getReservation(id);
            if (res != null) {
                res.setStatus("confirmed");
                reservationService.save(res);
            }
            session.removeAttribute("cart");
            session.removeAttribute("lastReservationId");
        }

        return "payment/success";
    }


    // ✅ PAGE : "Mes réservations"
    @GetMapping("/my-reservations")
    public String myReservations(Principal principal, Model model) {
        User user = userRepository.findByLogin(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }

        //  On récupère uniquement les réservations confirmées
        List<Reservation> reservations = reservationService.findByUserIdAndStatus(user.getId(), "confirmed");
        model.addAttribute("reservations", reservations);
        return "reservation/my_reservations";
    }

}
