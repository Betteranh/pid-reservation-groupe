package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.User;


import java.util.List;
import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.model.Show;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByShow(Show show);
    List<Review> findByUser(User user);
    List<Review> findByValidated(Boolean validated);
}
