package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 255, nullable = false, unique = true)
    private String videoUrl;

    @Column(length = 255, nullable = false)
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    /**
     * Retourne l’URL au format YouTube “embed”
     */
    @Transient
    public String getEmbedUrl() {
        String url = videoUrl.trim();
        if (url.contains("youtu.be/")) {
            // https://youtu.be/ID → https://www.youtube.com/embed/ID
            return url.replace("youtu.be/", "www.youtube.com/embed/");
        }
        if (url.contains("watch?v=")) {
            // https://www.youtube.com/watch?v=ID → https://www.youtube.com/embed/ID
            return url.replace("watch?v=", "embed/");
        }
        // si déjà en embed, on retourne tel quel
        return url;
    }
}
