package at.fh.sem4.ea.game;


import at.fh.sem4.ea.game.model.Game;
import at.fh.sem4.ea.game.model.PlayerDTO;
import at.fh.sem4.ea.game.model.ShipDTO;
import at.fh.sem4.ea.game.repo.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final RestTemplate restTemplate;


    public Game createGame() {
        Game game = new Game();
        return gameRepository.save(game);
    }
    public Boolean GameExists(Long gameId){
        return gameRepository.existsById(gameId);
    }

//    public PlayerDTO createPlayer(String name, Long gameId) {
//        String playerServiceUrl = "http://localhost:8082/createPlayer";
//        PlayerDTO playerDTO = new PlayerDTO(name, gameId);
//        return restTemplate.postForObject(playerServiceUrl, playerDTO, PlayerDTO.class);
//    }

    public PlayerDTO createPlayer(String name, Long gameId) {
        String playerServiceUrl = "http://localhost:8082/createPlayer?name=" + name + "&gameId=" + gameId;
        return restTemplate.postForObject(playerServiceUrl, null, PlayerDTO.class);
    }

    public ShipDTO createShip(int x, int y, Long gameid, Long playerid){
        String shipServiceUrl = "http://localhost:8083/createShip?x=" + x + "&y=" + y + "&gameId=" + gameid + "&playerId=" + playerid;
        return restTemplate.postForObject(shipServiceUrl, null, ShipDTO.class);
    }

}