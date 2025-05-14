package be.iccbxl.pid.reservationsspringboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CollabForm {
    @NotNull
    private Long artistId;
    @NotNull
    private Long typeId;
}