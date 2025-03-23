package at.fh.sem4.ea.player;


import at.fh.sem4.ea.player.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/createPlayer")
    public Player addPlayer(@RequestParam Long gameId, @RequestParam String name) {
        return playerService.addPlayer(gameId, name);
    }

}