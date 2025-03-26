package be.iccbxl.pid.apireservationsshow.repository;

import be.iccbxl.pid.apireservationsshow.model.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {
}
