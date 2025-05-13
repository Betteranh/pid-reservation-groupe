package be.iccbxl.pid.reservationsspringboot.api.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @GetMapping("/check")
    public ResponseEntity<?> checkAuth(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build(); // non connecté
        }
        return ResponseEntity.ok(Map.of("username", userDetails.getUsername()));
    }

        @GetMapping("/csrf/token")
        public ResponseEntity<Void> csrf(HttpServletRequest request) {
            // Accéder au HttpServletRequest force Spring à générer le token
            return ResponseEntity.ok().build();
        }




    }
