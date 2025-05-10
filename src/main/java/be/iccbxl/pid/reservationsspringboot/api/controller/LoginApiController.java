package be.iccbxl.pid.reservationsspringboot.api.controller;


import be.iccbxl.pid.reservationsspringboot.config.CustomUserDetailsService;
import be.iccbxl.pid.reservationsspringboot.dto.AuthRequest;
import be.iccbxl.pid.reservationsspringboot.dto.LoginDTO;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class LoginApiController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO) {
        try {
            System.out.println("loginDTO: " + loginDTO.getLogin() + ",password: " + loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Connexion réussie");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("échec de la connexion !");
        }

    }
    @PostMapping
    public ResponseEntity<?> registerUser (@RequestBody User user) {
        try {
            userService.addUser( user );
            return ResponseEntity.ok( "Inscription réussie !" );
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'inscription !");
        }

    }



    // utilisé par le frontend ( voir index.js de router )
    @GetMapping ("/auth/check")
    public ResponseEntity<?> checkAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Principal: " + authentication.getPrincipal());
        System.out.println("Authorities: " + authentication.getAuthorities());

        System.out.println("authentifié");
        if (authentication != null && authentication.isAuthenticated()){

            System.out.println("authentifié");

            if (authentication.getPrincipal() instanceof User){

                return ResponseEntity.ok(authentication.getPrincipal());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non authentifié");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(false).invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Déconnecté");
    }


}
