package at.fh.sem4.ea.ship;

import at.fh.sem4.ea.ship.model.Ship;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShipController {
    private final ShipService shipService;

    @PostMapping("/createShip")
    public Ship addShip(@RequestParam Integer x, @RequestParam Integer y, @RequestParam Long gameId, @RequestParam Long playerId) {
        return shipService.addShip(x, y, gameId, playerId);
    }

//    @GetMapping("/game/{gameId}/display")
//    public String getGameDisplay(@PathVariable Long gameId) {
//        return shipService.getGameDisplay(gameId);
//    }

}
