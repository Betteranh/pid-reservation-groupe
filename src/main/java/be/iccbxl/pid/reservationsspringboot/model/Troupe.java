package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "troupes")
public class Troupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false, unique = true)
    private String name;

    @Column(name = "logo_url", length = 255, nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "troupe")
    private List<Artist> artists = new ArrayList<>();
}
