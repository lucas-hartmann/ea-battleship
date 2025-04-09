package at.fh.sem4.ea.player;


import at.fh.sem4.ea.player.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

//    @PostMapping("/createPlayer")
//    public Player addPlayer(@RequestParam Long gameId, @RequestParam String name) {
//        return playerService.addPlayer(gameId, name);
//    }

    @RabbitListener(queues = "player-queue")
    public void createPlayer(String message) {
        String[] parts = message.split(",");
        Long gameId = Long.parseLong(parts[0]);
        String name = parts[1];

        playerService.addPlayer(gameId, name);
        System.out.println("Player created: " + name);
    }
}