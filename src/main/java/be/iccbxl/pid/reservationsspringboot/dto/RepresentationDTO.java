package be.iccbxl.pid.reservationsspringboot.dto;


import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter

public class RepresentationDTO {

    private Long id;
    private String showTitle;
    private LocalDateTime when;
    private String locationDesignation;

    public RepresentationDTO(Long id, String showTitle, LocalDateTime when, String locationDesignation) {
        this.id = id;
        this.showTitle = showTitle;
        this.when = when;
        this.locationDesignation = locationDesignation;
    }




}