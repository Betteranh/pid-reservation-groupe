package be.iccbxl.pid.reservationsspringboot.model;

import com.github.slugify.Slugify;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    private boolean bookable;

    @ManyToMany
    @JoinTable(name = "show_price",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"))
    private List<Price> prices = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(targetEntity = Representation.class, mappedBy = "show")
    private List<Representation> representations = new ArrayList<>();

    @ManyToMany(mappedBy = "shows")
    private List<ArtistType> artistTypes = new ArrayList<>();

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "show_tag",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Tag> tags = new HashSet<>();

    // ==== Constructeurs ====
    public Show() {}

    public Show(String title, String description, String posterUrl, Location location, boolean bookable, List<Price> prices, List<Representation> representations) {
        Slugify slg = new Slugify();
        this.slug = slg.slugify(title);
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.location = location;
        this.bookable = bookable;
        this.prices = prices;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.representations = representations;
    }

    // ==== Getters/Setters ====

    public Long getId() { return id; }

    public String getSlug() { return slug; }

    private void setSlug(String slug) { this.slug = slug; }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
        Slugify slg = new Slugify();
        this.setSlug(slg.slugify(title));
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPosterUrl() { return posterUrl; }

    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public Location getLocation() { return location; }

    public void setLocation(Location location) {
        if (this.location != null) {
            this.location.removeShow(this);
        }
        this.location = location;
        if (this.location != null) {
            this.location.addShow(this);
        }
    }

    public boolean isBookable() { return bookable; }

    public void setBookable(boolean bookable) { this.bookable = bookable; }

    public List<Price> getPrices() { return prices; }

    public void setPrices(List<Price> prices) { this.prices = prices; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<Representation> getRepresentations() { return representations; }

    public void setRepresentations(List<Representation> representations) { this.representations = representations; }

    public Show addRepresentation(Representation representation) {
        if (!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.setShow(this);
        }
        return this;
    }

    public Show removeRepresentation(Representation representation) {
        if (this.representations.contains(representation)) {
            this.representations.remove(representation);
            if (representation.getLocation().equals(this)) {
                representation.setLocation(null);
            }
        }
        return this;
    }

    public List<ArtistType> getArtistTypes() { return artistTypes; }

    public void setArtistTypes(List<ArtistType> artistTypes) { this.artistTypes = artistTypes; }

    public Show addArtistType(ArtistType artistType) {
        if (!this.artistTypes.contains(artistType)) {
            this.artistTypes.add(artistType);
            artistType.addShow(this);
        }
        return this;
    }

    public Show removeArtistType(ArtistType artistType) {
        if (this.artistTypes.contains(artistType)) {
            this.artistTypes.remove(artistType);
            artistType.getShows().remove(this);
        }
        return this;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    // ==== equals / hashCode / toString ====

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + title
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", prices=" + prices.size()
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", representations=" + representations.size() + "]";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Show show = (Show) o;
        return getId() != null && Objects.equals(getId(), show.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
