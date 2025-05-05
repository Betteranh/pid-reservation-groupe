package be.iccbxl.pid.reservationsspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class ShowDto {
    public Long id;
    public String slug;
    @NotBlank
    public String title;
    public String description;
    public String posterUrl;
    public Long locationId;
    @NotNull
    public boolean bookable;
    public List<Double> prices;
    public Set<String> tags;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public List<RepresentationDto> representations;
    public List<ArtistTypeDto> collaborators;
}
