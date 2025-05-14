package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Reservation;
import be.iccbxl.pid.reservationsspringboot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByUserIdAndStatus(Long userId, String status);

    List<Reservation> findByUser_Id(Long userId);
}
