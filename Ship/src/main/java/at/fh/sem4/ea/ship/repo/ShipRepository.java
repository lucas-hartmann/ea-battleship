package at.fh.sem4.ea.ship.repo;

import at.fh.sem4.ea.ship.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
