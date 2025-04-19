package be.iccbxl.pid.reservationsspringboot.api.dto;

import java.time.LocalDateTime;

public class ShowDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String posterUrl;
    private boolean bookable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeur vide
    public ShowDTO() {}
    
    // Constructeur avec les paramètres
    public ShowDTO(Long id, String slug, String title, String description, 
                   String posterUrl, boolean bookable, 
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.bookable = bookable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }
    
    public boolean isBookable() { return bookable; }
    public void setBookable(boolean bookable) { this.bookable = bookable; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}