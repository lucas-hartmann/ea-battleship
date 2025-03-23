package at.fh.sem4.ea.ship;


import at.fh.sem4.ea.ship.model.Ship;
import at.fh.sem4.ea.ship.repo.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;

    public Ship addShip(int x, int y, Long gameId, Long playerId) {
        Ship ship = new Ship();
        ship.setX(x);
        ship.setY(y);
        ship.setSunk(false);
        ship.setPlayerid(playerId);
        ship.setGameid(gameId);

        return shipRepository.save(ship);
    }

//    public String getGameDisplay(Long gameId) {
//        char[][] board = new char[50][50];
//
//        // Fill the board with water
//        for (int i = 0; i < 50; i++) {
//            Arrays.fill(board[i], '~');
//        }
//
//        // Get ships for the game
//        List<Ship> ships = shipRepository.findByGameId(gameId);
//
//        // Get all hit locations
//        List<Guess> hits = guessRepository.findByGameIdAndHitIsTrue(gameId);
//
//        // Place ships on the board
//        for (Ship ship : ships) {
//            int x = ship.getX();
//            int y = ship.getY();
//
//            if (x >= 0 && x < 50 && y >= 0 && y < 50) {
//                board[y][x] = Character.forDigit(ship.getPlayer().getId().intValue(), 10);
//            }
//        }
//
//        // Mark hits as 'x'
//        for (Guess hit : hits) {
//            int x = hit.getX();
//            int y = hit.getY();
//
//            if (x >= 0 && x < 50 && y >= 0 && y < 50) {
//                board[y][x] = 'x';
//            }
//        }
//
//        // Convert board to a string
//        StringBuilder display = new StringBuilder();
//        for (char[] row : board) {
//            display.append(new String(row)).append("\n");
//        }
//
//        return display.toString();
//    }
}
