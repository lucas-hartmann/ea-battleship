package at.fh.sem4.ea.player;


import at.fh.sem4.ea.player.model.Player;
import at.fh.sem4.ea.player.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public Player addPlayer(Long gameId, String name) {
        Player player = new Player();
        player.setName(name);
        player.setGameid(gameId);
        return playerRepository.save(player);
    }
}
