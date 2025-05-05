package be.iccbxl.pid.reservationsspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ShowRequest {
    @NotBlank
    private String title;

    private String description;
    private String posterUrl;

    /**
     * ID du lieu (optionnel)
     */
    private Long locationId;

    @NotNull
    private Boolean bookable;

    /**
     * IDs des tarifs associés
     */
    private List<Long> priceIds;
}
