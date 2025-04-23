package be.iccbxl.pid.reservationsspringboot.dto;

import java.time.LocalDateTime;


public class ShowDTO {

    private String title;
    private String posterUrl;
    private LocalDateTime createdAt;

    public ShowDTO(String title, String posterUrl, LocalDateTime createdAt) {
        this.title = title;
        this.posterUrl = (posterUrl != null && !posterUrl.isEmpty()) ? posterUrl : "https://example.com/default-poster.jpg";
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = (posterUrl != null && !posterUrl.isEmpty()) ? posterUrl : "https://example.com/default-poster.jpg";
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
