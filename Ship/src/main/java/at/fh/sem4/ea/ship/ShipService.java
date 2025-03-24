package at.fh.sem4.ea.ship;


import at.fh.sem4.ea.ship.model.Ship;
import at.fh.sem4.ea.ship.repo.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Ship> findShipByGameId(Long gameId){
        return shipRepository.findByGameid(gameId);
    }

    public List<Ship> findAllShips(){
        return  shipRepository.findAll();
    }
}
