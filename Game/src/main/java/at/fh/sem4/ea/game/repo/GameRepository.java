package at.fh.sem4.ea.game.repo;


import at.fh.sem4.ea.game.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
