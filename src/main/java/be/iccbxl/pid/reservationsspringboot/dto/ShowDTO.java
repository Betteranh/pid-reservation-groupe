package be.iccbxl.pid.reservationsspringboot.dto;

import be.iccbxl.pid.reservationsspringboot.model.Price;
import be.iccbxl.pid.reservationsspringboot.model.Representation;

import java.time.LocalDateTime;
import java.util.List;


public class ShowDTO {

    private Long id;
    private String title;
    private String posterUrl;
    private LocalDateTime createdAt;
    private boolean bookable;
    private List<Price> prices;
    private List<Representation> representations;


    public ShowDTO(Long id, String title, String posterUrl, LocalDateTime createdAt, boolean bookable, List<Price> prices, List<Representation> representations) {
        this.id = id;
        this.title = title;
        this.posterUrl = (posterUrl != null && !posterUrl.isEmpty()) ? posterUrl : "https://example.com/default-poster.jpg";
        this.createdAt = createdAt;
        this.bookable = bookable;
        this.prices = prices;
        this.representations = representations;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isBookable() {
        return bookable;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public List<Representation> getRepresentations() {
        return representations;
    }
}
