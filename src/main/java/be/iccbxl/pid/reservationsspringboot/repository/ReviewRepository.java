package be.iccbxl.pid.reservationsspringboot.repository;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByShowId(Long showId);
}