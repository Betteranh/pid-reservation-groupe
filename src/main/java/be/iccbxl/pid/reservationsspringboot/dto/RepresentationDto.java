package be.iccbxl.pid.reservationsspringboot.dto;

import java.time.LocalDateTime;

public class RepresentationDto {
    public Long id;
    public LocalDateTime when;
    public Integer capacity;
    public Integer availableSeats;
}
