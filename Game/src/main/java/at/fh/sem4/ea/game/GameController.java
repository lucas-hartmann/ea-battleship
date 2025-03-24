package at.fh.sem4.ea.game;

import at.fh.sem4.ea.game.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

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

    @PostMapping("/guess")
    public String makeGuess(@RequestParam Long gameId, @RequestParam int x, @RequestParam int y) {
        return gameService.guess(gameId, x, y);
    }

    @PostMapping("/display")
    public String makeGuess(@RequestParam Long gameId) {
        return gameService.getGameDisplay(gameId);
    }

//    @ExceptionHandler(CallNotPermittedException.class)
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    public String handleCircuitBreaker() {
//        return "Circuit breaker is open";
//    }

}

