package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max=30, message = "Le nom du tag ne peut pas dépasser 30 caractères.")
    @NotBlank(message = "Le nom du Tag est obligatoire.")
    @Column(length = 30, unique = true, nullable = false)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private Set<Show> shows = new HashSet<>();
}
