package at.fh.sem4.ea.game;

import at.fh.sem4.ea.game.model.*;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final RestTemplate restTemplate;

    @PostMapping("/create")
    public Game createGame() {
        return gameService.createGame();
    }

    @GetMapping("/exists/{gameId}")
    public boolean doesGameExist(@PathVariable Long gameId) {
        return gameService.GameExists(gameId);
    }

    @PostMapping("/createPlayer")
    public void postPlayer(@RequestParam String name, @RequestParam Long gameId){
        gameService.createPlayer(name, gameId);
    }

    @PostMapping("/createShip")
    public void postShip(@RequestParam Integer x, @RequestParam Integer y, @RequestParam Long gameId, @RequestParam Long playerId){
        gameService.createShip(x, y, gameId, playerId);
    }

//    @PostMapping("/createPlayer")
//    public void postPlayer(@RequestBody PlayerDTO playerDTO){
//        gameService.createPlayer(playerDTO.getName(), playerDTO.getGameId());
//    }




}

