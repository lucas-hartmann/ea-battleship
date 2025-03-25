package at.fh.sem4.ea.game;


import at.fh.sem4.ea.game.model.Game;
import at.fh.sem4.ea.game.model.Guess;
import at.fh.sem4.ea.game.model.PlayerDTO;
import at.fh.sem4.ea.game.model.ShipDTO;
import at.fh.sem4.ea.game.repo.GameRepository;
import at.fh.sem4.ea.game.repo.GuessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GuessRepository guessRepository;
    private final RestTemplate restTemplate;

    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    public Game createGame() {
        Game game = new Game();
        return gameRepository.save(game);
    }
    public Boolean GameExists(Long gameId){
        return gameRepository.existsById(gameId);
    }


    public void createPlayer(String name, Long gameId) {
        String playerServiceUrl = "http://localhost:8082/createPlayer?name=" + name + "&gameId=" + gameId;
        circuitBreakerFactory.create("createPlayerBreaker").run(() ->
                        restTemplate.postForObject(playerServiceUrl, null, PlayerDTO.class),
                throwable -> {
                    System.out.println("Fallback: Returning null PlayerDTO");
                    return null;
                }
        );
    }

    public ShipDTO createShip(int x, int y, Long gameid, Long playerid) {
        String shipServiceUrl = "http://localhost:8083/createShip?x=" + x + "&y=" + y + "&gameId=" + gameid + "&playerId=" + playerid;

        return circuitBreakerFactory.create("createShipBreaker").run(
                () -> restTemplate.postForObject(shipServiceUrl, null, ShipDTO.class),
                throwable -> {
                    System.out.println("Fallback: Could not create ship");
                    return null;
                }
        );
    }

    public String guess(Long gameId, int x, int y) {
        String shipServiceUrl = "http://localhost:8083";
        Guess guess = new Guess();
        guess.setGame(gameRepository.findById(gameId).orElseThrow());
        guess.setX(x);
        guess.setY(y);

        String url = UriComponentsBuilder.fromHttpUrl(shipServiceUrl + "/check")
                .queryParam("gameId", gameId)
                .queryParam("x", x)
                .queryParam("y", y)
                .toUriString();

        Boolean hit = circuitBreakerFactory.create("guessBreaker").run(
                () -> restTemplate.getForObject(url, Boolean.class),
                throwable -> {
                    System.out.println("Fallback: Could not get guess result");
                    return false;
                }
        );

        guess.setHit(hit);
        guessRepository.save(guess);

        return hit ? "Hit!" : "Miss!";
    }

    public String getGameDisplay(Long gameId) {
        String shipServiceUrl = "http://localhost:8083";
        char[][] board = new char[50][50];

        for (int i = 0; i < 50; i++) {
            Arrays.fill(board[i], '~');
        }

        String url = UriComponentsBuilder.fromHttpUrl(shipServiceUrl + "/ships")
                .queryParam("gameId", gameId)
                .toUriString();

        ShipDTO[] ships = circuitBreakerFactory.create("getGameDisplayBreaker").run(
                () -> restTemplate.getForObject(url, ShipDTO[].class),
                throwable -> {
                    System.out.println("Fallback: Could not retrieve ships");
                    return new ShipDTO[0];
                }
        );

        List<Guess> hits = guessRepository.findByGameIdAndHitIsTrue(gameId);

        if (ships != null) {
            for (ShipDTO ship : ships) {
                int x = ship.getX();
                int y = ship.getY();

                if (x >= 0 && x < 50 && y >= 0 && y < 50) {
                    board[y][x] = Character.forDigit(ship.getPlayerid().intValue(), 10);
                }
            }
        }

        for (Guess hit : hits) {
            int x = hit.getX();
            int y = hit.getY();

            if (x >= 0 && x < 50 && y >= 0 && y < 50) {
                board[y][x] = 'x';
            }
        }

        StringBuilder display = new StringBuilder();
        for (char[] row : board) {
            display.append(new String(row)).append("\n");
        }

        return display.toString();
    }
}