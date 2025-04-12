package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Reservation;
import be.iccbxl.pid.reservationsspringboot.model.User;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.EntityModel;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Collection<EntityModel<Reservation>> findByUserId(Long id);
    List<Reservation> findByStatus(String status);
    List<Reservation> findByRepresentationReservations_Representation_Show_Id(Long showId); 
    

}
