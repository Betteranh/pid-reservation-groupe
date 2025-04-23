package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    @ManyToMany
    @JoinTable(
            name = "artist_type",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @ToString.Exclude
    private List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public Type addArtist(Artist artist) {
        if (!this.artists.contains(artist)) {
            this.artists.add(artist);
            artist.addType(this);
        }

        return this;
    }

    public Type removeType(Artist artist) {
        if (this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.getTypes().remove(this);
        }

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Type type = (Type) o;
        return getId() != null && Objects.equals( getId(), type.getId() );
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
