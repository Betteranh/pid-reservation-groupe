package be.iccbxl.pid.reservationsspringboot.api.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.iccbxl.pid.reservationsspringboot.api.dto.LoginDTO;
import be.iccbxl.pid.reservationsspringboot.api.dto.UserDTO;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.model.UserRole;
import be.iccbxl.pid.reservationsspringboot.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // Injection de dépendance
    public AuthApiController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            // Convertir le DTO en entité User
            User user = new User();
            user.setLogin(userDTO.getLogin());
            user.setEmail(userDTO.getEmail());
            user.setFirstname(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encoder le mot de passe
            user.setLangue(userDTO.getLangue());
            user.setCreated_at(LocalDateTime.now()); // Important: définir la date de création
            user.setRole(UserRole.MEMBER); // Définir un rôle par défaut en utilisant l'enum
            
            // Utiliser la méthode addUser qui existe déjà dans votre service
            userService.addUser(user);
            
            // Réponse de succès
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Inscription réussie");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Pour voir l'erreur dans les logs
            
            // Réponse d'erreur
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Échec de l'inscription: " + e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Rechercher l'utilisateur par login en utilisant la méthode du repository
            User user = userService.findByLogin(loginDTO.getLogin());
            
            if (user == null) {
                throw new Exception("Utilisateur non trouvé");
            }
            
            // Vérifier le mot de passe avec BCrypt
            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                throw new Exception("Mot de passe incorrect");
            }
            
            // Créer un objet utilisateur pour la réponse (sans le mot de passe)
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("login", user.getLogin());
            userData.put("firstname", user.getFirstname());
            userData.put("lastname", user.getLastname());
            userData.put("email", user.getEmail());
            userData.put("role", user.getRole()); // Utilisation de l'enum
            
            // Réponse avec token (à implémenter avec JWT ou autre)
            Map<String, Object> response = new HashMap<>();
            response.put("token", "jwt-token-example"); // À remplacer par un vrai token JWT
            response.put("user", userData);
            response.put("success", true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Échec de la connexion: " + e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }
}