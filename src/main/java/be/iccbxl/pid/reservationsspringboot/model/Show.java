package be.iccbxl.pid.reservationsspringboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.slugify.Slugify;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shows")
@JsonIgnoreProperties({"artistTypes", "reviews", "hibernateLazyInitializer"})

public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    private String title;
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    /**
     * Lieu de création du spectacle
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    @JsonBackReference("location-shows") // Ajouter cette annotation


    private Location location;

    private boolean bookable;

    @ManyToMany
    @JoinTable(name = "show_price",
            joinColumns = @JoinColumn(name = "show_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"))
    private List<Price> prices = new ArrayList<>();

    /**
     * Date de création du spectacle
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Date de modification du spectacle
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(targetEntity = Representation.class, mappedBy = "show")
    @JsonManagedReference("show-representations")

    private List<Representation> representations = new ArrayList<>();

    @ManyToMany(mappedBy = "shows")
    @JsonIgnore

    private List<ArtistType> artistTypes = new ArrayList<>();

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<Review> reviews = new ArrayList<>();


    public Show() {
    }

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

    public Long getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    private void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        Slugify slg = new Slugify();

        this.setSlug(slg.slugify(title));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        if (this.location != null) {
            this.location.removeShow(this);    //déménager de l'ancien lieu
        }
        this.location = location;
        if (location != null) {
            location.addShow(this);        //emménager dans le nouveau lieu
        }
    }
    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }

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

    /**
     * Get the performances (artists in a type of collaboration) for the show
     */
    public List<ArtistType> getArtistTypes() {
        return artistTypes;
    }

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

    @Override
    public String toString() {
        return "Show [id=" + id + ", slug=" + slug + ", title=" + title
                + ", description=" + description + ", posterUrl=" + posterUrl + ", location="
                + location + ", bookable=" + bookable + ", prices=" + prices.size()
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", representations=" + representations.size() + "]";
    }

}
