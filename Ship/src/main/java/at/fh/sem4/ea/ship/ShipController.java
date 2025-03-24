package at.fh.sem4.ea.ship;

import at.fh.sem4.ea.ship.model.Ship;
import at.fh.sem4.ea.ship.repo.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ShipController {
    private final ShipService shipService;

    @PostMapping("/createShip")
    public Ship addShip(@RequestParam Integer x, @RequestParam Integer y, @RequestParam Long gameId, @RequestParam Long playerId) {
        return shipService.addShip(x, y, gameId, playerId);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkShip(@RequestParam Long gameId, @RequestParam int x, @RequestParam int y) {
        List<Ship> ships = shipService.findAllShips();
        boolean hit = ships.stream()
                .anyMatch(ship -> ship.getGameid().equals(gameId) && ship.getX() == x && ship.getY() == y);
        return ResponseEntity.ok(hit);
    }

    @GetMapping("/ships")
    public ResponseEntity<List<Ship>> getShipsByGameId(@RequestParam Long gameId) {
        List<Ship> ships = shipService.findShipByGameId(gameId);
        return ResponseEntity.ok(ships);
    }
}
