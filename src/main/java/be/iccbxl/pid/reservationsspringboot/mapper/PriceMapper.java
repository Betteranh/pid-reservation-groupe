package be.iccbxl.pid.reservationsspringboot.mapper;

import be.iccbxl.pid.reservationsspringboot.dto.PriceDTO;
import be.iccbxl.pid.reservationsspringboot.model.Price;

public class PriceMapper {
    public static PriceDTO toPriceDTO(Price price) {
        if (price == null)
            return null;
        return new PriceDTO(
                price.getId(),
                price.getType(),
                price.getPrice(),
                price.getStartDate(),
                price.getEndDate()
        );
    }
}
