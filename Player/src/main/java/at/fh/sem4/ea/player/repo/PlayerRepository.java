package at.fh.sem4.ea.player.repo;


import at.fh.sem4.ea.player.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
