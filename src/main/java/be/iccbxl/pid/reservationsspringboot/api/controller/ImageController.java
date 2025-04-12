package be.iccbxl.pid.reservationsspringboot.api.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {
    private final Path fileStorageLocation;

    public ImageController() {
        // Chemin absolu vers votre dossier d'images
        this.fileStorageLocation = Paths.get("src/main/resources/static/images")
            .toAbsolutePath().normalize();
    }

    @GetMapping("/api/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            Path filePath = fileStorageLocation.resolve(filename.toLowerCase()).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                        "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            } else {
                // Log de débogage
                System.err.println("Image non trouvée : " + filePath);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException ex) {
            System.err.println("Erreur de chargement de l'image : " + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}