package at.fh.sem4.ea.game.repo;

import at.fh.sem4.ea.game.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepository extends JpaRepository<Guess, Long> {
    List<Guess> findByGameIdAndHitIsTrue(Long gameId);
}
