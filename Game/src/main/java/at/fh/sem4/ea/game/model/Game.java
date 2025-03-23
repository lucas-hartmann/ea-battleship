package at.fh.sem4.ea.game.model;

import jakarta.persistence.*;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @ElementCollection
    private List<Long> players;

    private boolean isFinished;
}
