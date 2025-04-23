package be.iccbxl.pid.reservationsspringboot.model;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identifiant du tag (clé primaire)

    private String tag; // Le texte du mot-clé

    // Constructeurs
    public Tag() {}

    public Tag(String tag) {
        this.tag = tag;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
